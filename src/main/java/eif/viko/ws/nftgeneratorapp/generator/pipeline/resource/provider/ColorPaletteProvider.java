package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.provider;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.impl.ColorPalette;
import eif.viko.ws.nftgeneratorapp.generator.util.ColorUtil;
import eif.viko.ws.nftgeneratorapp.service.ColorPaletteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class ColorPaletteProvider {

    @Autowired
    private static ColorPaletteService colorPaletteService;

    public static ColorPalette provide(Map<String, Object> properties) {
        ColorPalette resource = new ColorPalette();

        Object colors = properties.get("colors");
        if (colors instanceof Collection<?> col && col.size() > 0) {
            List<Color> palette = new ArrayList<>();

            boolean foundBadColor = false;
            for (Object colorInput : col) {
                Color color = ColorUtil.parseColor(colorInput);

                if (color == null) {
                    foundBadColor = true;
                    break;
                }

                palette.add(color);
            }

            if (!foundBadColor) {
                resource.setColors(palette.toArray(new Color[0]));
            }
        }

        if (resource.getColors() == null) {
            Object generatePalette = properties.get("generate-palette");
            if (generatePalette instanceof Number num) {
                resource.setColors(colorPaletteService.fetchPalette().stream()
                        .limit(Math.min(num.intValue(), ColorPaletteService.MAX_COLORS_PER_FETCH))
                        .toArray(Color[]::new));
            }
        }

        return resource;
    }

}
