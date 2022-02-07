import java.util.*;
/* 
 * Name: Board Class
 * Purpose: Creatation and  operates the board for  2048      
 * */


public class GameBoard {
   public final int TILES_SIZE = 2;
   public final int PROBABILITY_OF_TWO = 90;
   public final int MATRIX;


   private final Random randomObj;
   private int[][] grid;
   private int score;


   /* 
    * P Creates a new board with random tiles
    *int boardSize:set the size of the grid; Random random:a 
    *           random(number) generator       
    * */ 

   public GameBoard(int boardSize, Random random) {

      this.randomObj =random ; 
      this.MATRIX = boardSize;
      this.grid=new int[boardSize][boardSize];
      this.score=0;
      for(int i=0;i<TILES_SIZE;i++)
      {this.addingRandomTile();}

   }

 
  
   /* 
    * Name: addRandomTile 
    * Purpose:  Adds a random tile (of value 2 or 4) to a
    random empty space on the board      
    * */

   public void addingRandomTile() {
      //first count the number of available tiles
      int val=0;
      for(int row=0; row<MATRIX;row++){
         for(int col=0;col<MATRIX;col++){
            if(this.grid[row][col]==0)
            {
               val+=1;
            }

         }
      }

      //we exit the method if the count = 0,after cheking all the tiles that are available
      if(val==0)
      {return;}



      //get a random int called LOC (0~count-1)
      int LOC=this.randomObj.nextInt(val);

      //get a random int called value (0~99)
      int value=this.randomObj.nextInt(100);

      //we note the number of spaces with numEmp

      //we starts from "-1"  because we number the first empty space as "0"
      int numEmp=-1;
      int r=0;
      while(r<MATRIX){
         int c=0;
         while(c<MATRIX){
            if(this.grid[r][c]==0)
            { numEmp++;
            }
            // if numbering of empty space equals LOC value 
            //add a random tile

            if(numEmp==LOC)
            { //2 will be placed
               if(value<PROBABILITY_OF_TWO)
               {this.grid[r][c]=2;}
               else
                  // 4 will be placed
               {this.grid[r][c]=4;}
         }
         c++;
      }
      r++;
   }
      // for(int row=0; row<MATRIX;row++){
      //    for(int col=0;col<MATRIX;col++) {
      //       if(this.grid[row][col]==0)
      //       { numEmp++;
      //       }
      //       // if numbering of empty space equals LOC value 
      //       //add a random tile

      //       if(numEmp==LOC)
      //       { //2 will be placed
      //          if(value<PROBABILITY_OF_TWO)
      //          {this.grid[row][col]=2;}
      //          else
      //             // 4 will be placed
      //          {this.grid[row][col]=4;}
      //       }
      //    }

      // }


   }

   /* 
    *  rotate class

    [rotates the board by 90 degrees clockwise or anti-clockwise
    If rotateClockwise == true, rotates the board clockwise ]

     else rotates the board counter-clockwise        
    * */


   public void rotateGrid(boolean rotateClockwise) {
      int[][] rotGrid=new int[MATRIX][MATRIX];

      //rotate the matrix clockwise
      if(rotateClockwise)
      { for(int row=0;row<MATRIX;row++)
         {for(int col=0;col<MATRIX;col++)
            {rotGrid[col][MATRIX-1-row]=this.grid[row][col];}
         }

         this.grid=rotGrid;   
      }

      //rotate the matrix anti-clockwise
      else
      { for(int r=0;r<MATRIX;r++)
         {for(int c=0;c<MATRIX;c++)
            {rotGrid[MATRIX-1-c][r]=this.grid[r][c];}
         }

         this.grid=rotGrid;   

      }
   }


   //moveLeft method , it is an helper method for move() method

