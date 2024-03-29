package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.StepProperty;

import java.awt.image.BufferedImage;

/**
 * Class representing a step where the image is blurred according to the provided radius
 */
public class BlurStep extends ImageProcessorStep {

    private final StepProperty<Integer> radius = new StepProperty<>(Integer.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateStep() throws IllegalStateException {
        if (!radius.isSet()) {
            throw new IllegalStateException("missing 'radius' value");
        }
    }

    /**
     * {@inheritDoc}
     */
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
                        alphaAccumulator += (color >> 24) & 0xFF;
                        points++;
                    }
                }

                int redAvg = redAccumulator / points;
                int greenAvg = greenAccumulator / points;
                int blueAvg = blueAccumulator / points;
                int alphaAvg = alphaAccumulator / points;

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

    /**
     * Returns the radius step property
     *
     * @return The radius step property
     */
    public StepProperty<Integer> getRadiusProperty() {
        return radius;
    }
}