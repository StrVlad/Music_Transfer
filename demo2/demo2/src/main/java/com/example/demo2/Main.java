package com.example.demo2;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;




public class Main {



    public static boolean loginY(String login, String pass) throws IOException {
        Yandex yandex = new Yandex();
       return yandex.init(login,pass);
    }
    public static void donate(){
        WebDriver wb = new ChromeDriver();
        wb.get("https://ru.wikipedia.org/wiki/%D0%A0%D0%B8%D0%B2%D0%B7,_%D0%9A%D0%B8%D0%B0%D0%BD%D1%83");

    }
    public static void kiano(){
        WebDriver wb = new ChromeDriver();
        wb.get("https://ru.wikipedia.org/wiki/%D0%A0%D0%B8%D0%B2%D0%B7,_%D0%9A%D0%B8%D0%B0%D0%BD%D1%83");

    }
    public static void loginVk(){
        Vk vk = new Vk();
        vk.login();
    }
    public static void fromVkToY() throws IOException, InterruptedException {
        Vk vk = new Vk();
        Yandex yandex = new Yandex();
        ArrayList<String> trackIds = yandex.getTrackIds();
        for(int i=0;i<trackIds.size();i++){
            vk.addTrack( yandex.searchTrackById(trackIds.get(i)));
        }
    }
    public static void fromYToVk() throws IOException, InterruptedException {
        Yandex yandex = new Yandex();
        Vk vk = new Vk();
        ArrayList<String> tracks = vk.getTracks();
        ArrayList<String> tr = new ArrayList<>();
        for(int i=0;i<tracks.size();i++){
            tr.add(yandex.getTrackId(tracks.get(i)));
        }
        yandex.addTracks(tr);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        https://oauth.vk.com/authorize?client_id=2685278&scope=69640&redirect_uri=https://oauth.vk.com/blank.html&display=page&response_type=token&revoke=1
        https://oauth.vk.com/blank.html#access_token=vk1.a.I9Yq-nUpuYt_wkwdTjXpBoosPRdsn-ap9XFCgiXZJ4tKhAEVfYipziYgQ2R3FYk4jFScBwjx0Y3Iicmnsc9IDx1-WQ3UptgjUe4cN0oKLpuMTY-zHZwYBktHLLfpIjdV9cm-FFCeA2kwsiDPBGLuXFlHdoXFe2s_iqTTrl8BW6ZA29J8qGRVuqAWH8nZsFcKHHUy7BtPI_m8aYRuMEWSPg&expires_in=0&user_id=470783422
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("https://api.vk.com/method/audio.getById?v=5.95&access_token=vk1.a.sdYJwjR-Ag_OopDZp58vMg4KMlEr__MzL5Rn0sb-jT2NaiS3EnYMTVDfoBQ86yQTTLm7V7ERmCC1rEt4_LiZjNqjYZb8Cycc62F7QRTjivX5kJ3tIKOMRyOLrWxeap5XoRNxXpO83f-BMBAS4DN6Sd2DfzWxN5NBbcc17MscjTlY8dKBHMnE4z1bpvkEdtlCBqPjc8acISdoIeWmOEc1wA&audios=371745461_456289486");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        */









 /*
        Yandex yandex = new Yandex();
        Vk vk = new Vk();
        vk.login();
        ArrayList<String> tracks = vk.getTracks();
        ArrayList<String> tr = new ArrayList<>();
        yandex.init("GeorgeHide","22005544qQ");
        for(int i=0;i<tracks.size();i++){
            tr.add(yandex.getTrackId(tracks.get(i)));
        }
        yandex.addTracks(tr);
        //Yandex yandex = new Yandex();
       // yandex.getToken("1","1");


  */
    }
}