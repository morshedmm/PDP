import java.io.IOException;
import java.io.InputStreamReader;
import mazes.Game;
import mazes.GameController;
import mazes.Player;

/**
 * Driver class to launching the process and giving control to Controller.
 *
 */
public class MazeDriver {
  
  /**
   * Driver function.
   * @param args used for command line if any
   * @throws IOException if inputs are not legal
   */
  public static void main(String[] args) throws IOException {
    
    
    Game player = new Player();
    
    Readable reader = new InputStreamReader(System.in);
    GameController controller = new GameController(reader, System.out);
    
    controller.start(player);
      
  }

}
