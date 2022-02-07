import java.util.*;
import java.io.*;

/* 
 GameManager Class ,It manages the start,loading,and closing of the game.
 */


public class GameController {
   // Instance variables
   private GameBoard board;       // The actual 2048 board
   



   /* 
    GameManager
a
    It generates a new game

    *int boardSize:specify the size of the grid
    *             String outputBoard:specify where to save the game
    *             Random random: a random generator
    * Return: void
    *
    */

   public GameController(int boardSize, String outputBoard, Random random) {
      System.out.println("Generating a New Board");
      if(boardSize>=2){
         this.board=new GameBoard(boardSize,random);}
      //account for the case when the boardsize value is less than 2
      else
      {System.out.println("Use the default board size:4.");
         this.board=new GameBoard(4,random);}


   }

   
   /* 
   // Takes in input from the user to specify moves to run for ony
   // valid moves are:
   //      u - Move up
   //      d - Move Down
   //      l - Move Left
   //      r - Move Right
   //      q - Quit 
   //
   // is there any change of getting invalid command from user we acknowledge the user thst it is an invalid command
   //
  
   // we  print "Game Over!" to the terminal ,if the game is over!
    */


   public void play() throws IOException {

      //print the controls

      this.printControls();
      //print out the current state of the 2048 board to the console 
      System.out.println(board.toString());

      while(board.findGameOver()==false) {


         Scanner userInput=new Scanner(System.in);

         System.out.println("Enter the direction  to move the tile");
         String userIn=userInput.next();

         //checking the input or validation

         while ((!userIn.equals("u"))&&(!userIn.equals("d"))&&
               (!userIn.equals("r"))&&(!userIn.equals("l"))
               &&(!userIn.equals("q")))
         {  this.printControls();

            System.out.println("Invalid direction input,please re-enter");

            userIn=userInput.next();
         } 


         DirectionMovement dir=null;

         switch (userIn) {
            case "u":
               dir = DirectionMovement.UP;
               break;
            case "d":
               dir = DirectionMovement.DOWN;
               break;
            case "l":
               dir = DirectionMovement.LEFT;
               break;
            case "r":
               dir = DirectionMovement.RIGHT;
               break;
            case "q":
               return;
         }

         //check whether  the move is valid or not
         while(this.board.isMovementPossible(dir)==false) 

         {  

            //prompt the user to re-enter controls if the move is invalid
            System.out.println("Could not  move in this direction");
            this.printControls();
            System.out.println(board.toString());



            userIn=userInput.next();
            while ((!userIn.equals("u"))&&(!userIn.equals("d"))&&
                  (!userIn.equals("r"))&&(!userIn.equals("l"))&&
                  (!userIn.equals("q")))

             //prompt the user to re-enter controls with an another command if the move is invalid

            {  this.printControls();

               System.out.println("Invalid control input,please re-enter");

               userIn=userInput.next();
            } 



            switch (userIn) {
               case "u":
                  dir = DirectionMovement.UP;
                  break;
               case "d":
                  dir = DirectionMovement.DOWN;
                  break;
               case "l":
                  dir = DirectionMovement.LEFT;
                  break;
               case "r":
                  dir = DirectionMovement.RIGHT;
                  break;
               case "q":
                  return;
            }


         }
         //finally,if the input is valid,move the tile and add a random 
         //new tile
         this.board.movement(dir);
         this.board.addingRandomTile();

         //print the updated board
         System.out.println(board.toString()); }


      //if we out of the while the game is over
      System.out.println("Game Over!");
     
      //we exit the method
      return;   

   }



   private void printControls() {
      System.out.println("  Controls:");
      System.out.println("    u - Move Up");
      System.out.println("    d - Move Down");
      System.out.println("    l - Move Left");
      System.out.println("    r - Move Right");
      System.out.println("    q - Quit");
      System.out.println();
   }
}
