package sample.models;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visitor implements Serializable, ApiModel {
    private static final long serialVersionUID = 1447199063826949783L;

    private Long id;
    private String first_name;
    private String last_name;
    private String library_card;

    public Visitor(Long id, String first_name, String last_name, String library_card) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.library_card = library_card;
    }

    public Visitor(String first_name, String last_name, String library_card) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.library_card = library_card;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLibrary_card() {
        return library_card;
    }

    public void setLibrary_card(String library_card) {
        this.library_card = library_card;
    }



    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("first_name", getFirst_name());
        map.put("last_name", getLast_name());
        map.put("library_card", getLibrary_card());
        return gson.toJson(map);
    }
}
