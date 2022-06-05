package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.BlurStep;

import java.util.Map;

public class BlurStepProvider {

    public static BlurStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        BlurStep step = new BlurStep();

        Object radius = properties.get("radius");
        if (radius instanceof Number num) {
            step.getRadiusProperty().set(num.intValue());
        }
        Object blendAlpha = properties.get("blendAlpha");
        if (blendAlpha instanceof Boolean bool) {
            step.getBlendAlphaProperty().set(bool.booleanValue());
        }

        return step;
    }

}