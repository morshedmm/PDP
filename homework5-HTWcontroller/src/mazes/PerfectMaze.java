package mazes;

/**
 * Represents a Perfect Maze.
 * 
 */
public class PerfectMaze extends AbstractMaze {
  
  /**
   * Builds a perfect maze.
   * @param numOfRows number of rows for the maze
   * @param numOfCols number of columns for the maze
   * @throws IllegalArgumentException if arguments are not legal
   */
  public PerfectMaze(int numOfRows, int numOfCols, int numOfPits, int numOfBats) 
      throws IllegalArgumentException {
    super(numOfRows, numOfCols, numOfPits, numOfBats);
    this.addLocations();
    this.createWalls();
    this.addMazeDoors();
    // Changed here to build the game as objects should only sit in cave not tunnel,
    // so objects are assigned after maze doors are added.
    this.assignObjects();
  }

}
