package fr.donovan.cap_entreprise.utils;

import java.net.*;
import java.util.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchImage {
    static String subscriptionKey = "enter key here";
    static String host = "https://api.bing.microsoft.com";
    static String path = "/v7.0/images/search/";
    static String searchTerm = "tropical ocean";

    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static String search() {
        try {
            String searchTerm = Slugger.slugify( "League of legends");
            int num = 2;

            String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + num;
            System.out.println("searchURL = " + searchURL);
            Connection.Response response = Jsoup.connect(searchURL)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute();
//            Document doc = Jsoup.connect(searchURL)
//                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                    .referrer("http://www.google.com").get();


            //If google search results HTML change the <h3 class="r" to <h3 class="r1"
            //we need to change below accordingly
//            Elements results = doc.select("h3.r1 > a");
//            System.out.println("results.size() = " + results.size());
//            System.out.println("results = " + results);
//            for (Element result : results) {
//                String linkHref = result.attr("href");
//                String linkText = result.text();
//                System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
//            }
//            return doc.toString();
            return response.parse().html();
        } catch (Exception e) {
            System.out.println("Exception = " + e);
        }
        return "";
    }

    public static String apple() {
        String search="Apple"; //your word to be search on google
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)";
        Elements links=null;
        try {
            String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + 2;
            links = Jsoup.connect(GOOGLE_SEARCH_URL +
                            URLEncoder.encode(search,"UTF-8")).
                    userAgent(userAgent).get().select(".g>.r>a");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for (Element link : links) {
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            try {
                url = URLDecoder.decode(url.substring(url.indexOf('=') +
                        1, url.indexOf('&')), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (!url.startsWith("http")) {
                continue; // Ads/news/etc.
            }

            System.out.println("Title: " + title);
            System.out.println("URL: " + url);


        }
        return "";
    }
//    public static void image(String searchQuery) {
//        try {
//            // construct the search request URL (in the form of endpoint + query string)
//            URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8"));
//            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
//            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
//
//            // receive JSON body
//            InputStream stream = connection.getInputStream();
//            String response = new Scanner(stream).useDelimiter("\\A").next();
//            // construct result object for return
//            SearchResults results = new SearchResults(new HashMap<String, String>(), response);
//
//            // extract Bing-related HTTP headers
//            Map<String, List<String>> headers = connection.getHeaderFields();
//            for (String header : headers.keySet()) {
//                if (header == null) continue;      // may have null key
//                if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
//                    results.relevantHeaders.put(header, headers.get(header).get(0));
//                }
//            }
//
//            stream.close();
//            JsonParser parser = new JsonParser();
//            JsonObject json = parser.parse(result.jsonResponse).getAsJsonObject();
//            //get the first image result from the JSON object, along with the total
//            //number of images returned by the Bing Image Search API.
//            String total = json.get("totalEstimatedMatches").getAsString();
//            JsonArray results = json.getAsJsonArray("value");
//            JsonObject first_result = (JsonObject)results.get(0);
//            String resultURL = first_result.get("thumbnailUrl").getAsString();
//        } catch (Exception e) {
//            System.out.println("Exception = " + e);
//        }
//
//    }

}
