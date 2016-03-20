package com.michalik;

/**
 * Created by michalik on 16.03.16.
 */
public class TouchDataObject {
    private String date;
    private String ID;
    private int[] release;
    private int[] press;

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
            System.out.println(table[i]);
        }
        this.press = table;
    }

    public void setRelease(long[] release) {
        int[] table = new int[release.length-1];
        for(int i=0; i<table.length; i++){
            table[i]=(int)(release[i+1]-release[i]);
            System.out.println(table[i]);
        }
        this.release = table;
    }

    public int[] getRelease() {
        return release;
    }

    public int[] getPress() {
        return press;
    }

    public String getDate() {
        return date;
    }

    public String getID() {
        return ID;
    }

/*    public void countIntervals(){
        //first is zero
        int[] table = new int[press.length-1];
        for(int i=0; i<press.length; i++){
            table[i]=(int)(press[i+1]-press[i]);
        }

    }*/
}
