package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.HorizontalFlipStep;

import java.util.Map;

/**
 * Class providing {@link HorizontalFlipStep} instances
 */
public class HorizontalFlipStepProvider {

    /**
     * Creates a horizontal flip image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static HorizontalFlipStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        HorizontalFlipStep step = new HorizontalFlipStep();

        // no data to retrieve

        return step;
    }
}