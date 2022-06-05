package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;


import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider.ColorFillStepProvider;
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
    }

    public ImageProcessorStep getStep(String stepName, Map<String, Object> properties, ProcessorStepResourceContext context) {
        ProcessorStepProvider provider = providerRegistry.get(stepName);

        if (provider == null)
            return null;

        return provider.provide(properties, context);
    }

}
