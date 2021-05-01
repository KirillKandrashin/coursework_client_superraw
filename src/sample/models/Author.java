package sample.models;

import java.io.Serializable;

public class Author  implements Serializable {
    private static final long serialVersionUID = 1447199063826949783L;
    private Long id;
    private String name;
    private String link;


    public Author(Long id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Author(String name) {
        this.name = name;
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
}
