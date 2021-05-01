package sample.utils;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sample.models.Book;
import sample.models.Publisher;
import sample.models.Section;

import java.util.ArrayList;
import java.util.List;

public class ApiSessionSection {
    //private static final String url = "https://server-for-coursework.herokuapp.com";
    private static final String url = "http://localhost:8080";

    public void createSection(Section section) {
        Unirest.post(url + "/sections")
            .header("Content-Type", "application/json")
            .body("{}")
            .asEmpty();
    }

    public List<Section> getAllfromTable() {
        List<Section> result = new ArrayList<>();
        JSONArray answer = HttpClass.GetRequest(url + "/sections", "sections");
        for (int i = 0; i < answer.length(); i++) {
            result.add(sectionFromJson(answer.getJSONObject(i)));
        }
        return result;
    }

    public Section sectionFromJson(JSONObject currentSection){
        Long id_parsed = Long.parseLong(ParseID(currentSection));
        String link = getSectionsLink(currentSection);
        Section section = new Section(id_parsed, link);
        return section;
    }

    public String ParseID(JSONObject currentSection){
        String id_raw = currentSection.getJSONObject("_links").getJSONObject("self").getString("href");
        String id_p = id_raw.substring(id_raw.length() - 1);
        return id_p;
    }

    public Section getSectionById(Long id) {
        List<Section> answer = getAllfromTable();
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).getId() == id){
                System.out.println(answer.get(i));
                System.out.println(answer.get(i).getId());
                return answer.get(i);
            }
        }
        return null;
    }

    public String getSectionsLink(JSONObject currentSection){
        String link = currentSection.getJSONObject("_links").getJSONObject("self").getString("href");
        return link;
    }
}
