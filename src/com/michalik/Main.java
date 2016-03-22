package com.michalik;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        /*
        @TODO
                utwórz katalog o name=userid
                zapisz każdy pomiar do osobnego pliku
                1,2,3,4, czy datą???

        */
        /*
        pobierz z serwera
        przetłumacz na język tablic i stringów
        przygotuj do wyświetlania w gnuplocie
         */
        String ID = "555";

        DownloadAndSaveData downloadAndSaveData = new DownloadAndSaveData(ID);
        downloadAndSaveData.doStuff();

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
