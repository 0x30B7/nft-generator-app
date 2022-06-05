package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.GrayscaleColorStep;

import java.util.Map;

public class GrayscaleColorStepProvider {

    public static GrayscaleColorStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        GrayscaleColorStep step = new GrayscaleColorStep();

        // no data to retrieve

        return step;
    }
}
