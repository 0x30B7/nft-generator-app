package eif.viko.ws.nftgeneratorapp.service.impl;

import eif.viko.ws.nftgeneratorapp.repository.ImageRepository;
import eif.viko.ws.nftgeneratorapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

/**
 * Class representing an image persistence service with the disk storage strategy
 */
@Service
@Qualifier("DiskImageService")
public class DiskImageService implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getLayerImageIds() throws Exception {
        return imageRepository.getLayerImageIds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BufferedImage> getLayerImageById(int imageId) throws Exception {
        return imageRepository.getLayerImageById(imageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveLayerImage(BufferedImage image) throws Exception {
        return imageRepository.saveLayerImage(image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getNFTImageIds() throws Exception {
        return imageRepository.getNFTImageIds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<BufferedImage> getNFTImageById(int imageId) throws Exception {
        return imageRepository.getNFTImageById(imageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveNFTImage(BufferedImage image) throws Exception {
        return imageRepository.saveNFTImage(image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int reserveImageId() throws Exception {
        return imageRepository.reserveImageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveNFTImage(BufferedImage image, int imageId) throws Exception {
        return imageRepository.saveNFTImage(image, imageId);
    }

}
