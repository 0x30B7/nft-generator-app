package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.HorizontalFlipStep;

import java.util.Map;

public class HorizontalFlipStepProvider {

    public static HorizontalFlipStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        HorizontalFlipStep step = new HorizontalFlipStep();

        // no data to retrieve

        return step;
    }
}
