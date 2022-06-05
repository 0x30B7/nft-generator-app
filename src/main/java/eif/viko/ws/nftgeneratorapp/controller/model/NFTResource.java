package eif.viko.ws.nftgeneratorapp.controller.model;

import java.util.Map;

public class NFTResource {

    private String type;
    private int id;
    private Map<String, Object> options;

    public NFTResource(String type, int id, Map<String, Object> options) {
        this.type = type;
        this.id = id;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

}
