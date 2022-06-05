package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.GrayscaleColorStep;

import java.util.Map;

/**
 * Class providing {@link GrayscaleColorStep} instances
 */
public class GrayscaleColorStepProvider {

    /**
     * Creates a grayscale image processing step instance from the given properties
     *
     * @param properties The given properties
     * @param ctx        The current processor step resource context
     * @return The new processor step instance
     */
    public static GrayscaleColorStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        GrayscaleColorStep step = new GrayscaleColorStep();

        // no data to retrieve

        return step;
    }
}