package sample.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HttpClass {
    public static JSONArray GetRequest(String urlString, String table) {
        HttpResponse<JsonNode> response = Unirest.get(urlString).asJson();
        JSONArray answer = response.getBody().getObject().getJSONObject("_embedded").getJSONArray(table);
        return answer;
    }

    public static boolean DeleteRequest(String urlString) {
        try {

            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("DELETE");
            http.setDoOutput(true);
            http.connect();

            http.getResponseCode();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String PostRequest(String urlString, String jsonString) {

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("POST");
            http.setDoOutput(true);

            byte[] out = jsonString.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        public static String PutRequest(String urlString, String jsonString) {
            try {
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                HttpURLConnection http = (HttpURLConnection) conn;

                http.setRequestMethod("PUT");
                http.setDoOutput(true);

                byte[] out = jsonString.getBytes(StandardCharsets.UTF_8);
                int length = out.length;
                http.setFixedLengthStreamingMode(length);
                http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                http.connect();
                try (OutputStream os = http.getOutputStream()) {
                    os.write(out);
                }

                StringBuilder sb = new StringBuilder();
                InputStream is = new BufferedInputStream(conn.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
}
