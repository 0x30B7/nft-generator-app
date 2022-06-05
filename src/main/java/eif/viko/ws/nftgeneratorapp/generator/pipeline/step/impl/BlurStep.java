package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.StepProperty;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlurStep extends ImageProcessorStep {

    private final StepProperty<Integer> radius = new StepProperty<>(Integer.class);
    private final StepProperty<Boolean> blendAlpha = new StepProperty<>(Boolean.class);

    @Override
    public void validateStep() throws IllegalStateException {
        if (!radius.isSet()) {
            throw new IllegalStateException("missing 'radius' value");
        }
        if (!blendAlpha.isSet()) {
            throw new IllegalStateException("missing 'blendAlpha' value");
        }
    }

    @Override
    public void onProcess(BufferedImage image) {
        int maxXCoordinate = image.getWidth() - 1;
        int maxYCoordinate = image.getHeight() - 1;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                int x1 = clamp(i - radius.get(), 0, maxXCoordinate);
                int y1 = clamp(j - radius.get(), 0, maxYCoordinate);
                int x2 = clamp(i + radius.get(), 0, maxXCoordinate);
                int y2 = clamp(j + radius.get(), 0, maxYCoordinate);

                int redAccumulator = 0;
                int greenAccumulator = 0;
                int blueAccumulator = 0;
                int alphaAccumulator = 0;
                int points = 0;

                for (int xx = x1; xx <= x2; xx++) {
                    for (int yy = y1; yy <= y2; yy++) {

                        int color = image.getRGB(xx, yy);
                        redAccumulator += (color >> 16) & 0xFF;
                        greenAccumulator += (color >> 8) & 0xFF;
                        blueAccumulator += (color >> 0) & 0xFF;

                        if (blendAlpha.get()) alphaAccumulator += (color >> 24) & 0xFF;

                        points++;
                    }
                }

                int redAvg = redAccumulator / points;
                int greenAvg = greenAccumulator / points;
                int blueAvg = blueAccumulator / points;
                int alphaAvg = blendAlpha.get() ? alphaAccumulator / points : 255;

                image.setRGB(i, j,
                        ((alphaAvg & 0xFF) << 24) |
                                ((redAvg & 0xFF) << 16) |
                                ((greenAvg & 0xFF) << 8) |
                                ((blueAvg & 0xFF) << 0));
            }
        }
    }

    private int clamp(int value, int min, int max) {
        return value < min ? min : value >= max ? max : value;
    }

    public StepProperty<Integer> getRadiusProperty() {
        return radius;
    }

    public StepProperty<Boolean> getBlendAlphaProperty() {
        return blendAlpha;
    }
}
