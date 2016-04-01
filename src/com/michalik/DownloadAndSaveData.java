package com.michalik;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
                System.out.println("Data downloaded");
                //touchDataObjectParser.parseRawDataToObjects();
                TouchDataObject[] touchDataObjects = touchDataObjectParser.parseRawDataToObjects();

                for(int i=0; i< touchDataObjects.length; i++){
                    System.out.println("ID pomiaru: "+touchDataObjects[i].getID());
                    System.out.println("Data pomiaru: "+touchDataObjects[i].getDate());
                    //System.out.println("Press[17]: "+touchDataObjects[i].getPress()[17]);
                    touchDataObjects[i].countIntervals();
                    touchDataObjects[i].countBPM();
                    String f = touchDataObjects[i].parseForGnuplot();
                    saveObjectToFile(touchDataObjects[i]);
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
        String dir = "";
        /*
        0-60, 60-80, 80-100, 100-120, 120-140, 140 - inf
         */

        if(bmp<50){
            dir="veryslow";
        }
        if(bmp>=50 && bmp<90){
            dir="50-90";
        }
        if(bmp>=90 && bmp<110){
            dir="90-110";
        }
        if(bmp>=110 && bmp<130){
            dir="110-130";
        }
        if(bmp>=130 && bmp<150){
            dir="130-150";
        }
        if(bmp>=150 && bmp<190){
            dir="150-190";
        }
        if(bmp>=160){
            dir="veryfast";
        }


        try{
            String d = touchDataObject.getDate().replaceAll("/", "-");
            d = d.replaceAll(" ", "-");
            d = d.replaceAll(":", "-");
            System.out.println(d);
            File file = new File(dir+"-"+touchDataObject.getID()+"-"+d+".dat");
            //file.getParentFile().mkdirs();

            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(touchDataObject.parseForGnuplot());
            printWriter.flush();
            printWriter.close();
            //out.print(touchDataObject.parseForGnuplot());
            //out.flush();
            //out.close();
            System.out.println("Zapisano do pliku "+file.getPath());
        }
        catch(IOException e){
            e.printStackTrace();
        }

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
