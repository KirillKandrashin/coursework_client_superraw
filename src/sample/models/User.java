package sample.models;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable, ApiModel {
    private static final long serialVersionUID = 1447199063826949783L;

    private Long id;
    private String login;
    private String password;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String email) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("login", getLogin());
        map.put("password", getPassword());
        return gson.toJson(map);
    }

}
