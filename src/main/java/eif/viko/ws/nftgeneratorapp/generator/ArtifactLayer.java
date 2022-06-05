package eif.viko.ws.nftgeneratorapp.generator;

/**
 * Class representing an image layer in the NFT minting operation model
 */
public class ArtifactLayer {

    private int imageId;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;
    private int zOrder;

    public ArtifactLayer(int imageId, int width, int height, int xOffset, int yOffset, int zOrder) {
        this.imageId = imageId;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOrder = zOrder;
    }

    /**
     * @return The image id of the artifact layer
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * @return The layer image width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The layer image height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The X coordinate offset of the layer
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * @return The Y coordinate offset of the layer
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * @return The layer's Z level order relative to other layers
     */
    public int getZOrder() {
        return zOrder;
    }

}
