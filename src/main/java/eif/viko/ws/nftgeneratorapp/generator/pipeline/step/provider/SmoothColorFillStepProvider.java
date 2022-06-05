package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResource;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl.SmoothColorFillStep;
import eif.viko.ws.nftgeneratorapp.generator.util.ColorUtil;

import java.awt.*;
import java.util.Map;

public class SmoothColorFillStepProvider {

    public static SmoothColorFillStep provide(Map<String, Object> properties, ProcessorStepResourceContext ctx) {
        SmoothColorFillStep step = new SmoothColorFillStep();

        properties.get("radius");
        Object color = properties.get("color");
        if (color instanceof Map<?, ?> map) {
            Object resourceId = map.get("resource");

            if (resourceId instanceof Number num) {
                ProcessorStepResource<?> resource = ctx.getResource(num.intValue());

                if (step.getColorProperty().isCompatibleWith(resource.getResourceValueType())) {
                    step.getColorProperty().set(resource.get());
                }
            }
        } else {
            Color parsedColor = ColorUtil.parseColor(color);

            if (parsedColor != null) {
                step.getColorProperty().set(ColorUtil.parseColor(color));
            }
        }

        return step;
    }

}