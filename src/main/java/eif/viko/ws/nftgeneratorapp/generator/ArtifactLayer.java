package eif.viko.ws.nftgeneratorapp.generator;

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

    public int getImageId() {
        return imageId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public int getZOrder() {
        return zOrder;
    }

}
