package eif.viko.ws.nftgeneratorapp.generator.pipeline;

import eif.viko.ws.nftgeneratorapp.controller.model.NFTLayer;
import eif.viko.ws.nftgeneratorapp.controller.model.NFTProcessorStep;
import eif.viko.ws.nftgeneratorapp.controller.model.NFTResource;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResource;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceService;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ProcessorStepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class PipelineFactory {

    @Autowired
    private static ProcessorStepResourceService resourceService;
    @Autowired
    private static ProcessorStepService stepService;

    public static ProcessorStepResourceContext createResourceContext(List<NFTResource> resources) throws Exception {
        ProcessorStepResourceContext.Builder resourceCtxBuilder = ProcessorStepResourceContext.builder();

        for (NFTResource entry : resources) {
            ProcessorStepResource<?> resource = resourceService.getResource(entry.getType(), entry.getOptions());

            if (resource == null) {
                throw new Exception("Resource '" + entry.getType() + "' does not exist.");
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
                                                               NFTProcessorStep step) throws Exception {
        ImageProcessorStep processorStep = stepService.getStep(step.getType(), step.getProperties(), resourceContext);

        if (processorStep == null) {
            throw new Exception("Step '" + step.getType() + "' does not exist.");
        }

        try {
            processorStep.validateStep();
        } catch (Exception ex) {
            throw new Exception("Step validation error: " + ex.getMessage());
        }

        return processorStep;
    }


}
