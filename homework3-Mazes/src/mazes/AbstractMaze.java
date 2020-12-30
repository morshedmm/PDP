package mazes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Implements generic maze interface.
 * 
 */
public class AbstractMaze implements Maze {
  
  protected int numOfRows;
  protected int numOfCols;
  protected int remainingWalls;
  protected static final float goldRatio = 0.2f;
  protected static final float thiefRatio = 0.1f;
  protected static final int goldVal = 10;
  protected static final double thiefFactor = 0.9;
  protected Map<Location, List<Location>> adjaLocation = new HashMap<Location, List<Location>>();
  protected List<Location> locationIndex = new ArrayList<Location>();
  protected List<List<Location>> redundantDoors = new ArrayList<List<Location>>();
  protected List<List<Location>> hallWays = new ArrayList<List<Location>>();
  protected List<Walls> listOfWalls = new ArrayList<Walls>();
  
  /**
   * Creates a maze for Perfect Maze.
   * @param numOfRows number of rows of the maze
   * @param numOfCols number of columns of the maze
   * @throws IllegalArgumentException if dimensions are not legal
   */
  public AbstractMaze(int numOfRows, int numOfCols)
      throws IllegalArgumentException {
    if (numOfRows <= 0 || numOfCols <= 0) {
      throw new IllegalArgumentException("Maze dimension must be greater than zero");
    }
    this.numOfRows = numOfRows;
    this.numOfCols = numOfCols;
    
  }
  
  /**
   * Creates a maze for Room Maze.
   * @param numOfRows number of rows of the maze
   * @param numOfCols number of columns of the maze
   * @param remainingWalls number of walls to remain for room maze
   * @throws IllegalArgumentException if dimensions are not legal
   */
  public AbstractMaze(int numOfRows, int numOfCols, int remainingWalls)
      throws IllegalArgumentException {
    if (numOfRows <= 0 || numOfCols <= 0 || remainingWalls < 0 || remainingWalls
        > 2 * numOfCols * numOfRows) {
      throw new IllegalArgumentException("One or more Maze parameters are Illegal!!");
    }
    this.numOfRows = numOfRows;
    this.numOfCols = numOfCols;
    this.remainingWalls = remainingWalls;
    
  }
  
  
  private List<Integer> generateNumbers(int locationNum, float ratio) {
    int count = 0;
    int bound = (int) Math.round(locationNum * ratio);
    List<Integer> randNumbers = new ArrayList<Integer>(Collections.nCopies(locationNum, 0));
    Random r = new Random();
    r.setSeed(123456789);
    while (count < bound) {
      int temp = r.nextInt(locationNum);
      if (randNumbers.get(temp) == 0) {
        randNumbers.set(temp, 1);
        count++;
      }
    }
    return randNumbers;
  }
  
  private int findHallWayForLocation(Location location) {
    for (int idx = 0; idx < this.hallWays.size(); idx++) {
      if (this.hallWays.get(idx).contains(location)) {
        return idx;
      }
    }
    return -1;
  }

  @Override
  public void addDoor(Location location1, Location location2) {
        
    this.adjaLocation.get(location1).add(location2);
    this.adjaLocation.get(location2).add(location1);
    int locationIndex1 = this.findHallWayForLocation(location1);
    int locationIndex2 = this.findHallWayForLocation(location2);
    
    if (locationIndex1 == -1 && locationIndex2 == -1) {
      List<Location> newList = new ArrayList<Location>();
      newList.add(location1);
      newList.add(location2);
      this.hallWays.add(newList);
    } else if (locationIndex1 == -1) {
      this.hallWays.get(locationIndex2).add(location1);
    } else if (locationIndex2 == -1) {
      this.hallWays.get(locationIndex1).add(location2);
    } else {
      this.hallWays.get(locationIndex1).addAll(0, this.hallWays.get(locationIndex2));
      this.hallWays.remove(locationIndex2);
    }

  }
 
  /*
   * Checks if in same wall, if they are, they will be added to redundantDoor 
   * if not already in redunDantDoor list.
   */
  private boolean checkIfInSameHallWay(Location loc1, Location loc2) {
    for (int idx = 0; idx < this.hallWays.size(); idx++) {
      if (this.hallWays.get(idx).contains(loc1) && this.hallWays.get(idx).contains(loc2)) {
        for (int jdx = 0; jdx < this.redundantDoors.size(); jdx++) {
          if (this.redundantDoors.get(jdx).get(0).equals(loc1) 
              && this.redundantDoors.get(jdx).get(1).equals(loc2)) {
            return true;
          }
          if (this.redundantDoors.get(jdx).get(0).equals(loc2) 
              && this.redundantDoors.get(jdx).get(1).equals(loc1)) {
            return true;
          }
          
        }
        List<Location> newLocations = new ArrayList<Location>();
        newLocations.add(loc1);
        newLocations.add(loc2);
        this.redundantDoors.add(newLocations);
        return true;
      }
    }
    return false;
  }

  @Override
  public void addMazeDoors() {
    
    int totalWallsToRemove = this.numOfRows * this.numOfCols - 1;
    
    while (totalWallsToRemove > 0) {
      
      int randNumber = this.generateOneRandNumber(this.listOfWalls.size()); 
      Location location1 = this.listOfWalls.get(randNumber).getLocation1();
      Location location2 = this.listOfWalls.get(randNumber).getLocation2();
      
      if (! this.checkIfInSameHallWay(location1, location2)) {
        totalWallsToRemove--;
        this.addDoor(location1, location2);        
      }
      // Removes from list of walls and adds to redundant doors list.
      this.listOfWalls.remove(randNumber);
      
    }
    /* Now add remaining walls to redundantDoors if there is any left */
    for (int idx = 0; idx < this.listOfWalls.size(); idx++) {
      List<Location> newLocations = new ArrayList<Location>();
      newLocations.add(this.listOfWalls.get(idx).getLocation1());
      newLocations.add(this.listOfWalls.get(idx).getLocation2());
      this.redundantDoors.add(newLocations);
    }
    
    
    
     
  }
    
  

  

