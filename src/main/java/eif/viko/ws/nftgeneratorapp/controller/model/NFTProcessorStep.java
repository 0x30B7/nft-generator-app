package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.Map;

public class NFTProcessorStep {

    private String type;
    private Map<String, Object> properties;

    public NFTProcessorStep(String type, Map<String, Object> properties) {
        this.type = type;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

}
