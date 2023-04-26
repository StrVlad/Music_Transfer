package com.example.demo2;




import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//мой айди музыки 579863045
public class Yandex {



    static String uid = null;
    private static String token = null;
    public static String get(String url,boolean base ) throws IOException {
        try {
            String baseUrl = "https://api.music.yandex.net/";
            URL ur;
            if (base) {

                ur = new URL(baseUrl + url);
            } else {
                ur = new URL(url);
            }

            HttpURLConnection httpCon = (HttpURLConnection) ur.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();
        }catch (Exception e){
            return null;
        }

    }
    public static boolean getTokenAndUid(String login, String password) throws IOException {
        Process process =Runtime.getRuntime().exec("curl -X POST https://oauth.yandex.ru/token -H accept: application/json -H Content-Type: application/x-www-form-urlencoded -d grant_type=password&client_id=23cabbbdc6cd418abb4b39c32c41195d&client_secret=53bc75238f0c4d08a118e51fe9203300&username="+login+"&password="+password);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine())!=null){
            sb.append(line+"\n");
        }

        JSONObject jso = new JSONObject(sb.toString());
        if(sb.indexOf("error")==-1) {
            String token = jso.getString("access_token");
            Yandex.token = token;
            int uid = jso.getInt("uid");
            Yandex.uid = String.valueOf(uid);
            System.out.println("getToken Success");
            return true;
        }
        System.out.println("getToken not Success");
        return false;

    }
    public static void getUid(String login, String password) throws IOException {
        Process process =Runtime.getRuntime().exec("curl -X POST https://yandex-music-cors-proxy.onrender.com/https://oauth.yandex.ru/token -H accept: application/json -H Content-Type: application/x-www-form-urlencoded -d grant_type=password&client_id=23cabbbdc6cd418abb4b39c32c41195d&client_secret=53bc75238f0c4d08a118e51fe9203300&username="+login+"&password="+password);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine())!=null){
            sb.append(line+"\n");
        }

        JSONObject jso = new JSONObject(sb.toString());
        String uid = String.valueOf( jso.getInt("uid"));
        Yandex.uid = uid;
    }
    public static String searchTrackById(String id)  {
        String track = null;
        try {

            JSONObject jsa = new JSONObject(get("tracks/" + id,true));
            String title = jsa.getJSONArray("result").getJSONObject(0).getString("title");
            String autor = jsa.getJSONArray("result").getJSONObject(0).getJSONArray("artists").getJSONObject(0).getString("name");
            track = title + " " + autor;
        }catch (Exception e){

        }
        System.out.println("searchByTrackId"+track+" success");
        return track;
    }

    public static String getTrackId(String track) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String resp = get("search?text=" + track.replace(" ", "%20") + "&page=0&type=track&oncocrrect=true", true);
            System.out.println(resp);
            Map<String, Object> map = new HashMap<String, Object>();
            int jso = 0;
            map = mapper.readValue(resp, new TypeReference<Map<String, Object>>() {
            });
            if (resp.indexOf("tracks") != -1) {

                jso = new JSONObject(map).getJSONObject("result").getJSONObject("tracks").getJSONArray("results").getJSONObject(0).getInt("id");
                System.out.println(jso);
            }
            System.out.println("getTrackId success");
            return String.valueOf(jso);
        }catch (Exception e){
            return "";
        }

    }

    public static ArrayList<String> getTrackIds () throws IOException {
        String get = get("users/"+uid+"/likes/tracks",true);
        JSONObject jsa = new JSONObject(get);
        JSONArray tracks =  jsa.getJSONObject("result").getJSONObject("library").getJSONArray("tracks");
        ArrayList<String> tracksIds = new ArrayList<>();
        for(int i =0;i<tracks.length();i++){
            tracksIds.add(tracks.getJSONObject(i).getString("id"));
        }
        System.out.println(" success");
        return tracksIds;
    }
    public static void addTracks(ArrayList<String> tracks) throws IOException {
        try {
            String trackList = "";
            trackList += tracks.get(0);
            for (int i = 1; i < tracks.size(); i++) {
                trackList += "," + tracks.get(i);
            }

            DefaultHttpClient httpClient = new DefaultHttpClient();
            String u = "https://api.music.yandex.net/users/" + Yandex.uid + "/likes/tracks/add-multiple?trackIds=" + trackList;
            HttpPost httpPost = new HttpPost(u);

            httpPost.addHeader("Authorization", "OAuth " + token);
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response);
        }catch (Exception e){

        }
    }
    public static boolean init(String login, String password) throws IOException {
       return getTokenAndUid(login,password);
    }
//    public static void main(String[] args) throws IOException {
//        // init("GeorgeHide","22005544qQ");
//        Yandex.uid = "1739946741";
//        ArrayList<String> tracks = new ArrayList<>();
//        //?trackIds="+trackList+"
//        /*
//        tracks.add("104001062");
//        tracks.add("575028");
//        */
//        tracks.add(getTrackId( "демоны три дня дождя"));
//
//
//
//        addTracks(tracks);
//    }

}
