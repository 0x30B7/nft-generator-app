package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.RotateClockwiseStep;

import java.util.Map;

public class RotateClockwiseStepProvider {

    public static RotateClockwiseStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        RotateClockwiseStep step = new RotateClockwiseStep();

        Object times = properties.get("times");
        if (times instanceof Number num) {
            step.getTimesProperty().set(num.intValue());
        }

        return step;
    }
}
