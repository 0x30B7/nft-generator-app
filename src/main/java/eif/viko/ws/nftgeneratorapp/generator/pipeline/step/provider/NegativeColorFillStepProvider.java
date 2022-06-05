package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.NegativeColorFillStep;

import java.util.Map;

/**
 * Class providing {@link NegativeColorFillStep} instances
 */
public class NegativeColorFillStepProvider {

    /**
     * Creates a negative color image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static NegativeColorFillStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        NegativeColorFillStep step = new NegativeColorFillStep();

        // no data to retrieve

        return step;
    }
}