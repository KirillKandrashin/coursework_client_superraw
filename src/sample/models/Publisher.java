package sample.models;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Publisher implements Serializable, ApiModel, ApiModelPut {
    private static final long serialVersionUID = 1447199063826949783L;
    private Long id;
    private String name;

    private String book_name;

    private String link;
    private List<String> books_link;


    public Publisher(Long id, String name, String link, String book_name) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.book_name = book_name;
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publisher(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getBooks_link() {
        return books_link;
    }

    public void setBooks_link(List<String> books_link) {
        this.books_link = books_link;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("name", getName());
        map.put("bookList", getBooks_link());
        return gson.toJson(map);
    }

    @Override
    public String toJsonPut() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("name", getName());
        return gson.toJson(map);
    }
}
