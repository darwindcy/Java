//File: Floor.java
//Description: Floor Class file for project1
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Project1                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;

public class Floor {
    private int x;
    private int y;
    Room[][] rooms;
    public Floor(){
        this.rooms = new  Room[1][1];
    }

    public Floor(int x, int y){
        this.rooms = new Room[x][y];
    }

}
