package com.michalik;

import java.io.IOException;
public class Main {

    public static void main(String[] args) {
        // write your code here
        /*
        pobierz z serwera
        przetłumacz na język tablic i stringów
        przygotuj do wyświetlania w gnuplocie
         */
        String dataFileNumber = "666";
        String userID = "666";
        try{
            ServerConnection serverConnection = new ServerConnection(userID);
            serverConnection.setBaseURL("http://rozkmin.esy.es/touch/data"+dataFileNumber);
            serverConnection.updateTouchDataFiles();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }
}
