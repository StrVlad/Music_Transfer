package com.example.demo2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class Vk {
    public static String token = null;
    public static String get(String ur) throws IOException, InterruptedException {
        sleep(200);
        boolean captcha = false;
        StringBuilder sb = null;
        String resp = null;
        String baseUrl = "https://api.vk.com/method/";
        do {
            URL url = new URL(baseUrl+ur+"&v=5.95&access_token="+token);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.addRequestProperty("User-Agent", "KateMobileAndroid/56 lite-460 (Android 4.4.2; SDK 19; x86; unknown Android SDK built for x86; en)");
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            resp = sb.toString();
            if(sb.indexOf("Captcha")!=-1) {
                System.out.println("капча " + sb);
                JSONObject jso =  new JSONObject(sb.toString()).getJSONObject("error");
                //solveCaptcha(jso.getString("captcha_img"),jso.getString("captcha_sid"),ur);
                sleep(200);
                captcha = false;
                resp = solveCaptcha(jso.getString("captcha_img"),jso.getString("captcha_sid"),baseUrl+ur);
            }else if(sb.indexOf("error")!=-1){
                System.out.println("ssssss " + sb.indexOf("error"));
                System.out.println(sb.toString());
                sleep(100);
                captcha = true;
            }else{
                captcha = false;
            }


        }while (captcha);
        return resp;
    }
    public static void login(){
        //https://oauth.vk.com/authorize?client_id=2685278&scope=69640&redirect_uri=https://oauth.vk.com/blank.html&display=page&response_type=token&revoke=1
        WebDriver wb = new ChromeDriver();
        String tokenUrl;
        wb.get("https://oauth.vk.com/authorize?client_id=2685278&scope=69640&redirect_uri=https://oauth.vk.com/blank.html&display=page&response_type=token&revoke=1");

        while(true) {
            String s ;
            s = wb.getTitle();
            if(s.equals("OAuth Blank")){
               tokenUrl = wb.getCurrentUrl();
               wb.quit();
               break;
            }
        }
        String token = tokenUrl.substring(tokenUrl.indexOf("token=")+6,tokenUrl.indexOf("&"));
        Vk.token = token;
    }
    public static String getTrackId(String track) throws IOException, InterruptedException {
        String trackId = null;
        String url = "audio.search?q="+track.replace(" ","%20");


        JSONObject jso = new JSONObject( get(url)).getJSONObject("response");
        trackId = String.valueOf(jso.getJSONArray("items").getJSONObject(0).getInt("id"));
        return trackId;
    }
    public static String getOwnerId(String track) throws IOException, InterruptedException {
        String ownerId = null;
        String url = "audio.search?q="+track.replace(" ","%20");


        JSONObject jso = new JSONObject( get(url)).getJSONObject("response");
        ownerId = String.valueOf(jso.getJSONArray("items").getJSONObject(0).getInt("owner_id"));

        return ownerId;
    }
    public static  String addTrack( String track) throws IOException, InterruptedException {
        String url = "audio.add?audio_id=" + getTrackId(track) + "&owner_id=" + getOwnerId(track);
        return get(url);
    }
    public static String solveCaptcha(String captchaUrl,String captchaSid,String url) throws IOException, InterruptedException {
        FileWriter writer = new FileWriter("captchaUrl.txt");
        writer.write(captchaUrl);
        writer.flush();
        Process p = Runtime.getRuntime().exec("main.exe");
        
        p.waitFor();
        Path path = Paths.get("captchaSolved.txt");
        String key =  Files.lines(Paths.get("captchaSolved.txt")).collect(Collectors.joining(System.lineSeparator()));
       URL ur = new URL(url+"&captcha_key="+key+"&captcha_sid="+captchaSid);
       HttpURLConnection httpConn = (HttpURLConnection) ur.openConnection();
       httpConn.addRequestProperty("User-Agent", "KateMobileAndroid/56 lite-460 (Android 4.4.2; SDK 19; x86; unknown Android SDK built for x86; en)");
       httpConn.setRequestMethod("GET");
       httpConn.connect();
       BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
       StringBuilder sb = new StringBuilder();
       String line;

       while ((line = br.readLine()) != null) {
           sb.append(line + "\n");
       }
       br.close();
       System.out.println("ответ от капчи     "+sb.toString());
       return sb.toString();
   }

    public static ArrayList<String> getTracks() throws IOException, InterruptedException {
        ArrayList<String> tracks = new ArrayList<String>();

        String get =  get("audio.get?offset=0");
        JSONArray jso = new JSONObject(get).getJSONObject("response").getJSONArray("items");
        String artist;
        String title;
        System.out.println(jso);
        for(int i=1;jso.length()!=0;i++){
            for(int j=0;j<jso.length();j++){
                artist = jso.getJSONObject(j).getString("title");
                title = jso.getJSONObject(j).getString("artist");
                tracks.add(artist+"&"+title);
                System.out.println(tracks.get(i*j));
            }
            jso = new JSONObject(get("audio.get?offset="+  200*i)).getJSONObject("response").getJSONArray("items");
        }


        System.out.println(tracks.size());
        return tracks;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        Vk.token = "vk1.a.wTpXo-4_tJfAsk0K7zUTiWFdJ7nL9zvVPcGWv6RblSN5ZFfzTpX9JRlnqrO2P4AE4gVbpdIw4eS9X0Lo5Pb74ioH_N_R_DOjvGc7Nd3GUGG2A5wvBfKQ253ege8EIh_SQ-ltKDsraFcAUEo8DO3HyaXWMJlOx8Hu_RyXkt7NprCC0jCVh9co7PG7CqgH85POjw7Q-TRvlg57cch8xNEEIw";
//        getTracks();
//    }
}
