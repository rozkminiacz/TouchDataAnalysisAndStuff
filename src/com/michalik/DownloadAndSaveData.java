package com.michalik;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by michalik on 22.03.16.
 */
public class DownloadAndSaveData {
    private String ID;
    public DownloadAndSaveData(String ID){
        this.ID = ID;
    }
    public void doStuff(){
        if(!checkIntConnection()){
            System.out.println("Cannot connect to internet");
        }
        else {
            try {
                ServerConnection serverConnection = new ServerConnection(ID);
                serverConnection.setBaseURL("http://rozkmin.esy.es/touch/data" + ID);
                String rawData = serverConnection.updateTouchDataFiles();

                TouchDataObjectParser touchDataObjectParser = new TouchDataObjectParser(rawData, ID);

                //touchDataObjectParser.parseRawDataToObjects();
                TouchDataObject[] touchDataObjects = touchDataObjectParser.parseRawDataToObjects();

                for(int i=0; i< touchDataObjects.length; i++){
                    System.out.println("ID pomiaru: "+touchDataObjects[i].getID());
                    System.out.println("Data pomiaru: "+touchDataObjects[i].getDate());
                    //System.out.println("Press[17]: "+touchDataObjects[i].getPress()[17]);
                    touchDataObjects[i].countIntervals();
                    touchDataObjects[i].countBPM();
                    String f = touchDataObjects[i].parseForGnuplot();
                    //System.out.println(f);
                }
                //save object ot file
                //mkdir userID
                //cd userID
                //each file to separate

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
    public void saveObjectToFile(TouchDataObject touchDataObject){
        double bmp = touchDataObject.getBmpP();
        /*
        @TODO
        katalogowanie odpowiednie plików - wg:
        1. userID
        2. BPM

        konwencja:
        nieparzyste - trzeźwe
        parzyste - pijane/zmęczone
        

         */

    }
}
