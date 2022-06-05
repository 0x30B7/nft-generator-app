package eif.viko.ws.nftgeneratorapp.generator.pipeline.step;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;

import java.util.Map;

public interface ProcessorStepProvider {

    ImageProcessorStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx);

}
