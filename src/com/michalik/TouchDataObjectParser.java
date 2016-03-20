package com.michalik;

/**
 * Created by mic   halik on 19.03.16.
 */
public class TouchDataObjectParser {
    private String lines;
    int objectCounter;
    private String userID;
    public TouchDataObjectParser(String lines, String userID){
        this.lines = lines;
        this.objectCounter=0;
        this.userID = userID;
    }

    public TouchDataObject[] parseRawDataToObjects(){
        String[] linesTable = lines.split("\n");
        for(int i=0; i<linesTable.length; i++){
            if(linesTable[i].equals("endOfPress")){
                objectCounter++;
            }

        }

        TouchDataObject[] touchDataObject = new TouchDataObject[objectCounter];
        for(int i=0; i<touchDataObject.length; i++){
            touchDataObject[i] = new TouchDataObject();
            touchDataObject[i].setID(userID);
        }

        //set ID for all objects

        int i=0;
        for(int j=0; j<touchDataObject.length; j++){
            while(i<linesTable.length){
                if(linesTable[i].equals(userID)){
                    touchDataObject[j].setDate(linesTable[i+1]);
                    i++;
                }
                if(linesTable[i].equals("release")){
                    i++;
                    long[] releaseTable = new long[56];
                    int n=0;
                    for(n=0; n<releaseTable.length; n++){
                        releaseTable[n]=Long.parseLong(linesTable[i+n]);
                        //System.out.println(releaseTable[n]);
                    }
                    touchDataObject[j].setRelease(releaseTable);
                    i+=n;

                }
                if(linesTable[i].equals("press")){
                    i++;
                    long[] pressTable = new long[55];
                    int n=0;
                    for(n=0; n<pressTable.length; n++){
                        pressTable[n]=Long.parseLong(linesTable[i+n]);
                    }
                    touchDataObject[j].setPress(pressTable);
                    i+=n;
                    j++;
                }
                else{
                    i++;
                }
            }
            //touchDataObject[j].countIntervals();

        }
        System.out.println(objectCounter);
        //objectcounter
        return touchDataObject;
    }

/*    public void parseRawDataToObjects(){

//        System.out.print(lines);
        //przerzuć do talblicy
        String[] linesTable = lines.split("\n");
        for(int i=0; i<linesTable.length; i++){
            if(linesTable[i].equals("endOfPress")){
                objectCounter++;
            }

        }

        TouchDataObject[] touchDataObject = new TouchDataObject[objectCounter];
        for(int i=0; i<touchDataObject.length; i++){
            touchDataObject[i] = new TouchDataObject();
            touchDataObject[i].setID(userID);
        }

        //set ID for all objects

        int i=0;
        for(int j=0; j<touchDataObject.length; j++){
            while(i<linesTable.length){
                if(linesTable[i].equals(userID)){
                    touchDataObject[j].setDate(linesTable[i+1]);
                    i++;
                }
                if(linesTable[i].equals("release")){
                    i++;
                    long[] releaseTable = new long[56];
                    int n=0;
                        for(n=0; n<releaseTable.length; n++){
                            releaseTable[n]=Long.parseLong(linesTable[i+n]);
                            //System.out.println(releaseTable[n]);
                        }
                    touchDataObject[j].setRelease(releaseTable);
                    i+=n;

                }
                if(linesTable[i].equals("press")){
                    i++;
                    long[] pressTable = new long[55];
                    int n=0;
                        for(n=0; n<pressTable.length; n++){
                            pressTable[n]=Long.parseLong(linesTable[i+n]);
                        }
                    touchDataObject[j].setPress(pressTable);
                    i+=n;
                }
                else{
                    i++;
                }

            }
            //touchDataObject[j].countIntervals();
            j++;
        }
        System.out.println(objectCounter);
        //objectcounter

    }*/


}
