package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.NegativeColorFillStep;

import java.util.Map;

public class NegativeColorFillStepProvider {

    public static NegativeColorFillStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        NegativeColorFillStep step = new NegativeColorFillStep();

        // no data to retrieve

        return step;
    }
}
