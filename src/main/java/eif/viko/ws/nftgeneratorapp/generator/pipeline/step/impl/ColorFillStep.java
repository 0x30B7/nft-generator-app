package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.StepProperty;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class representing a step where the image is solid filled with the provided color
 */
public class ColorFillStep extends ImageProcessorStep {

    private final StepProperty<Color> color = new StepProperty<>(Color.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateStep() throws IllegalStateException {
        if (!color.isSet()) {
            throw new IllegalStateException("missing 'color' value");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onProcess(BufferedImage image) {
        int color = this.color.get().getRGB();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                int pixelColor = image.getRGB(x, y);

                if (pixelColor != 0) {
                    image.setRGB(x, y, color);
                }
            }
        }
    }

    /**
     * Returns the color step property
     *
     * @return The color factor property
     */
    public StepProperty<Color> getColorProperty() {
        return color;
    }
}