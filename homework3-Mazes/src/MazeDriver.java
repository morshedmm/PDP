import mazes.Maze;
import mazes.PerfectMaze;
import mazes.Player;
import mazes.RoomMaze;
import mazes.WrappedRoomMaze;

/**
 * Driver class.
 *
 */
public class MazeDriver {
  
  /**
   * Driver function.
   * @param args used for command line
   */
  public static void main(String[] args) {
    
    //Perfect Maze with destination
    if (args.length == 7 && Integer.parseInt(args[0]) == 1) {
      Maze myPerfectMaze = new PerfectMaze(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      System.out.println("Doors Created:");
      System.out.println(myPerfectMaze.toString());
      Player player = new Player(Integer.parseInt(args[3]), 
          Integer.parseInt(args[4]), Integer.parseInt(args[5]), 
          Integer.parseInt(args[6]), myPerfectMaze);
      player.moveAll(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      System.out.println("Players locations and gold:");
      System.out.println(player.toString());
    }
    
    // Wrapped Room Maze
    if (args.length == 6 && Integer.parseInt(args[0]) == 3) {
      Maze myWrappedRoomMaze = new WrappedRoomMaze(Integer.parseInt(args[1]), 
          Integer.parseInt(args[2]), Integer.parseInt(args[3]));
      System.out.println("Doors Created:");
      System.out.println(myWrappedRoomMaze.toString());
      Player player = new Player(Integer.parseInt(args[4]), Integer.parseInt(args[5]), 
          myWrappedRoomMaze);
      player.moveAll(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      System.out.println("Players locations and gold:");
      System.out.println(player.toString());
      
    }
    
    // Room Maze
    if (args.length == 6 && Integer.parseInt(args[0]) == 2) {
      Maze myRoomMaze = new RoomMaze(Integer.parseInt(args[1]), 
          Integer.parseInt(args[2]), Integer.parseInt(args[3]));
      System.out.println("Doors Created:");
      System.out.println(myRoomMaze.toString());
      Player player = new Player(Integer.parseInt(args[4]), Integer.parseInt(args[5]), 
          myRoomMaze);
      player.moveAll(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      System.out.println("Players locations and gold:");
      System.out.println(player.toString());
      
    }
  }

}
