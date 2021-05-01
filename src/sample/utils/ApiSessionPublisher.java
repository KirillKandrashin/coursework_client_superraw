package sample.utils;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sample.models.Book;
import sample.models.Publisher;

import java.util.ArrayList;
import java.util.List;

public class ApiSessionPublisher {
    private static final String url = "https://server-for-coursework.herokuapp.com";
    //private static final String url = "http://localhost:8080";

    public void createPublisher(Publisher publisher) {
        Unirest.post(url + "/publishers")
                .header("Content-Type", "application/json")
                .body(publisher)
                .asEmpty();
    }

    public List<Publisher> getAllfromTable(String dop_url) {
        List<Publisher> result = new ArrayList<>();
        if (dop_url.contains(url)) {
            JSONArray answer = HttpClass.GetRequest(dop_url, "publishers");
            for (int i = 0; i < answer.length(); i++) {
                result.add(publisherFromJson(answer.getJSONObject(i)));
            }
        }else {
            JSONArray answer = HttpClass.GetRequest(url + dop_url, "publishers");
            for (int i = 0; i < answer.length(); i++) {
                result.add(publisherFromJson(answer.getJSONObject(i)));
            }
        }
        return result;
    }

    public Publisher getPublisherByName(String name) {
        List<Publisher> answer = getAllfromTable("/publishers");
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).getName().equals(name)){
                return answer.get(i);
            }
        }
        return null;
    }

    public Publisher publisherFromJson(JSONObject currentPublisher){
        Long id_parsed = Long.parseLong(ParseID(currentPublisher));
        String name = currentPublisher.getString("name");
        String link = getPublishersLink(currentPublisher);
        // List<Book> bookList = ParseBookList(currentPublisher);
        // ObservableList<Book> obbookList = FXCollections.observableArrayList(bookList);
        // System.out.println(id_parsed);
        // System.out.println(name);
        // System.out.println(obbookList);
        Publisher publisher = new Publisher(id_parsed, name, link);
        return publisher;
    }

    public String ParseID(JSONObject currentPublisher){
        String id_raw = currentPublisher.getJSONObject("_links").getJSONObject("self").getString("href");
        String id_p = id_raw.substring(id_raw.length() - 1);
        return id_p;
    }

    public String getPublishersLink(JSONObject currentPublisher){
        String link = currentPublisher.getJSONObject("_links").getJSONObject("self").getString("href");
        return link;
    }
}
