import java.io.IOException;
import java.io.InputStreamReader;
import mazes.GameController;
import mazes.GameControllerInterface;
import mazes.GameView;
import mazes.GameViewInterface;
import mazes.Maze;
import mazes.RoomMaze;

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
    
    
    Maze maze = new RoomMaze();
    
    Readable reader = new InputStreamReader(System.in);
    GameControllerInterface controller = new GameController(reader, System.out);
    
    
    
    
    if (args[0].equals("--gui")) {
      GameViewInterface view = new GameView("Hunt The Wumpus");
      controller.start(view, maze);
    } else if (args[0].equals("--text")) {
      controller.start(maze);
    }
    
    
    
    
      
  }

}
