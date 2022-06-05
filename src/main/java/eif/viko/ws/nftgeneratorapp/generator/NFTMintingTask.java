package eif.viko.ws.nftgeneratorapp.generator;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.service.ImageService;

import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Class representing an NFT minting task
 */
public class NFTMintingTask implements Runnable {

    private final int taskId;
    private final Artifact artifact;
    private final ImageService imageService;
    private final Queue<List<ImageProcessorStep>> layerProcessorSteps;
    private final List<ImageProcessorStep> postProcessorSteps;
    private final NFTMintingCallback callback;

    private int finalImageId;
    private BufferedImage finalImage;

    public NFTMintingTask(int taskId, int finalImageId, Artifact artifact, ImageService imageService,
                          Queue<List<ImageProcessorStep>> layerProcessorSteps,
                          List<ImageProcessorStep> postProcessorSteps, NFTMintingCallback callback) {
        this.taskId = taskId;
        this.finalImageId = finalImageId;
        this.artifact = artifact;
        this.imageService = imageService;
        this.layerProcessorSteps = layerProcessorSteps;
        this.postProcessorSteps = postProcessorSteps;
        this.callback = callback;
    }

    /**
     * Execute the NFT minting process
     */
    @Override
    public void run() {
        System.out.println("Commencing NFT minting process...");

        // Initialize final NFT image
        this.finalImage = new BufferedImage(artifact.getWidth(), artifact.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Order layers according to the configured 'z' order
        List<ArtifactLayer> orderedLayers = artifact.getLayers().stream()
                .sorted(Comparator.comparing(ArtifactLayer::getZOrder))
                .toList();

        for (ArtifactLayer orderedLayer : orderedLayers) {
            BufferedImage layerImage;

            try {
                Optional<BufferedImage> optLayerImage = imageService.getLayerImageById(orderedLayer.getImageId());

                if (optLayerImage.isEmpty()) {
                    callback.onError(new Exception("Could not load image id '" + orderedLayer.getImageId() + "'."));
                    continue;
                }

                layerImage = optLayerImage.get();
            } catch (Exception ex) {
                callback.onError(new Exception("An error occurred whilst loading layer image id '" + orderedLayer.getImageId() + "': " + ex.getMessage()));
                continue;
            }

            List<ImageProcessorStep> processorSteps = layerProcessorSteps.poll();

            if (processorSteps == null) {
                callback.onError(new Exception("Internal issue - layer processor step list queue size and layer count mismatch"));
                return;
            }

            int stepIndex = 0;
            for (ImageProcessorStep step : processorSteps) {
                try {
                    step.onProcess(layerImage);
                    stepIndex++;
                } catch (Exception ex) {
                    callback.onError(new Exception("Could not perform layer processor step for image id '" + orderedLayer.getImageId() + "', step " + stepIndex));
                    return;
                }
            }

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

        int stepIndex = 0;
        for (ImageProcessorStep step : postProcessorSteps) {
            try {
                step.onProcess(finalImage);
                stepIndex++;
            } catch (Exception ex) {
                callback.onError(new Exception("Could not perform final image processor step " + stepIndex));
                return;
            }
        }

        try {
            artifact.setArtifactImageId(imageService.saveNFTImage(finalImage, finalImageId));
            callback.onComplete(artifact);
            System.out.println("Artifact complete! (" + artifact.getArtifactImageId() + ".png)");
        } catch (Exception ex) {
            callback.onError(new Exception("Could not save artifact image: " + ex.getMessage()));
        }
    }

    /**
     * @return The id of the minting task
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @return the NFT minting operation model associated with the minting Task
     */
    public Artifact getArtifact() {
        return artifact;
    }

    public BufferedImage getFinalImage() {
        return finalImage;
    }

}
