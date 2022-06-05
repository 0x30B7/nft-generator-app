package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;

import java.util.Map;

/**
 * Interface representing a {@link ImageProcessorStep} factory signature
 */
@FunctionalInterface
public interface ProcessorStepProvider {

    /**
     * Create an image processor step instance with the given properties and resources
     *
     * @param properties The given properties
     * @param ctx        The given resources context
     * @return The created image processor step instance
     */
    ImageProcessorStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx);

}
