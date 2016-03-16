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

    public void setPress(int[] press) {
        this.press = press;
    }

    public void setRelease(int[] release) {
        this.release = release;
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

}
