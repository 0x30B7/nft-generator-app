package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BrightenStep;

import java.util.Map;

public class BrightenStepProvider {

    public static BrightenStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        BrightenStep step = new BrightenStep();

        Object factor = properties.get("factor");
        if (factor instanceof Number num) {
            step.getFactorProperty().set(num.floatValue());
        }

        return step;
    }
}
