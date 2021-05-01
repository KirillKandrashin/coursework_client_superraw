package sample.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import sample.models.Author;
import sample.models.Book;
import sample.models.Publisher;
import sample.models.Section;

import java.util.ArrayList;
import java.util.List;

public class ApiSessionBook {
    //private static final String url = "https://server-for-coursework.herokuapp.com";
    private static final String url = "http://localhost:8080";

    public void createBook(String jsons) {
        HttpClass.PostRequest(url + "/books", jsons);
    }

    public void editBook(Long id, String jsons) {
        HttpClass.PutRequest(url + "/books/" + id, jsons);
    }

    public List<Book> getAllfromTable(String dop_url) {
        List<Book> result = new ArrayList<>();
        if (dop_url.contains(url)){
            JSONArray answer = HttpClass.GetRequest(dop_url, "books");
            for (int i = 0; i < answer.length(); i++) {
                result.add(bookFromJson(answer.getJSONObject(i)));
            }
        }else{
            JSONArray answer = HttpClass.GetRequest(url + dop_url, "books");
            for (int i = 0; i < answer.length(); i++) {
                result.add(bookFromJson(answer.getJSONObject(i)));
            }
        }
        return result;
    }

    public Book bookFromJson(JSONObject currentBook) {
          System.out.println(currentBook);
        Long id_parsed = Long.parseLong(ParseID(currentBook));
        String title = currentBook.getString("title");
        String type = currentBook.getString("type");
        String genre = currentBook.getString("genre");
        Integer number_of_copies = currentBook.getInt("number_of_copies");
        Long section_id_parsed = Long.parseLong(Parse_Section_ID(currentBook));
        List<String> authors_names = new ArrayList<>();
        List<String> publishers_names = new ArrayList<>();
        for (int s = 0; s < ParseAuthorList(currentBook).size(); s++){
            authors_names.add(ParseAuthorList(currentBook).get(s).getName());
        }
        String a_n_result = String.join(",", authors_names);
        for (int b = 0; b < ParsePublisherList(currentBook).size(); b++){
            publishers_names.add(ParsePublisherList(currentBook).get(b).getName());
        }
        String p_n_result = String.join(",", publishers_names);
        System.out.println(a_n_result);
        System.out.println(p_n_result);
        Book book = new Book(id_parsed, title, a_n_result, p_n_result, type, genre, number_of_copies, section_id_parsed);
        return book;
    }

    public String ParseID(JSONObject currentBook) {
        String id_raw = currentBook.getJSONObject("_links").getJSONObject("self").getString("href");
        String id_p = id_raw.substring(id_raw.length() - 1);
        return id_p;
    }

    public String Parse_Section_ID(JSONObject currentBook) {
        String sectionlink = currentBook.getJSONObject("_links").getJSONObject("section").getString("href");
        HttpResponse<JsonNode> response = Unirest.get(sectionlink).asJson();
        String answer = response.getBody().getObject().getJSONObject("_links").getJSONObject("self").getString("href");
        String sec_id_p = answer.substring(answer.length() - 1);
        return sec_id_p;
    }

    public List<Author> ParseAuthorList(JSONObject currentBook){
        ApiSessionAuthor apiSessionAuthor = new ApiSessionAuthor();
        String authorListlink = currentBook.getJSONObject("_links").getJSONObject("authorList").getString("href");
        List<Author> authorList = apiSessionAuthor.getAllfromTable(authorListlink);
        return authorList;
    }

    public List<Publisher> ParsePublisherList(JSONObject currentBook){
        ApiSessionPublisher apiSessionPublisher = new ApiSessionPublisher();
        String publisherListlink = currentBook.getJSONObject("_links").getJSONObject("publisherList").getString("href");
        List<Publisher> publisherList = apiSessionPublisher.getAllfromTable(publisherListlink);
        return publisherList;
    }

    public boolean deleteBook(Book book) {
        Long id = book.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(url + "/books/" + Long.valueOf(id));
    }
}
