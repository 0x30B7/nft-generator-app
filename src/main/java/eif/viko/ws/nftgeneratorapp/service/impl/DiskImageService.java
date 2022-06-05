package eif.viko.ws.nftgeneratorapp.service.impl;

import eif.viko.ws.nftgeneratorapp.repository.ImageRepository;
import eif.viko.ws.nftgeneratorapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Qualifier("DiskImageService")
public class DiskImageService implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Integer> getLayerImageIds() throws Exception {
        return imageRepository.getLayerImageIds();
    }

    @Override
    public Optional<BufferedImage> getLayerImageById(int imageId) throws Exception {
        return imageRepository.getLayerImageById(imageId);
    }

    @Override
    public int saveLayerImage(BufferedImage image) throws Exception {
        return imageRepository.saveLayerImage(image);
    }

    @Override
    public List<Integer> getNFTImageIds() throws Exception {
        return imageRepository.getNFTImageIds();
    }

    @Override
    public Optional<BufferedImage> getNFTImageById(int imageId) throws Exception {
        return imageRepository.getNFTImageById(imageId);
    }

    @Override
    public int saveNFTImage(BufferedImage image) throws Exception {
        return imageRepository.saveNFTImage(image);
    }

    @Override
    public int reserveImageId() throws Exception {
        return imageRepository.reserveImageId();
    }

    @Override
    public int saveNFTImage(BufferedImage image, int imageId) throws Exception {
        return imageRepository.saveNFTImage(image, imageId);
    }

}
