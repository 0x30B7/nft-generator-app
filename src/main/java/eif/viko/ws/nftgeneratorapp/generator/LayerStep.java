package eif.viko.ws.nftgeneratorapp.generator;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;

import java.util.List;

public class LayerStep {

    private final ArtifactLayer layer;
    private final List<ImageProcessorStep> processorSteps;

    public LayerStep(ArtifactLayer layer, List<ImageProcessorStep> processorSteps) {
        this.layer = layer;
        this.processorSteps = processorSteps;
    }

    public ArtifactLayer getLayer() {
        return layer;
    }

    public List<ImageProcessorStep> getProcessorSteps() {
        return processorSteps;
    }

}
