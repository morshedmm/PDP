package mazes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

/**
 * Implements generic maze interface.
 * 
 */
public abstract class AbstractMaze implements Maze {
  
  protected int numOfRows;
  protected int numOfCols;
  protected int remainingWalls;
  protected int numOfPits;
  protected int numOfBats;
  protected Map<Location, List<Location>> adjaLocation = new HashMap<Location, List<Location>>();
  protected List<List<Location>> redundantDoors = new ArrayList<List<Location>>();
  
  
  /**
   * Creates a maze for Perfect Maze.
   * @param numOfRows number of rows of the maze
   * @param numOfCols number of columns of the maze
   * @throws IllegalArgumentException if dimensions are not legal
   */
  public AbstractMaze(int numOfRows, int numOfCols, int numOfPits, int numOfBats)
      throws IllegalArgumentException {
    if (numOfRows <= 0 || numOfCols <= 0 || numOfPits < 0 || numOfBats < 0) {
      throw new IllegalArgumentException("Maze dimension must be greater than zero");
    }
    this.numOfRows = numOfRows;
    this.numOfCols = numOfCols;
    this.numOfPits = numOfPits;
    this.numOfBats = numOfBats;
    adjaLocation = new HashMap<Location, List<Location>>();
    redundantDoors = new ArrayList<List<Location>>();
    
  }
  
