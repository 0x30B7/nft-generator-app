package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProcessorStepService {

    private final Map<String, ProcessorStepProvider> providerRegistry = new HashMap<>();

    @PostConstruct
    private void onPostConstruct() {
        registerProviders();
    }

    public void registerProviders() {
        providerRegistry.put("color-fill", ColorFillStepProvider::provide);
        providerRegistry.put("smooth-color-fill", SmoothColorFillStepProvider::provide);
        providerRegistry.put("blur", BlurStepProvider::provide);
        providerRegistry.put("brighten", BrightenStepProvider::provide);
        providerRegistry.put("grayscale-color", GrayscaleColorStepProvider::provide);
        providerRegistry.put("horizontal-flip", HorizontalFlipStepProvider::provide);
        providerRegistry.put("vertical-flip", VerticalFlipStepProvider::provide);
        providerRegistry.put("rotate-clockwise", RotateClockwiseStepProvider::provide);
    }

    public ImageProcessorStep getStep(String type, Map<String, Object> properties, ProcessorStepResourceContext context) {
        ProcessorStepProvider provider = providerRegistry.get(type);

        if (provider == null)
            return null;

        return provider.provide(properties, context);
    }

}
