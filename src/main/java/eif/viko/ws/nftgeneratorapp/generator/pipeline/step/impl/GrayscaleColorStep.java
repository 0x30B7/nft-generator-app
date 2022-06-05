package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class GrayscaleColorStep extends ImageProcessorStep {

    @Override
    public void validateStep() throws IllegalStateException {
        // nothing to validate
    }

    @Override
    public void onProcess(BufferedImage image) {
        BufferedImage originalImage = image;
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(originalImage, image);
    }
}
