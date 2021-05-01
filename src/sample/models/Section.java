package sample.models;

import java.io.Serializable;

public class Section implements Serializable {
    private static final long serialVersionUID = 1447199063826949783L;
    private Long id;
    private String link;

    public Section(Long id, String link) {
        this.id = id;
        this.link = link;
    }

    public Section(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
