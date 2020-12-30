package mazes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
  public WrappedRoomMaze(int numOfRows, int numOfCols, int remainingWalls, 
      int numOfPits, int numOfBats)
      throws IllegalArgumentException {
    
    super(numOfRows, numOfCols, remainingWalls, numOfPits, numOfBats);
    if (remainingWalls > (numOfRows * numOfCols - 1)) {
      throw new IllegalArgumentException("One or more Maze parameters are Illegal!!");
    }
    
    this.addLocations();
    this.createWalls();
    this.addRoomMazeDoors();
    // Changed here to build the game as objects should only sit in cave not tunnel,
    // so objects are assigned after maze doors are added.
    this.assignObjects();
    
  }
  
  @Override
  public List<Walls> createWalls() {
    
    List<Walls> listOfWalls = new ArrayList<Walls>();
    
    List<Location> locationIndex = new ArrayList<Location>();
    for (Entry<Location, List<Location>> entry : this.adjaLocation.entrySet()) {
      locationIndex.add(entry.getKey());
    }
    
    for (int idx = 0; idx < locationIndex.size(); idx++) {
      int x = (int) locationIndex.get(idx).getLocationCoord().getX();
      int y = (int) locationIndex.get(idx).getLocationCoord().getY();
      if (x + 1 < this.numOfCols) {
        Location newLoc1 = new Location(new Point(x + 1, y));
        for (int jdx = 0; jdx < locationIndex.size(); jdx++) {
          if (locationIndex.get(jdx).equals(newLoc1)) {
            listOfWalls.add(new Walls(locationIndex.get(idx), 
                locationIndex.get(jdx)));
          }
        }
      } else {
        Location newLoc1 = new Location(new Point(0, y));
        for (int jdx = 0; jdx < locationIndex.size(); jdx++) {
          if (locationIndex.get(jdx).equals(newLoc1)) {
            listOfWalls.add(new Walls(locationIndex.get(idx), 
                locationIndex.get(jdx)));
          }
        }
      }
      if (y + 1 < this.numOfRows) {
        Location newLoc2 = new Location(new Point(x, y + 1));
        for (int jdx = 0; jdx < locationIndex.size(); jdx++) {
          if (locationIndex.get(jdx).equals(newLoc2)) {
            listOfWalls.add(new Walls(locationIndex.get(idx), 
                locationIndex.get(jdx)));
          }
        }
      } else {
        Location newLoc2 = new Location(new Point(x, 0));
        for (int jdx = 0; jdx < locationIndex.size(); jdx++) {
          if (locationIndex.get(jdx).equals(newLoc2)) {
            listOfWalls.add(new Walls(locationIndex.get(idx), 
                locationIndex.get(jdx)));
          } 
        }
    
    
      }
    }
    return listOfWalls;
  }

}
