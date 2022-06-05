package eif.viko.ws.nftgeneratorapp.generator;

import java.util.List;

/**
 * Class representing an NFT minting operation model
 */
public class Artifact {

    private final String title;
    private final int width;
    private final int height;
    private final List<ArtifactLayer> layers;

    private MintingStage stage = MintingStage.IN_QUEUE;
    private int artifactImageId;

    public Artifact(String title, int width, int height, List<ArtifactLayer> layers) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.layers = layers;
    }

    /**
     * @return The title of the artifact
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The width of the artifact image
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the artifact image
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The layers associated with the artifact
     */
    public List<ArtifactLayer> getLayers() {
        return layers;
    }

    /**
     * @return The current minting stage of the model
     */
    public MintingStage getStage() {
        return stage;
    }

    /**
     * Updates the current minting stage of the model
     *
     * @param stage the given minting stage
     */
    public void setStage(MintingStage stage) {
        this.stage = stage;
    }

    /**
     * @return The artifact image id associated with the model
     */
    public int getArtifactImageId() {
        return artifactImageId;
    }

    /**
     * Updates the artifact image id associated with the model
     *
     * @param artifactImageId The given artifact image id
     */
    public void setArtifactImageId(int artifactImageId) {
        this.artifactImageId = artifactImageId;
    }

}
