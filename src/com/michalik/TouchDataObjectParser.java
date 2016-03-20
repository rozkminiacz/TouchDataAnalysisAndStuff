package com.michalik;

/**
 * Created by michalik on 19.03.16.
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
    public void parseRawDataToObjects(){

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
                    int[] releaseTable = new int[56];
                    int k=0;
                    while(!linesTable[i].equals("endOfRelease")){
                        releaseTable[k]=Integer.parseInt(linesTable[i]);
                        i++;
                        k++;
                    }
                }
                if(linesTable[i].equals("press")){
                    i++;
                    int[] pressTable = new int[56];
                    int k=0;
                    while(!linesTable[i].equals("endOfPress")){
                        pressTable[k]=Integer.parseInt(linesTable[i]);
                        i++;
                        k++;
                    }
                }
                else{
                    i++;
                }
            j++;
            }

        }
        System.out.println(objectCounter);
        //objectcounter

    }

}
