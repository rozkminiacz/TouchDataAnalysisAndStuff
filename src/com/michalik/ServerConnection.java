package com.michalik;

import org.apache.sanselan.util.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;

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
    private int[] pressTable;
    private int[] releaseTable;
    public ServerConnection(String userID){
        this.userID = userID;
    }
    //download all data marked by *ID from server
    //metody statyczne, wrzucamy znowu do tablic
    public void updateTouchDataFiles() throws IOException {
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
        int i=0;
        while ((inputLine = in.readLine()) != null) {

//            System.out.println(inputLine);
            lines+=inputLine+"\n";
        }
        System.out.println(lines);
        /*String[] lines = inputLine.split("\n");
            for(int i=0; i<lines.length; i++){
                System.out.println(lines[i]);
            }
         */
        //parse inputLine


        in.close();
        convertToTouchDataAcceptableFormat(lines);
    }


    private void convertToTouchDataAcceptableFormat(String lines){
        String[] linesTable = lines.split("\n");
        System.out.println(linesTable.length);
        int objectCounter = 0;
        for(int i=0; i<linesTable.length; i++){
            if(linesTable[i].equals(userID)){
                objectCounter++;
            }
        }
        System.out.println(objectCounter);

        TouchDataObject[] touchDataObject= new TouchDataObject[objectCounter];
        for(int i=0; i<touchDataObject.length; i++){
            //będzie tutaj coś odpowiedniego, jakieś settery na jakimś kiju
        }

            //korzystam z obiektu TouchDataObject
            //tyle obiektów, ile wystąpień daty
    }

    void setBaseURL(String url){
        this.baseURL = url;
    }

    int[] getPressTable(){
        return pressTable;
    }
    int[] getReleaseTable(){
        return releaseTable;
    }
}
