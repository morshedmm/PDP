package mazes;

import java.awt.Point;

/**
 * Represents a wrapped room maze. 
 * 
 */
public class WrappedRoomMaze extends AbstractRoomMaze {
  
  /**
   * Builds a wrapped room maze.
   * @param numOfRows number of rows
   * @param numOfCols number of columns
   * @param remainingWalls walls remaining
   * @throws IllegalArgumentException if illegal argument is given
   */
  public WrappedRoomMaze(int numOfRows, int numOfCols, int remainingWalls)
      throws IllegalArgumentException {
    super(numOfRows, numOfCols, remainingWalls);
    
    this.addLocations();
    this.createWalls();
    this.addRoomMazeDoors();
    
  }
  
  @Override
  public void createWalls() {
    
    for (int idx = 0; idx < this.locationIndex.size(); idx++) {
      int x = (int) this.locationIndex.get(idx).getLocationCoord().getX();
      int y = (int) this.locationIndex.get(idx).getLocationCoord().getY();
      if (x + 1 < this.numOfCols) {
        Location newLoc1 = new Location(new Point(x + 1, y), new NoPrize());
        for (int jdx = 0; jdx < this.locationIndex.size(); jdx++) {
          if (this.locationIndex.get(jdx).equals(newLoc1)) {
            this.listOfWalls.add(new Walls(this.locationIndex.get(idx), 
                this.locationIndex.get(jdx)));
          }
        }
      } else {
        Location newLoc1 = new Location(new Point(0, y), new NoPrize());
        for (int jdx = 0; jdx < this.locationIndex.size(); jdx++) {
          if (this.locationIndex.get(jdx).equals(newLoc1)) {
            this.listOfWalls.add(new Walls(this.locationIndex.get(idx), 
                this.locationIndex.get(jdx)));
          }
        }
      }
      if (y + 1 < this.numOfRows) {
        Location newLoc2 = new Location(new Point(x, y + 1), new NoPrize());
        for (int jdx = 0; jdx < this.locationIndex.size(); jdx++) {
          if (this.locationIndex.get(jdx).equals(newLoc2)) {
            this.listOfWalls.add(new Walls(this.locationIndex.get(idx), 
                this.locationIndex.get(jdx)));
          }
        }
      } else {
        Location newLoc2 = new Location(new Point(x, 0), new NoPrize());
        for (int jdx = 0; jdx < this.locationIndex.size(); jdx++) {
          if (this.locationIndex.get(jdx).equals(newLoc2)) {
            this.listOfWalls.add(new Walls(this.locationIndex.get(idx), 
                this.locationIndex.get(jdx)));
          } 
        }
    
    
      }
    }
  }

}
