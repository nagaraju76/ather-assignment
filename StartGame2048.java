import java.util.Random;
import java.io.IOException;

public class StartGame2048 {
    private static String outB;
    private static int sizeOfBoard;

    public static void main(String[] args) throws IOException {
        System.out.println("-------------Ather Assignment  2048 Game--------------");                     // welcoming to the game 

        Random randomObj = new Random();//RANDOM MODULE
  
        GameController gameVariable;
        
        System.out.println("creating a fresh Board");                         //generating the new boardsss
        gameVariable = new GameController(sizeOfBoard, outB, randomObj);

        gameVariable.play();
    }
}
