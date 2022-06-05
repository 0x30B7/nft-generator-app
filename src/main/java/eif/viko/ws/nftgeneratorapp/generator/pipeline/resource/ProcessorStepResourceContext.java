package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Class storing image processing step resources and associating them with
 * reference ids
 */
public class ProcessorStepResourceContext {

    private final Map<Integer, ProcessorStepResource<?>> resources;

    public ProcessorStepResourceContext(Map<Integer, ProcessorStepResource<?>> resources) {
        this.resources = resources;
    }

    /**
     * Gets a resource by the given resource reference id
     *
     * @param resourceId The given reference id
     * @param <T>        The data type of the value that the resource is to provide
     * @return The resource, {@code null} if no resource is found with the given id
     */
    public <T> ProcessorStepResource<T> getResource(int resourceId) {
        return (ProcessorStepResource<T>) resources.get(resourceId);
    }

    /**
     * @return A builder instance of this class
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Utility subclass representing the builder pattern for this class
     */
    public static class Builder {

        private final Map<Integer, ProcessorStepResource<?>> resources = new HashMap<>();

        /**
         * Adds a resource to the context builder, associating it with the given resource id
         *
         * @param resourceId The given resource id
         * @param resource   The given resource
         * @return The builder instance
         */
        public Builder addResource(int resourceId, ProcessorStepResource<?> resource) {
            resources.put(resourceId, resource);
            return this;
        }

        /**
         * @return The constructed {@link ProcessorStepResourceContext} instance
         */
        public ProcessorStepResourceContext build() {
            return new ProcessorStepResourceContext(resources);
        }

    }

}
