package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

import java.awt.image.BufferedImage;

/**
 * Class representing an image manipulation action
 */
public abstract class ImageProcessorStep {

    /**
     * Validates the state of the process step after it has been initialized
     * and its properties been set
     *
     * @throws IllegalStateException if a property is detected to have not been
     *                               set or has an illegal value
     */
    public abstract void validateStep() throws IllegalStateException;

    /**
     * Manipulates the given image
     *
     * @param image The image to manipulate. This can be 
     */
    public abstract void onProcess(BufferedImage image);

}
