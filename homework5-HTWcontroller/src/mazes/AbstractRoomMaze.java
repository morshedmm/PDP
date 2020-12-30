package mazes;

import java.util.ArrayList;
import java.util.List;

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
  public AbstractRoomMaze(int numOfRows, int numOfCols, int remainingWalls, 
      int numOfPits, int numOfBats)
      throws IllegalArgumentException {
    super(numOfRows, numOfCols, remainingWalls, numOfPits, numOfBats);
  }
  
  /**
   * Gives common implementations for adding rooms of all room mazes.
   */
  public void addRoomMazeDoors() {
    this.addMazeDoors();
    int doorsToAdd = this.redundantDoors.size() - this.remainingWalls;
    
    while (doorsToAdd > 0) {
      int randNumber = this.generateOneRandNumber(this.redundantDoors.size());
      
      this.adjaLocation.get(this.redundantDoors.get(randNumber).get(0)).add(this.redundantDoors
          .get(randNumber).get(1));
      this.adjaLocation.get(this.redundantDoors.get(randNumber).get(1)).add(this.redundantDoors
          .get(randNumber).get(0));
      this.redundantDoors.remove(randNumber);
      doorsToAdd--;
    }
    
  }
  
  // Added to get the next location through the tunnel for room maze.
  private Location nextLocationForRoomMaze(Location current, Location previous) {
    
    
    while (this.adjaLocation.get(current).size() == 2) {
      for (Location eachLoc2 : this.adjaLocation.get(current)) {
        if (! eachLoc2.equals(previous)) {
          previous = current;
          current = eachLoc2;
          break;
        }
      }
    }
    return current;
    
  }
  
  // Added to use specifically for room mazes to get the east move to handle move through tunnel.
  @Override
  public Location giveEastMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getX() == curLoc.getLocationCoord().getX() + 1 
          || ((eachLoc.getLocationCoord().getX() == 0) 
          && curLoc.getLocationCoord().getX() == this.numOfRows - 1)) {
        
        Location prevLoc = curLoc;
        eachLoc = this.nextLocationForRoomMaze(eachLoc, prevLoc);
        return eachLoc;
      }
    }
    return null;
  }
  
  //Added to use specifically for room mazes to get the west move to handle move through tunnel.
  @Override
  public Location giveWestMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getX() == curLoc.getLocationCoord().getX() - 1 
          || ((eachLoc.getLocationCoord().getX() == this.numOfRows - 1) 
          && curLoc.getLocationCoord().getX() == 0)) {
        
        Location prevLoc = curLoc;
        eachLoc = this.nextLocationForRoomMaze(eachLoc, prevLoc);
        return eachLoc;
      }
    }
    return null;
  }
  
  //Added to use specifically for room mazes to get the north move to handle move through tunnel.
  @Override
  public Location giveNorthMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getY() == curLoc.getLocationCoord().getY() + 1 
          || ((eachLoc.getLocationCoord().getY() == 0) 
          && curLoc.getLocationCoord().getY() == this.numOfCols - 1)) {
        
        Location prevLoc = curLoc;
        eachLoc = this.nextLocationForRoomMaze(eachLoc, prevLoc);
        return eachLoc;
      }
    }
    return null;
  }

  //Added to use specifically for room mazes to get the south move to handle move through tunnel.
  @Override
  public Location giveSouthMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getY() == curLoc.getLocationCoord().getY() - 1 
          || ((eachLoc.getLocationCoord().getY() == this.numOfCols - 1) 
          && curLoc.getLocationCoord().getY() == 0)) {
        
        Location prevLoc = curLoc;
        eachLoc = this.nextLocationForRoomMaze(eachLoc, prevLoc);
        return eachLoc;
      }
    }
    return null;
  }
  
  // Added to check if there was smelling in neighbors. To handle neighbors through
  // tunnel in case of room maze, this is done here.
  @Override
  public String ifSmelledSomething(Location curLoc) {
    
    boolean wumpusFound = false;
    boolean pitFound = false;
    List<Location> neighbors = new ArrayList<Location>();
    
    if (this.giveEastMove(curLoc) != null) {
      neighbors.add(this.giveEastMove(curLoc));
    }
    
    if (this.giveWestMove(curLoc) != null) {
      neighbors.add(this.giveWestMove(curLoc));
    }
    
    if (this.giveNorthMove(curLoc) != null) {
      neighbors.add(this.giveNorthMove(curLoc));
    }
    
    if (this.giveSouthMove(curLoc) != null) {
      neighbors.add(this.giveSouthMove(curLoc));
    }
    
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
  public List<Location> getListOfCaves() {
    
    List<Location> listOfCaves = new ArrayList<Location>();
    for (Location eachLoc: this.adjaLocation.keySet()) {
      if (this.adjaLocation.get(eachLoc).size() != 2) {
        listOfCaves.add(eachLoc);
      }
      
    }
    return listOfCaves;
  }
  
  private List<Location> nextArrowLocationForRoomMaze(Location current, Location previous) {
    
    List<Location> locations = new ArrayList<Location>();
    while (this.adjaLocation.get(current).size() == 2) {
      for (Location eachLoc2 : this.adjaLocation.get(current)) {
        if (! eachLoc2.equals(previous)) {
          previous = current;
          current = eachLoc2;
          break;
        }
      }
    }
    locations.add(current);
    locations.add(previous);
    return locations;
    
  }
  
  /**
   * Gives east arrow move for room maze.
   * @param curLoc location of the arrow currently
   * @return returns possible moves of the arrow in list
   */
  public List<Location> giveEastArrowMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getX() == curLoc.getLocationCoord().getX() + 1 
          || ((eachLoc.getLocationCoord().getX() == 0) 
          && curLoc.getLocationCoord().getX() == this.numOfRows - 1)) {
        List<Location> locationList = new ArrayList<Location>();
        Location prevLoc = curLoc;
        locationList = this.nextArrowLocationForRoomMaze(eachLoc, prevLoc);
        return locationList;
      }
    }
    return null;
    
  }
  
  /**
   * Gives west arrow move for room maze.
   * @param curLoc location of the arrow currently
   * @return returns possible moves of the arrow in list
   */
  public List<Location> giveWestArrowMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getX() == curLoc.getLocationCoord().getX() - 1 
          || ((eachLoc.getLocationCoord().getX() == this.numOfRows - 1) 
          && curLoc.getLocationCoord().getX() == 0)) {
        List<Location> locationList = new ArrayList<Location>();
        Location prevLoc = curLoc;
        locationList = this.nextArrowLocationForRoomMaze(eachLoc, prevLoc);
        return locationList;
      }
    }
    return null;
  }
  
  /**
   * Gives north arrow move for room maze.
   * @param curLoc location of the arrow currently
   * @return returns possible moves of the arrow in list
   */
  public List<Location> giveNorthArrowMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getY() == curLoc.getLocationCoord().getY() + 1 
          || ((eachLoc.getLocationCoord().getY() == 0) 
          && curLoc.getLocationCoord().getY() == this.numOfCols - 1)) {
        List<Location> locationList = new ArrayList<Location>();
        Location prevLoc = curLoc;
        locationList = this.nextArrowLocationForRoomMaze(eachLoc, prevLoc);
        return locationList;
      }
    }
    return null;
  }

  /**
   * Gives south arrow move for room maze.
   * @param curLoc location of the arrow currently
   * @return returns possible moves of the arrow in list
   */
  public List<Location> giveSouthArrowMove(Location curLoc) {
    for (Location eachLoc : this.adjaLocation.get(curLoc)) {
      if (eachLoc.getLocationCoord().getY() == curLoc.getLocationCoord().getY() - 1 
          || ((eachLoc.getLocationCoord().getY() == this.numOfCols - 1) 
          && curLoc.getLocationCoord().getY() == 0)) {
        List<Location> locationList = new ArrayList<Location>();
        Location prevLoc = curLoc;
        locationList = this.nextArrowLocationForRoomMaze(eachLoc, prevLoc);
        return locationList;
      }
    }
    return null;
  }
  
  // Used to know the direction of a move from tunnel to a cave, to make sure arrow continues to 
  // move through same direction after coming out of tunnel.
  private String findDoorDirection(Location loc1, Location loc2) {
    
    if (loc1.getLocationCoord().getX() == loc2.getLocationCoord().getX()) {
      if (loc2.getLocationCoord().getY() == 1 + loc1.getLocationCoord().getY()) {
        return "north";
      } else if (loc2.getLocationCoord().getY() == loc1.getLocationCoord().getY() - 1) {
        return "south";
      } else if (loc2.getLocationCoord().getY() == 0 && loc1.getLocationCoord()
          .getY() == this.numOfRows - 1) {
        return "north";
      } else if (loc2.getLocationCoord().getY() == this.numOfRows - 1 && loc1.getLocationCoord()
          .getY() == 0) {
        return "south";
      }
      
      
    } else if (loc1.getLocationCoord().getY() == loc2.getLocationCoord().getY()) {
      if (loc2.getLocationCoord().getX() == 1 + loc1.getLocationCoord().getX()) {
        return "east";
      } else if (loc2.getLocationCoord().getX() == loc1.getLocationCoord().getX() - 1) {
        return "west";
      } else if (loc2.getLocationCoord().getX() == 0 && loc1.getLocationCoord()
          .getX() == this.numOfCols - 1) {
        return "east";
      } else if (loc2.getLocationCoord().getX() == this.numOfCols - 1 && loc1.getLocationCoord()
          .getX() == 0) {
        return "west";
      }
    }
    return "";
  }
  
  // To find arrow location for room maze, it is added here, as this is different than
  // perfect maze.
  @Override
  public Location findArrowLocation(Location curLocation, String direction, int distance) {
    
    List<Location> locations = new ArrayList<Location>();
    while (distance != 0) {
      if (direction.equals("east")) {
        locations = this.giveEastArrowMove(curLocation);
        if (locations == null) {
          return curLocation;
        }
        direction = this.findDoorDirection(locations.get(1), locations.get(0));
        
      }
    
    
      if (direction.equals("west")) {
        locations = this.giveWestArrowMove(curLocation);
        if (locations == null) {
          return curLocation;
        }
        direction = this.findDoorDirection(locations.get(1), locations.get(0));
      }
      if (direction.equals("north")) {
        locations = this.giveNorthArrowMove(curLocation);
        if (locations == null) {
          return curLocation;
        }
        direction = this.findDoorDirection(locations.get(1), locations.get(0));
      }
      
      if (direction.equals("south")) {
        locations = this.giveSouthArrowMove(curLocation);
        if (locations == null) {
          return curLocation;
        }
        direction = this.findDoorDirection(locations.get(1), locations.get(0));
      }
      distance--;
      curLocation = locations.get(0);
    }
    return locations.get(0);
  } 
    
} 


