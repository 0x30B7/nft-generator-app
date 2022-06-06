package eif.viko.ws.nftgeneratorapp.temporary;

import eif.viko.ws.nftgeneratorapp.service.ColorPaletteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Fetches a color palette of " + ColorPaletteService.MAX_COLORS_PER_FETCH + " colors")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created the color palette"
            )
    })
    @GetMapping
    public ResponseEntity<?> fetchColor() {
        return ResponseEntity.ok(colorPaletteService.fetchPalette().stream()
                .map(next -> Arrays.asList(next.getRed(), next.getGreen(), next.getBlue()))
                .collect(Collectors.toList()));
    }

}