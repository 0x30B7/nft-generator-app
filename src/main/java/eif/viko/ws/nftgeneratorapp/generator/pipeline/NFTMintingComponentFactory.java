package eif.viko.ws.nftgeneratorapp.generator.pipeline;

import eif.viko.ws.nftgeneratorapp.controller.model.NFTProcessorStep;
import eif.viko.ws.nftgeneratorapp.controller.model.NFTResource;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResource;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceService;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ProcessorStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NFTMintingComponentFactory {

    @Autowired
    private ProcessorStepResourceService resourceService;
    @Autowired
    private ProcessorStepService stepService;

    public ProcessorStepResourceContext createResourceContext(List<NFTResource> resources) throws Exception {
        ProcessorStepResourceContext.Builder resourceCtxBuilder = ProcessorStepResourceContext.builder();

        for (NFTResource entry : resources) {
            ProcessorStepResource<?> resource = resourceService.getResource(entry.getType(), entry.getProperties());

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

    public ImageProcessorStep createProcessorStep(ProcessorStepResourceContext resourceContext,
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
