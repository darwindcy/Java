//File: Application
//Description: GUI chess Application Class
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Chess                                Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

public class Application {

    public static void main (String[] args){
        Application a = new Application();
        a.run();
    }

    public void run() {
        Chess game1 = new Chess();
        game1.playGame();
    }
}
