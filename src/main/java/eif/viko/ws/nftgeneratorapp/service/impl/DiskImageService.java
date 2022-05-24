package eif.viko.ws.nftgeneratorapp.service.impl;

import eif.viko.ws.nftgeneratorapp.service.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Qualifier("DiskImageService")
public class DiskImageService implements ImageService {

    private final AtomicInteger imageIdGenerator = new AtomicInteger(1);

    @PostConstruct
    public void onPostConstruct() {
        File imageDir = new File("./images");

        if (!imageDir.exists()) {
            imageDir.mkdirs();
            return;
        }

        File[] imageFiles = imageDir.listFiles();

        if (imageFiles == null) {
            return;
        }

        Arrays.stream(imageFiles).max(Comparator.naturalOrder()).ifPresent(file -> {
            String name = file.getName()
                    .replace(".png", "")
                    .replace(".jpeg", "");

            try {
                int nameId = Integer.parseInt(name);
                imageIdGenerator.set(nameId + 1);
            } catch (Exception ignored) { }
        });
    }

    @Override
    public Optional<BufferedImage> getImageById(int imageId) throws Exception {
        File imageDir = new File("./images");

        if (!imageDir.exists()) {
            imageDir.mkdirs();
            return Optional.empty();
        }

        File imageFile = new File(imageDir, imageId + ".png");

        if (!imageFile.exists()) {
            imageFile = new File(imageDir, imageId + ".jpeg");

            if (!imageFile.exists()) {
                return Optional.empty();
            }
        }

        return Optional.of(ImageIO.read(imageFile));
    }

    @Override
    public int saveImage(BufferedImage image) throws Exception {
        File imageDir = new File("./images");

        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        int id = imageIdGenerator.getAndIncrement();
        File imageFile = new File(imageDir, id + ".png");
        ImageIO.write(image, "png", imageFile);

        return id;
    }

}
