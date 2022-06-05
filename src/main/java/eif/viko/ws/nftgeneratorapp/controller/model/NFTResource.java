package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.Map;

/**
 * Class representing an NFT image processor step resource descriptor request model
 */
public class NFTResource {

    private String type;
    private int id;
    private Map<String, Object> properties;

    public NFTResource(String type, int id, Map<String, Object> options) {
        this.type = type;
        this.id = id;
        this.properties = options;
    }

    /**
     * @return The type of this resource
     */
    public String getType() {
        return type;
    }

    /**
     * Updates the resource type
     *
     * @param type The given resource type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The reference id of this resource
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the reference id of this resource
     *
     * @param id The given reference id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The properties associated with this resource
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Updates the properties associated with this resource
     *
     * @param properties The given properties
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

}

