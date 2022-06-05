package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.awt.image.BufferedImage;

public class HorizontalFlipStep extends ImageProcessorStep {
    @Override
    public void validateStep() throws IllegalStateException {
        // nothing to validate
    }

    @Override
    public void onProcess(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                flippedImage.setRGB(x, (height-1)-y, image.getRGB(x, y));
            }
        }
        image = flippedImage;
    }
}