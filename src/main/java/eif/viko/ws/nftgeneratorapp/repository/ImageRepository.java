package eif.viko.ws.nftgeneratorapp.repository;

import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

/**
 * Repository providing image persistence functionality
 */
@Repository
public interface ImageRepository {

    /**
     * Queries a list of ids of all existing layer images
     *
     * @return The list of image ids
     * @throws Exception if an error occurs during the operation
     */
    List<Integer> getLayerImageIds() throws Exception;

    /**
     * Queries an {@link Optional} image associated with the given layer image id
     *
     * @param imageId The given layer image id
     * @return An optional layer image
     * @throws Exception if an error occurs during the operation
     */
    Optional<BufferedImage> getLayerImageById(int imageId) throws Exception;

    /**
     * Stores the given layer image, associating it with an image id
     *
     * @param image The layer image to store
     * @return The image id associated with the given layer image
     * @throws Exception if an error occurs during the operation
     */
    int saveLayerImage(BufferedImage image) throws Exception;

     /**
     * Queries a list of ids of all existing NFT images
     *
     * @return The list of image ids
     * @throws Exception if an error occurs during the operation
     */
    List<Integer> getNFTImageIds() throws Exception;

    /**
     * Queries an {@link Optional} image associated with the given NFT image id
     *
     * @param imageId The given NFT image id
     * @return An optional NFT image
     * @throws Exception if an error occurs during the operation
     */
    Optional<BufferedImage> getNFTImageById(int imageId) throws Exception;

    /**
     * Stores the given NFT image, associating it with an image id
     *
     * @param image The NFT image to store
     * @return The image id associated with the given NFT image
     * @throws Exception if an error occurs during the operation
     */
    int saveNFTImage(BufferedImage image) throws Exception;

    /**
     * Reserves an image id for later use in image storage operations
     * with a particular image id ({@link ImageRepository#saveNFTImage(BufferedImage, int)})
     *
     * @return The reserved image id
     * @throws Exception if an error occurs during the operation
     */
    int reserveImageId() throws Exception;

    /**
     * Stores the given NFT image, associating it with the given image id
     *
     * @param image   The NFT image to store
     * @param imageId The given image id
     * @return The given image id (to be used in a chaining-like pattern)
     * @throws Exception if an error occurs during the operation
     */
    int saveNFTImage(BufferedImage image, int imageId) throws Exception;


}
