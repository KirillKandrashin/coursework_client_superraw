package sample.utils;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sample.models.User;

import java.util.ArrayList;
import java.util.List;

public class ApiSessionUser {
    //private static final String url = "https://kandrashin-server-coursework.herokuapp.com/";
    private static final String url = "http://localhost:8080";

    public void createUser(String jsons) {
        HttpClass.PostRequest(url + "/users", jsons);
    }

    public List<User> getAllfromTable(String dop_url) {
        List<User> result = new ArrayList<>();
        if (dop_url.contains(url)){
            JSONArray answer = HttpClass.GetRequest(dop_url, "users");
            for (int i = 0; i < answer.length(); i++) {
                result.add(userFromJson(answer.getJSONObject(i)));
            }
        }else{
            JSONArray answer = HttpClass.GetRequest(url + dop_url, "users");
            for (int i = 0; i < answer.length(); i++) {
                result.add(userFromJson(answer.getJSONObject(i)));
            }
        }
        return result;
    }

    public User userFromJson(JSONObject currentUser) {
        Long id_parsed = Long.parseLong(ParseID(currentUser));
        String login = currentUser.getString("login");
        String password = currentUser.getString("password");
        User user = new User(id_parsed, login, password);
        return user;
    }

    public String ParseID(JSONObject currentUser) {
        String id_raw = currentUser.getJSONObject("_links").getJSONObject("self").getString("href");
        String id_p = id_raw.substring(id_raw.length() - 1);
        return id_p;
    }

    public User getUserByLogin(String login) {
        List<User> answer = getAllfromTable("/users");
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).getLogin().equals(login)){
                return answer.get(i);
            }
        }
        return null;
    }
}
