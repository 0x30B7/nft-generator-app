package eif.viko.ws.nftgeneratorapp.generator;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.util.List;

/**
 * Class representing an NFT image layer's processing steps
 */
public class LayerStep {

    private final ArtifactLayer layer;
    private final List<ImageProcessorStep> processorSteps;

    public LayerStep(ArtifactLayer layer, List<ImageProcessorStep> processorSteps) {
        this.layer = layer;
        this.processorSteps = processorSteps;
    }

    /**
     * @return The layer associated with the processing steps
     */
    public ArtifactLayer getLayer() {
        return layer;
    }

    /**
     * @return The processing steps associated with the layer
     */
    public List<ImageProcessorStep> getProcessorSteps() {
        return processorSteps;
    }

}
