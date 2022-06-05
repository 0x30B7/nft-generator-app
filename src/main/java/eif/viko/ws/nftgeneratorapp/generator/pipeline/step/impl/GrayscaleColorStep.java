package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

/**
 * Class representing a step where the image is converted to a grayscale image
 */
public class GrayscaleColorStep extends ImageProcessorStep {

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
        BufferedImage originalImage = image;
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(originalImage, image);
    }
}