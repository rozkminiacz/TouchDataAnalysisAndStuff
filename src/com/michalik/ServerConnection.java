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
    private static String baseURL = "http://rozkmin.esy.es/touch/data222";

    //download all data marked by *ID from server
    //metody statyczne, wrzucamy znowu do tablic
    public static void updateTouchDataFiles() throws IOException {
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

        while ((inputLine = in.readLine()) != null)

        System.out.println(inputLine    );

        in.close();


    }
}
