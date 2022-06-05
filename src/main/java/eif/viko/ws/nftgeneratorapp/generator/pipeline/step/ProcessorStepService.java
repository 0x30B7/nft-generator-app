package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.BlurStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.BrightenStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.ColorFillStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.GrayscaleColorStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.HorizontalFlipStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.NegativeColorFillStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.RotateClockwiseStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.SmoothColorFillStepProvider;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.VerticalFlipStepProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Class storing {@link ProcessorStepProvider} type step providers and constructs
 * {@link ImageProcessorStep} instances
 */
@Service
public class ProcessorStepService {

    private final Map<String, ProcessorStepProvider> providerRegistry = new HashMap<>();

    @PostConstruct
    private void onPostConstruct() {
        registerProviders();
    }

    /**
     * Registers all available image processor step providers
     */
    public void registerProviders() {
        providerRegistry.put("color-fill", ColorFillStepProvider::provide);
        providerRegistry.put("smooth-color-fill", SmoothColorFillStepProvider::provide);
        providerRegistry.put("blur", BlurStepProvider::provide);
        providerRegistry.put("brighten", BrightenStepProvider::provide);
        providerRegistry.put("grayscale-color", GrayscaleColorStepProvider::provide);
        providerRegistry.put("horizontal-flip", HorizontalFlipStepProvider::provide);
        providerRegistry.put("vertical-flip", VerticalFlipStepProvider::provide);
        providerRegistry.put("rotate-clockwise", RotateClockwiseStepProvider::provide);
        providerRegistry.put("negative-color-fill-step", NegativeColorFillStepProvider::provide);
    }

    /**
     * Queries an image processor step provider by its name and if found, constructs the step using the given
     * properties and resources
     *
     * @param type       The type of the processor step to create
     * @param properties The given properties
     * @param context    The given resources context
     * @return The created processor step instance
     */
    public ImageProcessorStep getStep(String type, Map<String, Object> properties, ProcessorStepResourceContext context) {
        ProcessorStepProvider provider = providerRegistry.get(type);

        if (provider == null)
            return null;

        return provider.provide(properties, context);
    }

}
