package com.michalik;

/**
 * Created by michalik on 16.03.16.
 */
public class TouchDataObject {
    private String date;
    private String ID;
    private long[] release;
    private long[] press;

    public void setDate(String date) {
        this.date = date;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPress(long[] press) {
        this.press = press;
    }

    public void setRelease(long[] release) {
        this.release = release;
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

}
