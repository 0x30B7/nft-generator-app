package eif.viko.ws.nftgeneratorapp.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

/**
 * Service providing image persistence management functionality
 */
@Service
public interface ImageService {

    /**
     * Obtains a list of ids of all existing layer images
     *
     * @return The list of image ids
     * @throws Exception if an error occurs during the operation
     */
    List<Integer> getLayerImageIds() throws Exception;

    /**
     * Obtains an {@link Optional} image associated with the given layer image id
     *
     * @param imageId The given layer image id
     * @return An optional layer image
     * @throws Exception if an error occurs during the operation
     */
    Optional<BufferedImage> getLayerImageById(int imageId) throws Exception;

    /**
     * Persists the given layer image, associating it with an image id
     *
     * @param image The layer image to persist
     * @return The image id associated with the given layer image
     * @throws Exception if an error occurs during the operation
     */
    int saveLayerImage(BufferedImage image) throws Exception;

    /**
     * Obtains a list of ids of all existing NFT images
     *
     * @return The list of image ids
     * @throws Exception if an error occurs during the operation
     */
    List<Integer> getNFTImageIds() throws Exception;

    /**
     * Obtains an {@link Optional} image associated with the given NFT image id
     *
     * @param imageId The given NFT image id
     * @return An optional NFT image
     * @throws Exception if an error occurs during the operation
     */
    Optional<BufferedImage> getNFTImageById(int imageId) throws Exception;

    /**
     * Persists the given NFT image, associating it with an image id
     *
     * @param image The NFT image to persist
     * @return The image id associated with the given NFT image
     * @throws Exception if an error occurs during the operation
     */
    int saveNFTImage(BufferedImage image) throws Exception;

    /**
     * Reserves an image id for later use in image persistance operations
     * with a particular image id ({@link ImageService#saveNFTImage(BufferedImage, int)})
     *
     * @return The reserved image id
     * @throws Exception if an error occurs during the operation
     */
    int reserveImageId() throws Exception;

    /**
     * Persists the given NFT image, associating it with the given image id
     *
     * @param image   The NFT image to persist
     * @param imageId The given image id
     * @return The given image id (to be used in a chaining-like pattern)
     * @throws Exception if an error occurs during the operation
     */
    int saveNFTImage(BufferedImage image, int imageId) throws Exception;


}
