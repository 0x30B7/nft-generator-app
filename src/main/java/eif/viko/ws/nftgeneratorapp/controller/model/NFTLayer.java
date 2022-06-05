package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.List;

public class NFTLayer {

    private int imageId;
    private List<NFTProcessorStep> processorSteps;

    public NFTLayer(int imageId, List<NFTProcessorStep> steps) {
        this.imageId = imageId;
        this.processorSteps = steps;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public List<NFTProcessorStep> getProcessorSteps() {
        return processorSteps;
    }

    public void setProcessorSteps(List<NFTProcessorStep> processorSteps) {
        this.processorSteps = processorSteps;
    }

}
