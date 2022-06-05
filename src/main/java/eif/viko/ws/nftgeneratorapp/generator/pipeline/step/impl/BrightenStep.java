package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.StepProperty;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrightenStep extends ImageProcessorStep {

    private final StepProperty<Float> factor = new StepProperty<>(Float.class);

    @Override
    public void validateStep() throws IllegalStateException {
        if (!factor.isSet()) {
            throw new IllegalStateException("missing 'factor' value");
        }
    }

    @Override
    public void onProcess(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);

                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = (color >> 0) & 0xFF;
                int alpha = (color >> 24) & 0xFF;

                red = clamp((int) ((float) red * factor.get()), 0, 255);
                green = clamp((int) ((float) green * factor.get()), 0, 255);
                blue = clamp((int) ((float) blue * factor.get()), 0, 255);

                image.setRGB(i, j,
                        ((alpha & 0xFF) << 24) |
                                ((red & 0xFF) << 16) |
                                ((green& 0xFF) << 8) |
                                ((blue & 0xFF) << 0));
            }
        }
    }

    private int clamp(int value, int min, int max) {
        return value < min ? min : value >= max ? max : value;
    }

    public StepProperty<Float> getFactorProperty() {
        return factor;
    }
}
