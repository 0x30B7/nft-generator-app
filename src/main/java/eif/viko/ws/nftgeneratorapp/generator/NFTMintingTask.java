package eif.viko.ws.nftgeneratorapp.generator;

import eif.viko.ws.nftgeneratorapp.service.ImageService;

import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NFTMintingTask implements Runnable {

    private final Artifact artifact;
    private final ImageService imageService;

    public NFTMintingTask(Artifact artifact, ImageService imageService) {
        this.artifact = artifact;
        this.imageService = imageService;
    }

    @Override
    public void run() {
        System.out.println("Commencing NFT minting process...");

        BufferedImage finalImage = new BufferedImage(artifact.getWidth(), artifact.getHeight(), BufferedImage.TYPE_INT_ARGB);

        List<ArtifactLayer> orderedLayers = artifact.getLayers().stream()
                .sorted(Comparator.comparing(ArtifactLayer::getZOrder))
                .toList();

        for (ArtifactLayer orderedLayer : orderedLayers) {
            BufferedImage layerImage;

            try {
                Optional<BufferedImage> optLayerImage = imageService.getImageById(orderedLayer.getImageId());

                if (optLayerImage.isEmpty()) {
                    System.err.println("Layer id " + orderedLayer.getImageId() + " is not found.");
                    continue;
                }

                layerImage = optLayerImage.get();
            } catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }

            // TODO: per-layer post-processing?

            int startX = orderedLayer.getXOffset();
            int startY = orderedLayer.getYOffset();
            int endX = Math.min(startX + orderedLayer.getWidth(), finalImage.getWidth());
            int endY = Math.min(startY + orderedLayer.getHeight(), finalImage.getHeight());

            for (int x = startX, xx = 0; x < endX; x++, xx++) {
                for (int y = startY, yy = 0; y < endY; y++, yy++) {
                    int color = layerImage.getRGB(xx, yy);

                    if (color == 0) {
                        continue;
                    }

                    finalImage.setRGB(x, y, color);
                }
            }
        }

        // TODO: final image post-processing

        try {
            artifact.setArtifactImageId(imageService.saveImage(finalImage));
            System.out.println("Artifact complete! (" + artifact.getArtifactImageId() + ".png)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
