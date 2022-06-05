package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.VerticalFlipStep;

import java.util.Map;

public class VerticalFlipStepProvider {

    public static VerticalFlipStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        VerticalFlipStep step = new VerticalFlipStep();

        // no data to retrieve

        return step;
    }
}
