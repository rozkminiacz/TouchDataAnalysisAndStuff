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

    public void countIntervals(){
        //policz różnice między odpowiadającymi sobie elementami pressTable i releaseTable

        for(int i=1; i<12; i++){
            System.out.println(i+" "+(pressTable[i]-releaseTable[i-1]));
            //intervals[i]=
        }
    }
}
