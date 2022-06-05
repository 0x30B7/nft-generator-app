package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.provider.ColorPaletteProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProcessorStepResourceService {

    private final Map<String, ProcessorStepResourceProvider> providerRegistry = new HashMap<>();

    @PostConstruct
    private void onPostConstruct() {
        registerProviders();
    }

    public void registerProviders() {
        providerRegistry.put("color-palette", ColorPaletteProvider::provide);
    }

    public <T, R extends ProcessorStepResource<T>> R getResource(String resourceName, Map<String, Object> properties) {
        ProcessorStepResourceProvider provider = providerRegistry.get(resourceName);

        if (provider == null)
            return null;

        R resource = (R) provider.provide(properties);

        if (!resource.isIdSet()) {
            Object id = properties.get("id");
            if (id instanceof Number num) {
                resource.setId(num.intValue());
            }
        }

        return resource;
    }

}
