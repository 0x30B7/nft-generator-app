package eif.viko.ws.nftgeneratorapp.repository.impl;

import eif.viko.ws.nftgeneratorapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Class representing an image repository with the disk storage strategy
 */
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
                } catch (Exception ignored) {
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getLayerImageIds() throws Exception {
        return getImageIds(PATH_LAYERS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BufferedImage> getLayerImageById(int imageId) throws Exception {
        return getImageById(PATH_LAYERS, imageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveLayerImage(BufferedImage image) throws Exception {
        return saveImage(PATH_LAYERS, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getNFTImageIds() throws Exception {
        return getImageIds(PATH_NFT_ARTIFACTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BufferedImage> getNFTImageById(int imageId) throws Exception {
        return getImageById(PATH_NFT_ARTIFACTS, imageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveNFTImage(BufferedImage image) throws Exception {
        return saveImage(PATH_NFT_ARTIFACTS, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int reserveImageId() throws Exception {
        return imageIdGenerator.getAndIncrement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveNFTImage(BufferedImage image, int imageId) throws Exception {
        return saveImage(PATH_NFT_ARTIFACTS, image, imageId);
    }

    /**
     * Finds and loads an image from the given file system directory, associated with
     * the given image id
     *
     * @param path    The path of the file system directory holding the image file
     * @param imageId The id/name associated with the image
     * @return An optional loaded image
     * @throws Exception If an error occurs during accessing of the file system
     */
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

    /**
     * See {@link DiskImageRepository#saveImage(String, BufferedImage, int)}
     */
    public int saveImage(String path, BufferedImage image) throws Exception {
        return saveImage(path, image, imageIdGenerator.getAndIncrement());
    }

    /**
     * Saves the given image to disk at the given file system path, associating it/naming
     * the image file with the given image id
     *
     * @param path    The given file system path
     * @param image   The given image
     * @param imageId The given image id
     * @return The given image id (to be used in a chaining-like pattern)
     * @throws Exception If an error occurs during file saving
     */
    public int saveImage(String path, BufferedImage image, int imageId) throws Exception {
        File imageDir = new File(path);

        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        File imageFile = new File(imageDir, imageId + ".png");
        ImageIO.write(image, "png", imageFile);

        return imageId;
    }

    /**
     * Transforms a directory of id-associated image files and extracts their "ids" from
     * their file names
     *
     * @param path The file system path to traverse
     * @return The image id list obtained from the files of the traversed directory
     * @throws Exception If an error occurs during accessing of the file system
     */
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
