package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.awt.image.BufferedImage;

public class NegativeColorFillStep extends ImageProcessorStep {

    @Override
    public void validateStep() throws IllegalStateException {
        // nothing to validate
    }

    @Override
    public void onProcess(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, pixel);
            }
        }
    }
}
