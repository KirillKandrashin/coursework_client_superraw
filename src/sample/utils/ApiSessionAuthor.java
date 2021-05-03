package sample.utils;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sample.models.Author;
import sample.models.Book;
import sample.models.Author;

import java.util.ArrayList;
import java.util.List;

public class ApiSessionAuthor {
    private static final String url = "https://kandrashin-server-coursework.herokuapp.com";
    //private static final String url = "http://localhost:8080";

    public void createAuthor(String jsons) {
        HttpClass.PostRequest(url + "/authors", jsons);
    }

    public void editAuthor(Long id, String jsons) {
        HttpClass.PutRequest(url + "/authors/" + id, jsons);
    }


    public List<Author> getAllfromTable(String dop_url) {
        List<Author> result = new ArrayList<>();
        if (dop_url.contains(url)) {
            JSONArray answer = HttpClass.GetRequest(dop_url, "authors");
            for (int i = 0; i < answer.length(); i++) {
                result.add(authorFromJson(answer.getJSONObject(i)));
            }
        }else {
            JSONArray answer = HttpClass.GetRequest(url + dop_url, "authors");
            for (int i = 0; i < answer.length(); i++) {
                result.add(authorFromJson(answer.getJSONObject(i)));
            }
        }
        return result;
    }

    public Author getAuthorByName(String name) {
        List<Author> answer = getAllfromTable("/authors");
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).getName().equals(name)){
                return answer.get(i);
            }
        }
        return null;
    }

    public Author authorFromJson(JSONObject currentAuthor){
        Long id_parsed = Long.parseLong(ParseID(currentAuthor));
        String name = currentAuthor.getString("name");
        String link = getAuthorsLink(currentAuthor);
        List<String> books_names = ParseBookList(currentAuthor);
        String b_n_result = String.join(",", books_names);
        Author author = new Author(id_parsed, name, link, b_n_result);
        return author;
    }

    public String ParseID(JSONObject currentAuthor){
        String id_raw = currentAuthor.getJSONObject("_links").getJSONObject("self").getString("href");
        String id_p = id_raw.substring(id_raw.length() - 1);
        return id_p;
    }

    public String getAuthorsLink(JSONObject currentAuthor){
        String link = currentAuthor.getJSONObject("_links").getJSONObject("self").getString("href");
        return link;
    }

    public List<String> ParseBookList(JSONObject currentAuthor){
        List<String> authorList = new ArrayList<>();
        String bookListlink = currentAuthor.getJSONObject("_links").getJSONObject("bookList").getString("href");
        JSONArray answer = HttpClass.GetRequest(bookListlink, "books");
        for (int i = 0; i < answer.length(); i++) {
            authorList.add(bookGetTitle(answer.getJSONObject(i)));
        }
        return authorList;
    }

    public String bookGetTitle(JSONObject currentBook) {
        String title = currentBook.getString("title");
        return title;
    }
}
