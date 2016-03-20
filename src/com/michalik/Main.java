package com.michalik;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        /*
        pobierz z serwera
        przetłumacz na język tablic i stringów
        przygotuj do wyświetlania w gnuplocie
         */
        String dataFileNumber = "555";
        String userID = "555";

        if(!checkIntConnection()){
            System.out.println("Cannot connect to internet");
        }
        else {
            try {
                ServerConnection serverConnection = new ServerConnection(userID);
                serverConnection.setBaseURL("http://rozkmin.esy.es/touch/data" + dataFileNumber);
                String rawData = serverConnection.updateTouchDataFiles();

                TouchDataObjectParser touchDataObjectParser = new TouchDataObjectParser(rawData, userID);

                //touchDataObjectParser.parseRawDataToObjects();
                TouchDataObject[] touchDataObjects = touchDataObjectParser.parseRawDataToObjects();
                for(int i=0; i< touchDataObjects.length; i++){
                    System.out.println("ID pomiaru: "+touchDataObjects[i].getID());
                    System.out.println("Data pomiaru: "+touchDataObjects[i].getDate());
                    System.out.println("Press[17]: "+touchDataObjects[i].getPress()[17]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static boolean checkIntConnection()
    {
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
        try
        {
            sock.connect(address, 3000);
            if(sock.isConnected())
            {
                status=true;
            }
        }
        catch(Exception e)
        {

        }
        finally
        {
            try
            {
                sock.close();
            }
            catch(Exception e)
            {

            }
        }

        return status;
    }


}
