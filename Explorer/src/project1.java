//File: project1.java
//Description: Main FIle for project1
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Project1                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class project1 {

    /* Makes item map that prints description of item */
    public static Map<String, String> MakeItemMap(){
        Map<String, String> itemMap = new HashMap<>();
        File fMmap = new File("items.txt");
        try (Scanner sMmap = new Scanner(fMmap)){
            while(sMmap.hasNextLine()) {
                String line = sMmap.nextLine();
                //System.out.println(line);

                String[] separated = line.split(":", 2);

                if (separated.length > 1) { // In case if there are spaces in the file, no out of bounds caused
                    //System.out.println(separated[0] + "----Middle part-----" + separated[1]);
                    itemMap.put(separated[0].toLowerCase(), separated[1]);
                }
            }
            //if something goes wrong, throw new RuntimeException("OOPS");
        } catch (FileNotFoundException e){
            System.out.println("Could not open file");
        }
        return itemMap;
    }
    /*Prints the commands available to the player */
    public static void print_commands(){
        File f1 = new File("commandshelp.txt");
        try (Scanner s2 = new Scanner(f1)){
            while(s2.hasNextLine())
                System.out.println(s2.nextLine());

            //throw new RuntimeException("OOPS"); // I forgot why this exception is thrown
        } catch (FileNotFoundException e){
            System.out.println("Could not open file");
        }
    }
    /*Main game program*/
    public static void main(String[] args){
        boolean quitReady = false;

        Map<String, String> itemMap= new HashMap<>();
        itemMap = MakeItemMap();

        Scanner S = new Scanner(System.in);

        System.out.println("This is a Dungeon game where you will have to find your way out " +
                "of the Dungeon\n");
        System.out.println("Remember you can always enter 'commands' and find out the commands " +
                "that you can use\nGood Luck and Have Fun");

        int rowIndex = 0;
        int colIndex = 0;
        Boolean inRoom = false;
        HashSet<Integer> accessibleIndex = new HashSet<>();
        Boolean exit = false;

        Floor Floor1 = build_floor1();
        Player mainPlayer = new Player();
        mainPlayer.pItems.add("hammer");


        while(!quitReady) {
            //System.out.println(rowIndex + colIndex);
            //System.out.println("Right now you are in " + Floor1.rooms[rowIndex][colIndex].toString());
            if (!inRoom) {
                System.out.println(Floor1.rooms[colIndex][rowIndex].getsDescription());
                inRoom = true;
            }
            if(!Floor1.rooms[colIndex][rowIndex].hasBeenVisited) {
                System.out.println(Floor1.rooms[colIndex][rowIndex].getlDescription());
                Floor1.rooms[colIndex][rowIndex].hasBeenVisited = true;
            }

                if(rowIndex == 7 && colIndex == 3) {
                    String choice;
                    System.out.println("You found the exit; But it needs the key \n Do you have it ? (y/n)");
                    choice = S.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        if (mainPlayer.pItems.contains("key")){
                            System.out.println("Congratulations, You exited the dungeon");
                            exit = true;
                        }else {
                            System.out.println("You don't have the key");
                        }
                    }
                    if (!exit)
                        System.out.println("Go back, find the key from the Dungeon and come back");
                    else
                        break;
                }

            System.out.print(">");
            //int X = S.nextInt();
            String CommandInput;
            CommandInput = S.nextLine();
            String[] Commands = CommandInput.split("\\s+", 2);
            if (Commands.length == 1) {
                System.out.println(Commands[0]);
                switch (Commands[0].toLowerCase()) {
                    case "quit":
                        System.out.println("You Entered quit \nAre you sure you want to quit? y/n: ");
                        if (S.next().equalsIgnoreCase("Y")) {
                            quitReady = true;
                            break;
                        } else {
                            S.nextLine();
                            break;
                        }
                    case "commands":
                        print_commands();
                        break;
                    case "look":
                        System.out.println(Floor1.rooms[colIndex][rowIndex].getsDescription());
                        System.out.println(Floor1.rooms[colIndex][rowIndex].getlDescription());
                        break;
                    case "items":
                        System.out.println("You have " + mainPlayer.pItems.toString() + " items" );
                        break;
                    default:
                        System.out.println("Wrong input command: Press Commands for help");
                }
            } else {
                switch (Commands[0].toLowerCase()) {

                    case "go":
                        switch (Commands[1].toLowerCase()){
                            // I have directions as numbers 9,10,12,1,3,5,6,7 in a hashet
                            case "north":
                                //Floor1.rooms[colIndex][rowIndex].hasBeenVisited = true;
                                //accessibleIndex.contains(10*(colIndex-1 + rowIndex)) &&
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(12)) {
                                    //System.out.println(colIndex + rowIndex);
                                    //System.out.println(Floor1.rooms[colIndex][rowIndex].accessIndex.toString());
                                    colIndex--;
                                    inRoom = false;
                                } else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "south":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(6)) {
                                    colIndex++;
                                    inRoom = false;
                                } else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "east":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(3)) {
                                    rowIndex++;
                                    inRoom = false;
                                }else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "west":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(9)) {
                                    rowIndex--;
                                    inRoom = false;
                                }else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "northeast":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(2)) {
                                    colIndex--;
                                    rowIndex++;
                                    inRoom = false;
                                }else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "northwest":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(10)) {
                                    colIndex--;
                                    rowIndex--;
                                    inRoom = false;
                                }else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "southeast":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(5)) {
                                    rowIndex++;
                                    colIndex++;
                                    inRoom = false;
                                }else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            case "southwest":
                                if(Floor1.rooms[colIndex][rowIndex].accessIndex.contains(7)) {
                                    rowIndex--;
                                    colIndex++;
                                    inRoom = false;
                                }else {
                                    System.out.println("You cannot go there ");
                                }
                                break;
                            default:
                                System.out.println("Invalid Direction " +
                                        "or Wrong direction syntax; Type 'commands' for help");
                                break;
                        }
                        break;
                    case "pickup":
                        System.out.println("Searching for " + Commands[1]);
                        if (Floor1.rooms[colIndex][rowIndex].rItem.equalsIgnoreCase(Commands[1])) {
                            String current_item = Floor1.rooms[colIndex][rowIndex].rItem;
                            System.out.println("Item " +  current_item + " Kept inside bag");
                            mainPlayer.pItems.add(current_item);
                        } else {
                            System.out.println("This item is not there");
                        }
                            //System.out.println("No Item selected");
                        break;
                    case "examine":

                        if(!itemMap.containsKey(Commands[1].toLowerCase()) ||
                            !mainPlayer.pItems.contains(Commands[1].toLowerCase()))
                            System.out.println("Check Item name");
                        else
                            System.out.println(Commands[1] + " : " + itemMap.get(Commands[1].toLowerCase()));
                        break;
                    default:
                        System.out.println("Syntax/Input Wrong: Press Commands for help");
                }

            }

        }
        System.out.println("Hello World!");
    }
    /*Builds the floor of the game*/

    public static Floor build_floor1(){
        Floor floor1 = new Floor(8,8);
        File f1Builder = new File("rooms.txt");
        try (Scanner f1File = new Scanner(f1Builder)){
            while(f1File.hasNextLine()) {
                String line = f1File.nextLine();

                String[] roomInfo = line.split(":");
                //System.out.println("Number of words here is " + roomInfo.length);

                if (roomInfo.length > 2) { // In case if there are spaces in the file, no out of bounds caused
                    int Index = Integer.parseInt(roomInfo[0]);
                    int i = Index/10;
                    int j = Index % 10;

                    if (roomInfo.length == 5) {
                        //System.out.println("accessed indexes " + roomInfo[4]);
                        String[] accessString = roomInfo[4].split(",");
                        HashSet<Integer> accessIndInt = new HashSet<>();
                        if(accessString.length > 1) {
                            for (int p = 0; p < accessString.length; p++) {
                                accessIndInt.add(Integer.parseInt(accessString[p]));
                            }
                        } else { accessIndInt.add(Integer.parseInt(roomInfo[4]));}
                        floor1.rooms[i][j] = new Room(roomInfo[0], roomInfo[1], roomInfo[2], roomInfo[3], accessIndInt);

                    } else {
                        //System.out.println("accessed indexes " + roomInfo[3]);
                        String[] accessString = roomInfo[3].split(",");
                        HashSet<Integer> accessIndInt = new HashSet<>();
                        if(accessString.length > 1) {
                            for (int p = 0; p < accessString.length; p++) {
                                accessIndInt.add(Integer.parseInt(accessString[p]));
                            }
                        } else { accessIndInt.add(Integer.parseInt(roomInfo[3]));}
                        floor1.rooms[i][j] = new Room(roomInfo[0], roomInfo[1], roomInfo[2], accessIndInt);

                    }
                }
            }
            //if something goes wrong, throw new RuntimeException("OOPS");
        } catch (FileNotFoundException e){
            System.out.println("Could not open file");
        }
        return floor1;
    }
}
