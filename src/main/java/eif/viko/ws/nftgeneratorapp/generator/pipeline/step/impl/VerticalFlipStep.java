package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.awt.image.BufferedImage;

/**
 * Class representing a step where the image is flipped on its vertical axis
 */
public class VerticalFlipStep extends ImageProcessorStep {

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateStep() throws IllegalStateException {
        // nothing to validate
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onProcess(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage originalImage = image;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB((width - 1) - x, y, originalImage.getRGB(x, y));
            }
        }
    }
}