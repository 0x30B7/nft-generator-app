package eif.viko.ws.nftgeneratorapp.repository.impl;

import eif.viko.ws.nftgeneratorapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Qualifier("DiskImageRepository")
public class DiskImageRepository implements ImageRepository {

    private final AtomicInteger imageIdGenerator = new AtomicInteger(1);

    private static final String PATH_LAYERS = "./images/layers";
    private static final String PATH_NFT_ARTIFACTS = "./images/nft";

    @PostConstruct
    public void onPostConstruct() {
        for (String path : Arrays.asList(PATH_LAYERS, PATH_NFT_ARTIFACTS)) {
            File imageDir = new File(path);

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

                    if (imageIdGenerator.get() < nameId) {
                        imageIdGenerator.set(nameId + 1);
                    }
                } catch (Exception ignored) { }
            });
        }
    }

    @Override
    public List<Integer> getLayerImageIds() throws Exception {
        return getImageIds(PATH_LAYERS);
    }

    @Override
    public Optional<BufferedImage> getLayerImageById(int imageId) throws Exception {
        return getImageById(PATH_LAYERS, imageId);
    }

    @Override
    public int saveLayerImage(BufferedImage image) throws Exception {
        return saveImage(PATH_LAYERS, image);
    }

    @Override
    public List<Integer> getNFTImageIds() throws Exception {
        return getImageIds(PATH_NFT_ARTIFACTS);
    }

    @Override
    public Optional<BufferedImage> getNFTImageById(int imageId) throws Exception {
        return getImageById(PATH_NFT_ARTIFACTS, imageId);
    }

    @Override
    public int saveNFTImage(BufferedImage image) throws Exception {
        return saveImage(PATH_NFT_ARTIFACTS, image);
    }

    @Override
    public int reserveImageId() throws Exception {
        return imageIdGenerator.getAndIncrement();
    }

    @Override
    public int saveNFTImage(BufferedImage image, int imageId) throws Exception {
        return saveImage(PATH_NFT_ARTIFACTS, image, imageId);
    }

    public Optional<BufferedImage> getImageById(String path, int imageId) throws Exception {
        File imageDir = new File(path);

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

    public int saveImage(String path, BufferedImage image) throws Exception {
        return saveImage(path, image, imageIdGenerator.getAndIncrement());
    }

    public int saveImage(String path, BufferedImage image, int imageId) throws Exception {
        File imageDir = new File(path);

        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        File imageFile = new File(imageDir, imageId + ".png");
        ImageIO.write(image, "png", imageFile);

        return imageId;
    }

    private List<Integer> getImageIds(String path) throws Exception {
        File[] imageDir = new File(path).listFiles();

        if (imageDir == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(imageDir)
                .map(File::getName)
                .filter(next -> next.endsWith(".png") || next.endsWith(".jpg"))
                .map(next -> Integer.parseInt(next.split("\\.")[0]))
                .collect(Collectors.toList());
    }

}
