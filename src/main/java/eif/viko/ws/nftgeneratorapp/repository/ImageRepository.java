package eif.viko.ws.nftgeneratorapp.repository;

import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository {

    List<Integer> getLayerImageIds() throws Exception;

    Optional<BufferedImage> getLayerImageById(int imageId) throws Exception;

    int saveLayerImage(BufferedImage image) throws Exception;

    List<Integer> getNFTImageIds() throws Exception;

    Optional<BufferedImage> getNFTImageById(int imageId) throws Exception;

    int saveNFTImage(BufferedImage image) throws Exception;

}
