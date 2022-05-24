package eif.viko.ws.nftgeneratorapp.generator;

import java.util.List;

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

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<ArtifactLayer> getLayers() {
        return layers;
    }

    public MintingStage getStage() {
        return stage;
    }

    public void setStage(MintingStage stage) {
        this.stage = stage;
    }

    public int getArtifactImageId() {
        return artifactImageId;
    }

    public void setArtifactImageId(int artifactImageId) {
        this.artifactImageId = artifactImageId;
    }

}
