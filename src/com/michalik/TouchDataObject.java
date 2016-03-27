package com.michalik;

/**
 * Created by michalik on 16.03.16.
 */
public class TouchDataObject {
    private String date;
    private String ID;
    private long[] release;
    private long[] press;
    private int[] intervals;
    public int[] releaseTable;
    public int[] pressTable;

    private double bmpR, bmpP;

    public void setDate(String date) {
        this.date = date;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPress(long[] press) {
        int[] table = new int[press.length-1];
        for(int i=0; i<table.length; i++){
            table[i]=(int)(press[i+1]-press[i]);
            //System.out.println(table[i]);
        }
        this.press = press;
        this.pressTable = table;
        //countIntervals();
    }

    public void setRelease(long[] release) {
        int[] table = new int[release.length-1];
        for(int i=0; i<table.length; i++){
            table[i]=(int)(release[i+1]-release[i]);
            //System.out.println(table[i]);
        }
        this.release = release;
        this.releaseTable = table;
    }

    public long[] getRelease() {
        return release;
    }

    public long[] getPress() {
        return press;
    }

    public String getDate() {
        return date;
    }

    public String getID() {
        return ID;
    }

    public int[] getIntervals(){
        //countIntervals();
        return intervals;
    }
    public double getBmpR(){
        return bmpR;
    }
    public double getBmpP(){
        return bmpP;
    }
    public void countBPM(){

        /*
        To bardzo źle, że tak wierzę w użytkownika, ale zakładam, że wykonał więcej niż 10 pomiarów...
         */

        double bpm=0;
        long startR = release[0];
        long endR = release[10];
        long startP = press[0];
        long endP = press[10];
        double bmpR = (double)endR - (double)startR;
        bmpR = bmpR / 1000;
        bmpR/=10; //średni okres jednego pomiaru
        bmpR = 1/bmpR;
        bmpR*=60;

        double bmpP = (double)endP - (double)startP;
        bmpP = bmpP / 1000;
        bmpP/=10; //średni okres jednego pomiaru
        bmpP = 1/bmpP;
        bmpP*=60;

        System.out.println("BPM: "+bmpR);
        /*double bmpR = ((double)endR - (double)startR)*1000;
        double bmpP = ((double)endP - (double)startP)*1000;

        bmpR = 6000/bmpR;
        //bmpR = bmpR*60;
        System.out.println(bmpR+" aaaaaaaaaaaaaaaaaaa");
*/
        this.bmpP=bmpP;
        this.bmpR=bmpR;

    }

    public void countIntervals(){
        //policz różnice między odpowiadającymi sobie elementami pressTable i releaseTable
        int[] table = new int[press.length];
        for(int i=1; i<table.length; i++){
            //System.out.println(i+" "+(release[i]-press[i]));
            table[i]=(int)(release[i]-press[i]);
        }
        this.intervals=table;
    }
    public String parseForGnuplot(){
        String gp = "press"+"\t"+"release"+"\t"+"interval"+"\n";
        /*
        press \t release \t interval
        pozbywam się również zer z tablic
         */


        int length = 0;
        for(int i=0; i<release.length; i++){
            if(release[i]!=0){
                length++;
            }
            else{
                break;
            }
        }
        int[] tableOne = new int[length];
        length=0;
        for(int i=0; i<press.length; i++){
            if(press[i]!=0){
                length++;
            }
            else{
                break;
            }
        }
        int[] tableTwo = new int[length];

        for(int i=0; i<tableOne.length-1; i++){
            tableOne[i]=(int)(release[i+1]-release[i]);
            //System.out.println(tableOne[i]);
        }
        for(int i=0; i<tableTwo.length-1; i++){
            tableTwo[i]=(int)(press[i+1]-press[i]);
        }
        //System.out.println(tableTwo.length+" "+tableOne.length+" "+intervals.length);
        for(int i=0; i<tableTwo.length; i++){

                gp+=tableTwo[i]+"\t"+tableOne[i]+"\t"+intervals[i]+"\n";
                //System.out.println(gp);
            /*
            @TODO:
            dobry parser, obsługujący to, że tablice nie mają takich samych wymiarów

             */


        }
        return gp;
    }
}
