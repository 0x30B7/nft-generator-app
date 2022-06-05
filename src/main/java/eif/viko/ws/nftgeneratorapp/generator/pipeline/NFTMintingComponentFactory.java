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

/**
 * Class containing NFT image processing pipeline component providers
 */
@Component
public class NFTMintingComponentFactory {

    @Autowired
    private ProcessorStepResourceService resourceService;
    @Autowired
    private ProcessorStepService stepService;

    /**
     * Construct a resource context from the given resource descriptors
     *
     * @param resources The given resource descriptors
     * @return The context containing the created resources
     * @throws Exception if an error occurs during the process, such as the resource with
     *                   the given type being not found or a resource property validation error
     */
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

    /**
     * Construct an image processor step from the given step descriptor and the available resources
     *
     * @param resourceContext The available resources
     * @param step            The image processor step descriptor
     * @return The created step instance
     * @throws Exception if an error occurs during the process, such as the step with
     *                   the given type being not found or a step property validation error
     */
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
