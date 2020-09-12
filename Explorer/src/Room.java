//File: Room.java
//Description: Room class file for project 1
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Project1                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Room {
    String roomName;
    String sDescription;
    String lDescription;
    String rItem;

    Boolean inGame;
    Boolean hasBeenVisited;
    HashSet<Integer> accessIndex;

    int rX;
    int rY;

    public String getRoomName(){
        return roomName;
    }
    public String getsDescription(){
        return sDescription;
    }
    public String getlDescription(){
        return lDescription;
    }
    public Room(Boolean inGame){
        this.inGame = inGame = false;
    }
    public Room(){
        this.inGame = false;
    }

    public Room(String roomName, String sDescription, String lDescription, HashSet<Integer> accessIndex){
        this.roomName = roomName;
        this.sDescription = sDescription;
        this.lDescription = lDescription;
        this.hasBeenVisited = false;
        this.rItem = new String();
        this.accessIndex = accessIndex;
    }
    public Room(String roomName, String sDescription, String lDescription, String rItem, HashSet<Integer> accessIndex){
        this.roomName = roomName;
        this.sDescription = sDescription;
        this.lDescription = lDescription;
        this.hasBeenVisited = false;
        this.rItem = rItem;
        this.accessIndex = accessIndex;
    }

    @Override
    public String toString(){
        return roomName + " " + sDescription + " " + lDescription + " HAS "
                + rItem + "items accessed from" + accessIndex.toString() + "\n";
    }

    public int getrX(){ return rX; }
    public int getrY(){ return rY; }
}
