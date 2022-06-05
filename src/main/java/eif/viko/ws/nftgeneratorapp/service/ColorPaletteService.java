package eif.viko.ws.nftgeneratorapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ColorPaletteService {

    public static final int MAX_COLORS_PER_FETCH = 5;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectReader objectReader = objectMapper.reader();
    private final ObjectWriter objectWriter = objectMapper.writer();

    public List<Color> fetchPalette() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://colormind.io/api/"))
                    .POST(HttpRequest.BodyPublishers.ofString(
                            objectWriter.writeValueAsString(Map.of("model", "default"))))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> responseJson = objectReader.readValue(response.body(), Map.class);
            List<List<Integer>> result = (List<List<Integer>>) responseJson.get("result");

            List<Color> colors = new ArrayList<>();
            for (List<Integer> colorRGB : result) {
                colors.add(new Color(colorRGB.get(0), colorRGB.get(1), colorRGB.get(2)));
            }

            return colors;
        } catch (IOException | InterruptedException e) {
            System.err.println("Unsuccessful color palette service endpoint call: " + e.getMessage());
            return null;
        }
    }

}
