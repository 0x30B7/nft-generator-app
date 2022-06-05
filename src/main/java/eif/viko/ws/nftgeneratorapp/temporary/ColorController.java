package eif.viko.ws.nftgeneratorapp.temporary;

import eif.viko.ws.nftgeneratorapp.service.ColorPaletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class containing endpoint for the <b>/api/v1/colors</b> endpoint subset
 */
@RestController
@RequestMapping("/api/v1/colors")
public class ColorController {

    @Autowired
    private ColorPaletteService colorPaletteService;

    @GetMapping
    public ResponseEntity<?> fetchColor() {
        return ResponseEntity.ok(colorPaletteService.fetchPalette().stream()
                .map(next -> Arrays.asList(next.getRed(), next.getGreen(), next.getBlue()))
                .collect(Collectors.toList()));
    }

}