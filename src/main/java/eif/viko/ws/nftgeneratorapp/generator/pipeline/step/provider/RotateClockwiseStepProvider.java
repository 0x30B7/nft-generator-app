package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.RotateClockwiseStep;

import java.util.Map;

/**
 * Class providing {@link RotateClockwiseStep} instances
 */
public class RotateClockwiseStepProvider {

    /**
     * Creates a clockwise rotation image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static RotateClockwiseStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        RotateClockwiseStep step = new RotateClockwiseStep();

        Object times = properties.get("times");
        if (times instanceof Number num) {
            step.getTimesProperty().set(num.intValue());
        }

        return step;
    }
}