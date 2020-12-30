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
  public PerfectMaze(int numOfRows, int numOfCols) throws IllegalArgumentException {
    super(numOfRows, numOfCols);
    this.addLocations();
    this.createWalls();
    this.addMazeDoors();
  }

}
