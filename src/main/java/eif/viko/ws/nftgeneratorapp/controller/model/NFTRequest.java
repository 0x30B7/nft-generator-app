package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.List;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<NFTLayer> getLayers() {
        return layers;
    }

    public void setLayers(List<NFTLayer> layers) {
        this.layers = layers;
    }

    public List<NFTResource> getResources() {
        return resources;
    }

    public void setResources(List<NFTResource> resources) {
        this.resources = resources;
    }

}
