package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;

import java.util.Map;

/**
 * Class providing {@link BlurStep} instances
 */
public class BlurStepProvider {

    /**
     * Creates a blur image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static BlurStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        BlurStep step = new BlurStep();

        Object radius = properties.get("radius");
        if (radius instanceof Number num) {
            step.getRadiusProperty().set(num.intValue());
        }

        return step;
    }
}