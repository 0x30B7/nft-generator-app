package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BrightenStep;

import java.util.Map;

/**
 * Class providing {@link BrightenStep} instances
 */
public class BrightenStepProvider {

    /**
     * Creates a brighten image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static BrightenStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        BrightenStep step = new BrightenStep();

        Object factor = properties.get("factor");
        if (factor instanceof Number num) {
            step.getFactorProperty().set(num.floatValue());
        }

        return step;
    }
}