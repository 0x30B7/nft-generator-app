package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

import java.awt.image.BufferedImage;

public abstract class ImageProcessorStep {

    public abstract void validateStep() throws IllegalStateException;

    public abstract void onProcess(BufferedImage image);

}
