package eif.viko.ws.nftgeneratorapp.generator.util;

import java.awt.*;
import java.util.List;

/**
 * Class containing a color parsing utility
 */
public class ColorUtil {

    private ColorUtil() { }

    /**
     * Attempts to parse a color from various forms of input, including an RGB numerical value,
     * a hex, octal string-numerical value, a list of RGB (and optionally A) color component lists
     *
     * @param input The given color input
     * @return The parsed color, or {@code null} if the parsing has failed
     */
    public static Color parseColor(Object input) {
        if (input instanceof Number num) {
            return new Color(num.intValue());
        } else if (input instanceof String str) {
            return Color.decode(str);
        } else if (input instanceof List<?> list) {
            if (list.size() >= 3) {
                int r, g, b, a = 255;

                try {
                    r = list.get(0) instanceof Number num ? num.intValue() : Integer.decode(list.get(0).toString());
                    g = list.get(1) instanceof Number num ? num.intValue() : Integer.decode(list.get(1).toString());
                    b = list.get(2) instanceof Number num ? num.intValue() : Integer.decode(list.get(2).toString());

                    if (list.size() >= 4) {
                        a = list.get(3) instanceof Number num ? num.intValue() : Integer.decode(list.get(3).toString());
                    }

                    return new Color(r, g, b, a);
                } catch (NumberFormatException ex) {
                    return null;
                }
            }
        }

        return null;
    }

}
