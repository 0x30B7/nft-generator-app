package eif.viko.ws.nftgeneratorapp.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.awt.*;

@Service
public class ColorPaletteService {

    public static final int MAX_COLORS_PER_FETCH = 5;

    public List<Color> fetchPalette() {
        // TODO: make http call and parse response
        return Collections.emptyList(); // replace with result
    }

}
