package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import java.util.HashMap;
import java.util.Map;

public class ProcessorStepResourceContext {

    private final Map<Integer, ProcessorStepResource<?>> resources;

    public ProcessorStepResourceContext(Map<Integer, ProcessorStepResource<?>> resources) {
        this.resources = resources;
    }

    public <T> ProcessorStepResource<T> getResource(int resourceId) {
        return (ProcessorStepResource<T>) resources.get(resourceId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Map<Integer, ProcessorStepResource<?>> resources = new HashMap<>();

        public Builder addResource(int resourceId, ProcessorStepResource<?> resource) {
            resources.put(resourceId, resource);
            return this;
        }

        public ProcessorStepResourceContext build() {
            return new ProcessorStepResourceContext(resources);
        }

    }

}
