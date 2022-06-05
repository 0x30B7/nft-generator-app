package eif.viko.ws.nftgeneratorapp.controller;

import eif.viko.ws.nftgeneratorapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/layer-image")
public class LayerImageController {

    @Autowired
    @Qualifier("DiskImageService")
    private ImageService imageService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> uploadLayer(MultipartFile layerImage) {
        String fileName = layerImage.getOriginalFilename();

        if (fileName == null) {
            return ResponseEntity.badRequest().build();
        }

        BufferedImage image;

        try {
            image = ImageIO.read(layerImage.getInputStream());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

        try {
            int imageId = imageService.saveLayerImage(image);
            return ResponseEntity.ok(Map.of("id", imageId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchLayer(@PathVariable int id) {
        InputStream is;
        int payloadSize;

        try {
            Optional<BufferedImage> optImage = imageService.getLayerImageById(id);

            if (optImage.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(optImage.get(), "png", os);
            byte[] data = os.toByteArray();
            payloadSize = data.length;
            is = new ByteArrayInputStream(data);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", id + ".png"));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(payloadSize)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(is));
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<?> fetchAllLayers() throws Exception {
        return ResponseEntity.ok(imageService.getLayerImageIds()
                .stream().map(next -> Map.of(
                        "id", next,
                        "url", "/api/v1/layer-image/fetch/" + next
                )).collect(Collectors.toList()));
    }

}
