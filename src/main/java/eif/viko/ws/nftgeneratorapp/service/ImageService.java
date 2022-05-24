package eif.viko.ws.nftgeneratorapp.service;

import java.awt.image.BufferedImage;
import java.util.Optional;

public interface ImageService {

    Optional<BufferedImage> getImageById(int imageId) throws Exception;

    int saveImage(BufferedImage image) throws Exception;

}
