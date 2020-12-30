package mazes;

/**
 * Gives generic implementation of different room mazes.
 * 
 */
public abstract class AbstractRoomMaze extends AbstractMaze {
  
  /**
   * Creates a Room Maze.
   * @param numOfRows number of columns of the maze.
   * @param numOfCols number of rows of the maze.
   * @param remainingWalls walls remaining after the maze is built
   * @throws IllegalArgumentException if dimensions are illegal
   */
  public AbstractRoomMaze(int numOfRows, int numOfCols, int remainingWalls)
      throws IllegalArgumentException {
    super(numOfRows, numOfCols, remainingWalls);
  }
  
  /**
   * Gives common implementations for adding rooms of all room mazes.
   */
  public void addRoomMazeDoors() {
    this.addMazeDoors();
    int doorsToAdd = this.redundantDoors.size() - this.remainingWalls;
    
    while (doorsToAdd > 0) {
      int randNumber = this.generateOneRandNumber(this.redundantDoors.size());
      this.addDoor(this.redundantDoors.get(randNumber).get(0), 
          this.redundantDoors.get(randNumber).get(1));
      this.redundantDoors.remove(randNumber);
      doorsToAdd--;
    }
  }

}