  @Override
  public void addLocations() {
    
    List<Integer> goldLocations = this.generateNumbers(numOfRows * numOfCols, goldRatio);
    List<Integer> thiefLocations = this.generateNumbers(numOfRows * numOfCols, thiefRatio);
    int curOrder = -1;
    for (int idx = 0; idx < numOfRows; idx++) {
      for (int jdx = 0; jdx < numOfCols; jdx++) {
        curOrder++; 
        //
        Point newPoint = new Point(jdx, idx);
        Prize newPrize;
        if (goldLocations.get(curOrder) == 1 && thiefLocations.get(curOrder) == 1) {
          newPrize = new GoldThief(goldVal, thiefFactor);      
        } else if (goldLocations.get(curOrder) == 1) {
          newPrize = new Gold(goldVal);
        } else if (thiefLocations.get(curOrder) == 1) {
          newPrize = new Thief(thiefFactor);
        } else {
          newPrize = new NoPrize();
        }
        Location newLocation = new Location(newPoint, newPrize);
        this.adjaLocation.put(newLocation, new ArrayList<Location>());
        this.locationIndex.add(newLocation);
      }
    }
    
  }
  
  @Override
  public int generateOneRandNumber(int bound) {
    Random r = new Random();
    r.setSeed(123456789);
    return r.nextInt(bound);
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
      }
      if (y + 1 < this.numOfRows) {
        Location newLoc2 = new Location(new Point(x, y + 1), new NoPrize());
        for (int jdx = 0; jdx < this.locationIndex.size(); jdx++) {
          if (this.locationIndex.get(jdx).equals(newLoc2)) {
            this.listOfWalls.add(new Walls(this.locationIndex.get(idx), 
                this.locationIndex.get(jdx)));
          }
        }
      }
    }
    
    
  }
  
  @Override
  public String giveLocationInfo(Location location) {
    
    //Get the actual Location object
    int idx = 0;
    StringBuilder moveList = new StringBuilder();
    for (idx = 0; idx < this.locationIndex.size(); idx++) {
      if (location.equals(this.locationIndex.get(idx))) {
        break;
      }
    }
    
    // Get the possible moves from current location
    for (Location eachLocation: this.adjaLocation.get(this.locationIndex.get(idx))) {
      if (eachLocation.getLocationCoord().getX() == this.locationIndex.get(idx)
          .getLocationCoord().getX() + 1 || (eachLocation.getLocationCoord().getX() == 0 
          && this.locationIndex.get(idx).getLocationCoord().getX() == this.numOfRows - 1)) {
        moveList.append("East");
      } else if (eachLocation.getLocationCoord().getX() == this.locationIndex.get(idx)
          .getLocationCoord().getX() - 1 || (eachLocation.getLocationCoord()
          .getX() == this.numOfCols - 1) 
          && this.locationIndex.get(idx).getLocationCoord().getX() == 0) {
        moveList.append("West");
      }
      if (eachLocation.getLocationCoord().getY() == this.locationIndex.get(idx)
          .getLocationCoord().getY() + 1 || (eachLocation.getLocationCoord().getY() == 0) 
          && this.locationIndex.get(idx).getLocationCoord().getY() == this.numOfCols - 1) {
        moveList.append("North");
      } else if (eachLocation.getLocationCoord().getY() == this.locationIndex.get(idx)
          .getLocationCoord().getY() - 1 || (eachLocation.getLocationCoord()
          .getY() == this.numOfRows - 1) 
          && this.locationIndex.get(idx).getLocationCoord().getY() == 0) {
        moveList.append("South");
      }
    }
    
    return location.toString() + "Possible moves are: " + moveList.toString();
    
  }
  
  @Override
  public Location mapLocation(Location location) {
    for (Location eachLoc: this.locationIndex) {
      if (location.equals(eachLoc)) {
        return eachLoc;
      }
    }
    return null;
  }
  
  @Override
  public Location giveEastMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getX() == curLoc.getLocationCoord().getX() + 1 
          || (eachLoc.getLocationCoord().getX() == 0) 
          && curLoc.getLocationCoord().getX() == this.numOfRows - 1) {
        return eachLoc;
      }
    }
    return null;
  }

  @Override
  public Location giveWestMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getX() == curLoc.getLocationCoord().getX() - 1 
          || (eachLoc.getLocationCoord().getX() == this.numOfRows - 1) 
          && curLoc.getLocationCoord().getX() == 0) {
        return eachLoc;
      }
    }
    return null;
  }

  @Override
  public Location giveNorthMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getY() == curLoc.getLocationCoord().getY() + 1 
          || (eachLoc.getLocationCoord().getY() == 0) 
          && curLoc.getLocationCoord().getY() == this.numOfCols - 1) {
        return eachLoc;
      }
    }
    return null;
  }

  @Override
  public Location giveSouthMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getY() == curLoc.getLocationCoord().getY() - 1 
          || (eachLoc.getLocationCoord().getY() == this.numOfCols - 1) 
          && curLoc.getLocationCoord().getY() == 0) {
        return eachLoc;
      }
    }
    return null;
  }
  
  @Override
  public String toString() {
    int totalDoors = 0;
    for (Location each: this.adjaLocation.keySet()) {
      totalDoors += this.adjaLocation.get(each).size();
    }
    return String.valueOf(totalDoors / 2);
  }
  

}
