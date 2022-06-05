package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.List;

/**
 * Class representing an NFT minting operation request model
 */
public class NFTRequest {

    private int width;
    private int height;
    private List<NFTLayer> layers;
    private List<NFTResource> resources;

    public NFTRequest(int width, int height, List<NFTLayer> layers, List<NFTResource> resources) {
        this.width = width;
        this.height = height;
        this.layers = layers;
        this.resources = resources;
    }

    /**
     * @return The width of the final image of the to-be minted NFT artifact
     */
    public int getWidth() {
        return width;
    }

    /**
     * Updates the width of the final image of the to-be minted NFT artifact
     *
     * @param width The given width, in pixels
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return The height of the final image of the to-be minted NFT artifact
     */
    public int getHeight() {
        return height;
    }

    /**
     * Updates the height of the final image of the to-be minted NFT artifact
     *
     * @param height The given width, in pixels
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The layer specifications of the to-be minted NFT artifact
     */
    public List<NFTLayer> getLayers() {
        return layers;
    }

    /**
     * Updates the layer specifications of the to-be minted NFT artifact
     *
     * @param layers The given layer specifications
     */
    public void setLayers(List<NFTLayer> layers) {
        this.layers = layers;
    }

    /**
     * @return The specifications of resources associated with this artifact
     */
    public List<NFTResource> getResources() {
        return resources;
    }

    /**
     * Updates the specifications of resources associated with this artifact
     *
     * @param resources The given specifications of resources
     */
    public void setResources(List<NFTResource> resources) {
        this.resources = resources;
    }

}
