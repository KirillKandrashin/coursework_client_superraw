package sample.utils;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sample.models.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ApiSessionVisitor {
    private static final String url = "https://kandrashin-server-coursework.herokuapp.com";
    //private static final String url = "http://localhost:8080";

    public void createVisitor(String jsons) {
        HttpClass.PostRequest(url + "/visitors", jsons);
    }

    public void editVisitor(Long id, String jsons) {
        HttpClass.PutRequest(url + "/visitors/" + id, jsons);
    }

    public List<Visitor> getAllfromTable(String dop_url) {
        List<Visitor> result = new ArrayList<>();
        if (dop_url.contains(url)){
            JSONArray answer = HttpClass.GetRequest(dop_url, "visitors");
            for (int i = 0; i < answer.length(); i++) {
                result.add(visitorFromJson(answer.getJSONObject(i)));
            }
        }else{
            JSONArray answer = HttpClass.GetRequest(url + dop_url, "visitors");
            for (int i = 0; i < answer.length(); i++) {
                result.add(visitorFromJson(answer.getJSONObject(i)));
            }
        }
        return result;
    }

    public Visitor visitorFromJson(JSONObject currentVisitor) {
        Long id_parsed = Long.parseLong(ParseID(currentVisitor));
        String first_name = currentVisitor.getString("first_name");
        String last_name = currentVisitor.getString("last_name");
        String library_card = currentVisitor.getString("library_card");
        Visitor visitor = new Visitor(id_parsed, first_name, last_name, library_card);
        return visitor;
    }

    public String ParseID(JSONObject currentVisitor) {
        String id_raw = currentVisitor.getJSONObject("_links").getJSONObject("self").getString("href");
        String id_p = id_raw.substring(id_raw.length() - 1);
        return id_p;
    }
    
    public boolean deleteVisitor(Visitor visitor) {
        Long id = visitor.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/visitors/" + Long.valueOf(id));
    }
}
