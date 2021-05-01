package sample.models;


import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book implements Serializable, ApiModel {
    private static final long serialVersionUID = 1447199063826949783L;

    private Long id;
    private String title;
    private String author_name;
    private String publisher_name;
    private String type;
    private String genre;
    private Integer number_of_copies;
    private Long section_id;

    private List<String> authors_link;
    private List<String> publishers_link;
    private String section_link;

    public Book(Long id, String title, String author_name, String publisher_name, String type, String genre, Integer number_of_copies, Long section_id) {
        this.id = id;
        this.title = title;
        this.author_name = author_name;
        this.publisher_name = publisher_name;
        this.type = type;
        this.genre = genre;
        this.number_of_copies = number_of_copies;
        this.section_id = section_id;
    }

    public Book(String title, String type, String genre, Integer number_of_copies, List<String> authors_link, List<String> publishers_link, String section_link) {
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.number_of_copies = number_of_copies;
        this.authors_link = authors_link;
        this.publishers_link = publishers_link;
        this.section_link = section_link;
    }

    public Book(Long id, String title, String type, String genre, Integer number_of_copies, List<String> authors_link, List<String> publishers_link, String section_link) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.number_of_copies = number_of_copies;
        this.authors_link = authors_link;
        this.publishers_link = publishers_link;
        this.section_link = section_link;
    }

    public Book(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getNumber_of_copies() {
        return number_of_copies;
    }

    public void setNumber_of_copies(Integer number_of_copies) {
        this.number_of_copies = number_of_copies;
    }

    public Long getSection_id() {
        return section_id;
    }

    public void setSection_id(Long section_id) {
        this.section_id = section_id;
    }

    public List<String> getAuthors_link() {
        return authors_link;
    }

    public void setAuthors_link(List<String> authors_link) {
        this.authors_link = authors_link;
    }

    public List<String> getPublishers_link() {
        return publishers_link;
    }

    public void setPublishers_link(List<String> publishers_link) {
        this.publishers_link = publishers_link;
    }

    public String getSection_link() {
        return section_link;
    }

    public void setSection_link(String section_link) {
        this.section_link = section_link;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("title", getTitle());
        map.put("type", getType());
        map.put("genre", getGenre());
        map.put("number_of_copies", getNumber_of_copies());
        map.put("genre", getGenre());
        map.put("authorList", getAuthors_link());
        map.put("publisherList", getPublishers_link());
        map.put("section", getSection_link());
//        map.put("company", new Gson().fromJson(getCompany().toJSON(), JsonObject.class));
        return gson.toJson(map);
    }
}
