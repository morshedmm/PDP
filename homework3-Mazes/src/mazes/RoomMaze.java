package mazes;

/**
 * Represents a room maze.
 * 
 */
public class RoomMaze extends AbstractRoomMaze {
  
  /**
   * Builds a room maze.
   * @param numOfRows number of rows in integer
   * @param numOfCols number of columns in integer
   * @param remainingWalls wall remaining in integer
   * @throws IllegalArgumentException if arguments are not legal
   */
  public RoomMaze(int numOfRows, int numOfCols, int remainingWalls)
      throws IllegalArgumentException {
    
    super(numOfRows, numOfCols, remainingWalls);
    
    if (remainingWalls > (numOfRows 
        * (numOfCols - 1) + (numOfCols * (numOfRows - 1)) - numOfRows * numOfCols + 1)) {
      throw new IllegalArgumentException("One or more Maze parameters are Illegal!!");
    }
    this.addLocations();
    this.createWalls();
    this.addRoomMazeDoors();
  }

}
