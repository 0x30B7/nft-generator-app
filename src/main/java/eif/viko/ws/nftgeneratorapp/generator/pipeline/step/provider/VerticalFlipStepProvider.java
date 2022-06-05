package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.VerticalFlipStep;

import java.util.Map;

/**
 * Class providing {@link VerticalFlipStep} instances
 */
public class VerticalFlipStepProvider {

    /**
     * Creates a vertical flip image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static VerticalFlipStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        VerticalFlipStep step = new VerticalFlipStep();

        // no data to retrieve

        return step;
    }
}