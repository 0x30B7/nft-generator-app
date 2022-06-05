package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.List;

/**
 * Class representing an NFT layer request model
 */
public class NFTLayer {

    private int imageId;
    private List<NFTProcessorStep> processorSteps;

    public NFTLayer(int imageId, List<NFTProcessorStep> steps) {
        this.imageId = imageId;
        this.processorSteps = steps;
    }

    /**
     * @return The image id of the stored pre-existing image associated with layer
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Updates the image id of the stored pre-existing image associated with this layer
     *
     * @param imageId The given image id
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * @return The image processor step descriptors of the image processor steps associated
     * with this layer
     */
    public List<NFTProcessorStep> getProcessorSteps() {
        return processorSteps;
    }

    /**
     * Updates the image processor step descriptors of the image processor steps associated
     * with this layer
     *
     * @param processorSteps The given image processor step descriptors
     */
    public void setProcessorSteps(List<NFTProcessorStep> processorSteps) {
        this.processorSteps = processorSteps;
    }

}
