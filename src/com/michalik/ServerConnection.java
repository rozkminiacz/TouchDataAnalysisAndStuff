package com.michalik;

import org.apache.sanselan.util.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.common.SystemCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by michalik on 16.03.16.
 */
public class ServerConnection {
    private String baseURL;// = "http://rozkmin.esy.es/touch/data222";
    private String userID;
    String lines;
    public ServerConnection(String userID){
        this.userID = userID;
    }
    //download all data marked by *ID from server
    //metody statyczne, wrzucamy znowu do tablic
    public String updateTouchDataFiles() throws IOException {
        //łącz z serwerem

        //pobierz wszystkie pliki

        //jesli nie udało się jakiegoś pobrać, pomiń go
        URL website = new URL(baseURL);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;
        String lines = "";
        //int i=0;
        while ((inputLine = in.readLine()) != null) {

//            System.out.println(inputLine);
            lines+=inputLine+"\n";
        }


        in.close();
        return lines;
    }
    void setBaseURL(String url){
        this.baseURL = url;
    }

    void saveDataToFile(String lines){
        //save lines to file for future use, when internet is down

    }
}
