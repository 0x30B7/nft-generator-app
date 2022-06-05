package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.Map;

/**
 * Class representing an NFT image processor step descriptor request model
 */
public class NFTProcessorStep {

    private String type;
    private Map<String, Object> properties;

    public NFTProcessorStep(String type, Map<String, Object> properties) {
        this.type = type;
        this.properties = properties;
    }

    /**
     * @return The type of this processing step
     */
    public String getType() {
        return type;
    }

    /**
     * Updates the type of this processing step
     *
     * @param type The given processing step type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The properties associated with this processor step
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Updates the properties associated with this processor step
     *
     * @param properties The given properties
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

}