   private void leftMove()
   {  //here we use count to move the tiles  one by one to the left
      for(int row=0;row<MATRIX;row++){
         int count=0;
         for(int col=0;col<MATRIX;col++)

         {
            if(this.grid[row][col]!=0)
            {   this.grid[row][0+count]=this.grid[row][col];

               // once the tile is moved we should empty the original tile
               //may get another number to the tile

               if(0+count!=col)
               {  this.grid[row][col]=0;}
               count++;}
         }

      }

      for(int r=0;r<MATRIX;r++)
      {for(int c=0;c<MATRIX-1;c++)
         {if(this.grid[r][c]==this.grid[r][c+1])
            
            //first rule we add two tiles with the same value and double the counter tile 
            {this.grid[r][c]+=this.grid[r][c+1];
               this.grid[r][c+1]=0;

               // update the score every time we have a move 
               //adding score is based on the numbers on the tiles and various factors includes
               int sum=this.grid[r][c];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the first for loop
      for(int r=0;r<MATRIX;r++){
         int val=0;
         for(int c=0;c<MATRIX;c++)

         {
            if(this.grid[r][c]!=0)
            {   this.grid[r][0+val]=this.grid[r][c];

               //value in the tile position is emptied once the tile is moved
               if(0+val!=c)
               {  this.grid[r][c]=0;}
               val++;}
         }

      }


   }

   //moveRight ,helper method for move() method


   private void rightMove()
      {  //here we use count to move the tiles  one by one to the right

      for(int r=0;r<MATRIX;r++){
         int count=0;

         for(int c=MATRIX-1;c>=0;c--)

         {
            if(this.grid[r][c]!=0)
            {   this.grid[r][MATRIX-1-count]=this.grid[r][c];

               // once the tile is moved we should empty the original tile
               //may get another number to the tile

               if(MATRIX-1-count!=c)
               { this.grid[r][c]=0;}
               count++;}

         }

      }

      for(int r=0;r<MATRIX;r++)
      {for(int c=MATRIX-1;c>0;c--)
         {if(this.grid[r][c]==this.grid[r][c-1])
            //add the two tiles with the same value
            {this.grid[r][c]+=this.grid[r][c-1];
               this.grid[r][c-1]=0;
               //update the score ONCE!
               int sum=this.grid[r][c];
               this.score+=sum;

               break;

            } } }

   
            //after adding two tiles, repeat the first for loop
      for(int r=0;r<MATRIX;r++){
         int count=0;

         for(int c=MATRIX-1;c>=0;c--)

         {
            if(this.grid[r][c]!=0)
            {   this.grid[r][MATRIX-1-count]=this.grid[r][c];
               if(MATRIX-1-count!=c)
               {this.grid[r][c]=0;}
               count++;}

         }

      }


   }
   //Name:moveUp,helper method for move() method
   //Parameter:none
   //Return:void


   private void Up_Move() 
   {  //use count to move the tiles to the top one by one
      for(int c=0;c<MATRIX;c++){
         int count=0;

         for(int r=0;r<MATRIX;r++)
         {
            if(this.grid[r][c]!=0)
            {   this.grid[0+count][c]=this.grid[r][c];
               //empty the original tile position after tile is moved
               if(0+count!=r)
               { this.grid[r][c]=0;}
               count++;}

         }

      }

      for(int c=0;c<MATRIX;c++)
      {for(int r=0;r<MATRIX-1;r++)
         {if(this.grid[r][c]==this.grid[r+1][c])
            //add the two tiles with the same value
            {this.grid[r][c]+=this.grid[r+1][c];
               this.grid[r+1][c]=0;
               //update the score ONCE!
               int sum=this.grid[r][c];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the same first for loop
      //in this method to move all the tiles to the top
      for(int c=0;c<MATRIX;c++){
         int count=0;

         for(int r=0;r<MATRIX;r++)

         {
            if(this.grid[r][c]!=0)
            {   this.grid[0+count][c]=this.grid[r][c];
               //empty the original tile position after tile is moved
               if(0+count!=r)
               { this.grid[r][c]=0;}
               count++;}

         }

      }

   }

   //Name:moveDown,Purpose:helper method for move() method
   

   private void Down_Move()   {  //use count to move the tiles to the bottom
      //one by one
      for(int c=0;c<MATRIX;c++){
         int count=0;

         for(int r=MATRIX-1;r>=0;r--)

         {
            if(this.grid[r][c]!=0)
            {   this.grid[MATRIX-1-count][c]=this.grid[r][c];
               //empty the original tile position after tile is moved
               if(MATRIX-1-count!=r)
               { this.grid[r][c]=0;}
               count++;}

         }

      }

      for(int c=0;c<MATRIX;c++)
      {for(int r=MATRIX-1;r>0;r--)
         {if(this.grid[r][c]==this.grid[r-1][c])
            //add the two tiles with the same value
            {this.grid[r][c]+=this.grid[r-1][c];
               this.grid[r-1][c]=0;
               //update the score ONCE!
               int sum=this.grid[r][c];
               this.score+=sum;

               break;

            } } }

      //after adding two tiles, repeat the same first for loop
      //in this method to move all the tiles to the bottom
      for(int c=0;c<MATRIX;c++){
         int count=0;

         for(int r=MATRIX-1;r>=0;r--)

         {
            if(this.grid[r][c]!=0)
            {   this.grid[MATRIX-1-count][c]=this.grid[r][c];
               //the original tile is emptied once it is moved
               if(MATRIX-1-count!=r)
               { this.grid[r][c]=0;}
               count++;}

         }

      }

   }





   //Name:move,performs a move operation
   //Return:boolean:return true if the move is successful
   //otherwise,return false

   public boolean movement(DirectionMovement direction) {
      //check whether the input direction enums and perform the move operation
      if(direction.equals(DirectionMovement.LEFT))
      {this.leftMove();
         return true;}
      if(direction.equals(DirectionMovement.RIGHT))
      {this.rightMove();
         return true;}
      if(direction.equals(DirectionMovement.UP))
      {this.Up_Move();
         return true;}
      if(direction.equals(DirectionMovement.DOWN))
      {this.Down_Move();
         return true;}
      //we return false if the move is un-successful
      return false;
   }




   //Name:isGameOver ,check to see if we have a game over
   

   public boolean findGameOver() {
      //check if there is no long any valid move left, then the game
      //is over.
      if(!this.isLeftMovePossible()&&!this.isRightMovePossible()&&!this.isUpMovePossible()
            &&!this.isDownMovePossible())
      {return true;}

      //return false when the game continues
      return false;
   }



//canMoveLeft,helper method for the canMove() method


   private boolean isLeftMovePossible() {

      for(int r=0;r<MATRIX;r++)


           //if there is a zero neighbour  the tiles can move down  or if it contains same value it can move left


      {for(int c=0;c<MATRIX-1;c++)
         {
            if(this.grid[r][c]==0&&this.grid[r][c+1]!=0)
            {return true;}
            if(this.grid[r][c]!=0&&this.grid[r][c]
                  ==this.grid[r][c+1])
            {return true;}
         }

      }
      return false;

   }
   //canMoveRight,helper method for the canMove() method

   private boolean isRightMovePossible(){
      for(int r=0;r<MATRIX;r++)
         
      //if there is a zero neighbour  the tiles can move down  or if it contains same value it can move right


      {for(int c=MATRIX-1;c>0;c--)
         {
            if(this.grid[r][c]==0&&this.grid[r][c-1]!=0)
            {return true;}
            if(this.grid[r][c]!=0&&this.grid[r][c]
                  ==this.grid[r][c-1])
            {return true;}

         }

      }
      return false; 

   }
   //Name:canMoveUp,helper method for the canMove() method



   private boolean isUpMovePossible(){
      for(int c=0;c<MATRIX;c++)
         

               //if there is a zero neighbour  the tiles can move down  or if it contains same value it can move up


      {for(int r=0;r<MATRIX-1;r++)
         {
            if(this.grid[r][c]==0&&this.grid[r+1][c]!=0)
            {return true;}
            if(this.grid[r][c]!=0&&this.grid[r][c]==
                  this.grid[r+1][c])
            {return true;}
         }

      }
      return false; }

   //canMoveDown,helper method for the canMove() method



   private boolean isDownMovePossible(){
      for(int c=0;c<MATRIX;c++)

         //if there is azero neighbour  the tiles can move down  or if it contains same value it can move down

      {for(int r=MATRIX-1;r>0;r--)
         {
            if(this.grid[r][c]==0&&this.grid[r-1][c]!=0)
            {return true;}
            if(this.grid[r][c]!=0&&this.grid[r][c]
                  ==this.grid[r-1][c])
            {return true;}
         }

      }
      return false; }




   //canMove
   //we use this method whether we can move in specified direction



   public boolean isMovementPossible(DirectionMovement direction) {

      //check if the board can move in the passed in direction
      if(direction.equals(DirectionMovement.LEFT)&&this.isLeftMovePossible())
      {return true;}
      if(direction.equals(DirectionMovement.RIGHT)&&this.isRightMovePossible())
      {return true;}
      if(direction.equals(DirectionMovement.UP)&&this.isUpMovePossible())
      {return true;}
      if(direction.equals(DirectionMovement.DOWN)&&this.isDownMovePossible())
      {return true;}


      // if it returns false ,the bord will not move
      return false;

   }




   public int[][] getGrid() {
      return grid;
   }


   //we return the score here
   public int getScore() {
      return score;
   }

   @Override
      public String toString() {
         StringBuilder outputString = new StringBuilder();
         outputString.append(String.format("Score: %d\n", score));
         for (int row = 0; row < MATRIX; row++) {
            for (int column = 0; column < MATRIX; column++)
               outputString.append(grid[row][column] == 0 ? "    -" :
                     String.format("%5d", grid[row][column]));

            outputString.append("\n");
         }
         return outputString.toString();
      }
}