  /**
   * Creates a maze for Room Maze.
   * @param numOfRows number of rows of the maze
   * @param numOfCols number of columns of the maze
   * @param remainingWalls number of walls to remain for room maze
   * @throws IllegalArgumentException if dimensions are not legal
   */
  public AbstractMaze(int numOfRows, int numOfCols, int remainingWalls, int numOfPits, 
      int numOfBats)
      throws IllegalArgumentException {
    if (numOfRows <= 0 || numOfCols <= 0 || remainingWalls < 0 || remainingWalls
        > 2 * numOfCols * numOfRows || numOfPits < 0 || numOfBats < 0) {
      throw new IllegalArgumentException("One or more Maze parameters are Illegal!!");
    }
    this.numOfRows = numOfRows;
    this.numOfCols = numOfCols;
    this.remainingWalls = remainingWalls;
    this.numOfBats = numOfBats;
    this.numOfPits = numOfPits;
    adjaLocation = new HashMap<Location, List<Location>>();
    redundantDoors = new ArrayList<List<Location>>();
    
  }
  
  
  private List<Integer> generateNumbers(int locationNum, int bound) {
    int count = 0;
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
  
  private int findHallWayForLocation(Location location, List<List<Location>> hallWays) {
    for (int idx = 0; idx < hallWays.size(); idx++) {
      if (hallWays.get(idx).contains(location)) {
        return idx;
      }
    }
    return -1;
  }

  @Override
  public List<List<Location>> addDoor(Location location1, Location location2, 
      List<List<Location>> hallWays) {
        
    this.adjaLocation.get(location1).add(location2);
    this.adjaLocation.get(location2).add(location1);
    int locationIndex1 = this.findHallWayForLocation(location1, hallWays);
    int locationIndex2 = this.findHallWayForLocation(location2, hallWays);
    
    if (locationIndex1 == -1 && locationIndex2 == -1) {
      List<Location> newList = new ArrayList<Location>();
      newList.add(location1);
      newList.add(location2);
      hallWays.add(newList);
    } else if (locationIndex1 == -1) {
      hallWays.get(locationIndex2).add(location1);
    } else if (locationIndex2 == -1) {
      hallWays.get(locationIndex1).add(location2);
    } else {
      hallWays.get(locationIndex1).addAll(0, hallWays.get(locationIndex2));
      hallWays.remove(locationIndex2);
    }
    
    return hallWays;

  }
 
  /*
   * Checks if in same wall, if they are, they will be added to redundantDoor 
   * if not already in redunDantDoor list.
   */
  private boolean checkIfInSameHallWay(Location loc1, Location loc2, 
      List<List<Location>> hallWays) {
    for (int idx = 0; idx < hallWays.size(); idx++) {
      if (hallWays.get(idx).contains(loc1) && hallWays.get(idx).contains(loc2)) {
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
  
  private List<Location> getLocationIndex() {
    
    List<Location> locationIndex = new ArrayList<Location>();
    for (Entry<Location, List<Location>> entry : this.adjaLocation.entrySet()) {
      locationIndex.add(entry.getKey());
    }
    return locationIndex;
  }

  @Override
  public void addMazeDoors() {
    
    int totalWallsToRemove = this.numOfRows * this.numOfCols - 1;
    List<Walls> listOfWalls = new ArrayList<Walls>();
    listOfWalls = this.createWalls();
    List<List<Location>> hallWays = new ArrayList<List<Location>>();
    
    while (totalWallsToRemove > 0) {
      
      int randNumber = this.generateOneRandNumber(listOfWalls.size()); 
      Location location1 = listOfWalls.get(randNumber).getLocation1();
      Location location2 = listOfWalls.get(randNumber).getLocation2();
      
      if (! this.checkIfInSameHallWay(location1, location2, hallWays)) {
        totalWallsToRemove--;
        hallWays = this.addDoor(location1, location2, hallWays);        
      }
      // Removes from list of walls and adds to redundant doors list.
      listOfWalls.remove(randNumber);
      
    }
    /* Now add remaining walls to redundantDoors if there is any left */
    for (int idx = 0; idx < listOfWalls.size(); idx++) {
      List<Location> newLocations = new ArrayList<Location>();
      newLocations.add(listOfWalls.get(idx).getLocation1());
      newLocations.add(listOfWalls.get(idx).getLocation2());
      this.redundantDoors.add(newLocations);
    }
    
    
    
     
  }
    
  

  

  @Override
  public void addLocations() {
    
    for (int idx = 0; idx < numOfRows; idx++) {
      for (int jdx = 0; jdx < numOfCols; jdx++) {
        
        Point newPoint = new Point(jdx, idx);
        Location newLocation = new Location(newPoint);
        this.adjaLocation.put(newLocation, new ArrayList<Location>());
        
      }
    } 
    
  }
  
  @Override
  public List<Location> getListOfCaves() {
    
    List<Location> listOfCaves = new ArrayList<Location>();
    for (Location eachLoc: this.adjaLocation.keySet()) {
      listOfCaves.add(eachLoc);
    }
    return listOfCaves;
  }
  
  @Override
  public void assignObjects() {
    
    List<Location> listOfCaves = this.getListOfCaves();
    List<Integer> batLocations = this.generateNumbers(listOfCaves.size(), this.numOfBats);
    List<Integer> pitLocations = this.generateNumbers(listOfCaves.size(), this.numOfPits);
    int wumpusLocation = this.generateOneRandNumber(listOfCaves.size());
    int curOrder = -1;
    for (int idx = 0; idx < listOfCaves.size(); idx++) {
      curOrder++;
      List<CaveObject> newList = new ArrayList<CaveObject>();
      if (batLocations.get(curOrder) == 1 && wumpusLocation == curOrder) {
        newList.add(new SuperBat());
        newList.add(new Wumpus());
        
      } else if (batLocations.get(curOrder) == 1 && pitLocations.get(curOrder) == 1) {
        newList.add(new SuperBat(123));
        newList.add(new BottomlessPit());
        
        
      } else if (wumpusLocation == curOrder) {
        newList.add(new EmptyObject());
        newList.add(new Wumpus());
        
      } else if (pitLocations.get(curOrder) == 1) {
        newList.add(new EmptyObject());
        newList.add(new BottomlessPit());
        
      } else if (batLocations.get(curOrder) == 1) {
        newList.add(new SuperBat());
        newList.add(new EmptyObject());
      } else {
        newList.add(new EmptyObject());
        newList.add(new EmptyObject());
      }
      listOfCaves.get(idx).addObjects(newList);
    }
  }
  
  @Override
  public int generateOneRandNumber(int bound) {
    Random r = new Random();
    r.setSeed(123456789);
    return r.nextInt(bound);
  }

  @Override
  public List<Walls> createWalls() {
    
    List<Walls> listOfWalls = new ArrayList<Walls>();
    
    List<Location> locationIndex = new ArrayList<Location>();
    locationIndex = this.getLocationIndex();
    
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
      }
      if (y + 1 < this.numOfRows) {
        Location newLoc2 = new Location(new Point(x, y + 1));
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
  
  @Override
  public String giveLocationInfo(Location location) {
    
    List<Location> locationIndex = new ArrayList<Location>();
    locationIndex = this.getLocationIndex();
    
    //Get the actual Location object
    int idx = 0;
    StringBuilder moveList = new StringBuilder();
    for (idx = 0; idx < locationIndex.size(); idx++) {
      if (location.equals(locationIndex.get(idx))) {
        break;
      }
    }
    
    // Get the possible moves from current location
    for (Location eachLocation: this.adjaLocation.get(locationIndex.get(idx))) {
      if (eachLocation.getLocationCoord().getX() == locationIndex.get(idx)
          .getLocationCoord().getX() + 1 || (eachLocation.getLocationCoord().getX() == 0 
          && locationIndex.get(idx).getLocationCoord().getX() == this.numOfRows - 1)) {
        moveList.append("East");
      } else if (eachLocation.getLocationCoord().getX() == locationIndex.get(idx)
          .getLocationCoord().getX() - 1 || (eachLocation.getLocationCoord()
          .getX() == this.numOfCols - 1) 
          && locationIndex.get(idx).getLocationCoord().getX() == 0) {
        moveList.append("West");
      }
      if (eachLocation.getLocationCoord().getY() == locationIndex.get(idx)
          .getLocationCoord().getY() + 1 || (eachLocation.getLocationCoord().getY() == 0) 
          && locationIndex.get(idx).getLocationCoord().getY() == this.numOfCols - 1) {
        moveList.append("North");
      } else if (eachLocation.getLocationCoord().getY() == locationIndex.get(idx)
          .getLocationCoord().getY() - 1 || (eachLocation.getLocationCoord()
          .getY() == this.numOfRows - 1) 
          && locationIndex.get(idx).getLocationCoord().getY() == 0) {
        moveList.append("South");
      }
    }
    
    return location.toString() + "Possible moves are: " + moveList.toString();
    
  }
  
  @Override
  public Location mapLocation(Location location) {
    for (Entry<Location, List<Location>> entry : this.adjaLocation.entrySet()) {
      Location eachLoc = entry.getKey();
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
  public String ifSmelledSomething(Location curLoc) {

    boolean wumpusFound = false;
    boolean pitFound = false;
    List<Location> neighbors = this.adjaLocation.get(curLoc);
    for (Location eachLoc: neighbors) {
      ArrayList<CaveObject> objects = (ArrayList<CaveObject>) eachLoc.getprize();
      if (objects.get(1).equalsWumpus()) {
        wumpusFound = true;
      }
      if (objects.get(1).equalsBottomlessPit()) {
        pitFound = true;
      }
    }
    StringBuilder retStr = new StringBuilder();
    if (wumpusFound) {
      retStr.append("You smell a Wumpus!");
    }
    if (pitFound) {
      retStr.append("You feel a Draft!");
    }
    return retStr.toString();
  }
  
  @Override
  public Location giveRandomLocation() {
    
    int newRowNum = this.generateOneRandNumber(numOfRows);
    int newColNum = this.generateOneRandNumber(numOfCols);
    
    Point newPoint = new Point(newColNum, newRowNum);
    Location newLocation = new Location(newPoint);
    
    for (Location eachLoc: this.adjaLocation.keySet()) {
      if (eachLoc.equals(newLocation)) {
        return eachLoc;
      }
    }
    
    return newLocation;
  }
  
  
  @Override
  public Location findArrowLocation(Location curLocation, String direction, int distance) {
    
    
    StringBuilder travelDirection = new StringBuilder();
    travelDirection.append(direction);
    
    while (distance != 0) {
      if (travelDirection.toString().equals("east")) {
        if (this.giveEastMove(curLocation) != null) {
          curLocation = this.giveEastMove(curLocation);
          
        } else if (this.giveNorthMove(curLocation) != null) {
          curLocation = this.giveNorthMove(curLocation);
        } else if (this.giveSouthMove(curLocation) != null) {
          curLocation = this.giveSouthMove(curLocation);
        } else {
          break;
        }
      }
      
      if (travelDirection.toString().equals("west")) {
        if (this.giveWestMove(curLocation) != null) {
          curLocation = this.giveWestMove(curLocation);
          
        } else if (this.giveNorthMove(curLocation) != null) {
          curLocation = this.giveNorthMove(curLocation);
        } else if (this.giveSouthMove(curLocation) != null) {
          curLocation = this.giveSouthMove(curLocation);
        } else {
          break;
        }
      }
      
      if (travelDirection.toString().equals("north")) {
        if (this.giveNorthMove(curLocation) != null) {
          curLocation = this.giveNorthMove(curLocation);
          
        } else if (this.giveEastMove(curLocation) != null) {
          curLocation = this.giveEastMove(curLocation);
        } else if (this.giveWestMove(curLocation) != null) {
          curLocation = this.giveWestMove(curLocation);
        } else {
          break;
        }
      }
      
      if (travelDirection.toString().equals("south")) {
        if (this.giveSouthMove(curLocation) != null) {
          curLocation = this.giveSouthMove(curLocation);
          
        } else if (this.giveEastMove(curLocation) != null) {
          curLocation = this.giveEastMove(curLocation);
        } else if (this.giveWestMove(curLocation) != null) {
          curLocation = this.giveWestMove(curLocation);
        } else {
          break;
        }
      }
      
      distance--;
      
    }
    return curLocation;
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
