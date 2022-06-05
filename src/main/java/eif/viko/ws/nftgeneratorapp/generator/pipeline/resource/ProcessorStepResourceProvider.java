package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import java.util.Map;

/**
 * Interface representing a {@link ProcessorStepResource>} factory signature
 */
@FunctionalInterface
public interface ProcessorStepResourceProvider {

    /**
     * Create an image processor step resource instance with the given properties
     *
     * @param properties The given properties
     * @return The created image processor step resource instance
     */
    ProcessorStepResource<?> provide(Map<String, Object> properties);

}
