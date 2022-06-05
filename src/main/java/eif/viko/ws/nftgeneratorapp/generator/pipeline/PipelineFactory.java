package eif.viko.ws.nftgeneratorapp.generator.pipeline;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResource;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceService;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ProcessorStepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class PipelineFactory {

    @Autowired
    private static ProcessorStepResourceService resourceService;
    @Autowired
    private static ProcessorStepService stepService;

    public static ProcessorStepResourceContext createResourceContext(Map<String, Object> config) throws Exception {
        ProcessorStepResourceContext.Builder resourceCtxBuilder = ProcessorStepResourceContext.builder();

        for (Map.Entry<String, Object> entry : config.entrySet()) {
            String resourceName = entry.getKey();
            Map<String, Object> resourceProps = (Map<String, Object>) entry.getValue();

            ProcessorStepResource<?> resource = resourceService.getResource(resourceName, resourceProps);

            if (resource == null) {
                throw new Exception("Resource '" + resourceName + "' does not exist.");
            }

            try {
                resource.validateResource();
            } catch (Exception ex) {
                throw new Exception("Resource validation error: " + ex.getMessage());
            }

            resourceCtxBuilder.addResource(resource.getId(), resource);
        }

        return resourceCtxBuilder.build();
    }

    public static ImageProcessorStep createProcessorStep(ProcessorStepResourceContext resourceContext,
                                                               Map<String, Object> config) throws Exception {
        Object name = config.get("name");

        if (!(name instanceof String)) {
            throw new IllegalArgumentException("Missing step 'name' field");
        }

        ImageProcessorStep step = stepService.getStep((String) name, config, resourceContext);

        if (step == null) {
            throw new Exception("Step '" + name + "' does not exist.");
        }

        try {
            step.validateStep();
        } catch (Exception ex) {
            throw new Exception("Step validation error: " + ex.getMessage());
        }

        return step;
    }


}
