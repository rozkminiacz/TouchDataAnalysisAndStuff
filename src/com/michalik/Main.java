package com.michalik;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) {
        // write your code here
        /*
        pobierz z serwera
        przetłumacz na język tablic i stringów
        przygotuj do wyświetlania w gnuplocie
         */
        try{
            ServerConnection.updateTouchDataFiles();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }
}
