package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.provider.ColorPaletteProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Class storing {@link ProcessorStepResourceProvider} type resource providers and constructs
 * {@link ProcessorStepResource} instances
 */
@Service
public class ProcessorStepResourceService {

    private final Map<String, ProcessorStepResourceProvider> providerRegistry = new HashMap<>();

    @PostConstruct
    private void onPostConstruct() {
        registerProviders();
    }

    /**
     * Registers all available image processor step resource providers
     */
    public void registerProviders() {
        providerRegistry.put("color-palette", ColorPaletteProvider::provide);
    }

    /**
     * Queries an image processor step resource provider by its name and if found, constructs the resource using
     * the given properties
     *
     * @param type       The type of the resource to create
     * @param properties The given properties
     * @param <T>        The data type of the value that the resource is to provide
     * @param <R>        The data type of the resource implementation
     * @return The created resource instance
     */
    public <T, R extends ProcessorStepResource<T>> R getResource(String type, Map<String, Object> properties) {
        ProcessorStepResourceProvider provider = providerRegistry.get(type);

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
