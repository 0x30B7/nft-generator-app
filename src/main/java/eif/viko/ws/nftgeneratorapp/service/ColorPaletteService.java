package eif.viko.ws.nftgeneratorapp.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.*;

@Service
public class ColorPaletteService {

    public static final int MAX_COLORS_PER_FETCH = 5;

    public List<Color> fetchPalette() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://colormind.io/api/"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\"model\":\"default\"}")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            responseString = responseString.replace("{\"result\":", "");
            responseString = responseString.replace("}\n", "");
            responseString = responseString.replace("[", "");
            responseString = responseString.replace("]", "");
            var valueArray = responseString.split(",");
            List<Color> colors = new ArrayList<>();
            for (int i = 0; i < MAX_COLORS_PER_FETCH * 3; i += 3) {
                Color color = new Color(Integer.parseInt(valueArray[i]), Integer.parseInt(valueArray[i + 1]), Integer.parseInt(valueArray[i + 2]), 128);
                colors.add(color);
            }
            return colors;
        } catch (IOException | InterruptedException e) {
            System.out.println("Unsuccessful color palette service endpoint call; " + e.getMessage());
            return null;
        }
    }

}
