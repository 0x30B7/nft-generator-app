package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource;

import java.util.Map;

public interface ProcessorStepResourceProvider {

    ProcessorStepResource<?> provide(Map<String, Object> properties);

}
