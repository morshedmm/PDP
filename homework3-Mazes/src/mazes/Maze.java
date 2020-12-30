package mazes;


/**
 * Represents generic Maze.
 * 
 */
public interface Maze {
  
  /**
   * Adds a location to the maze.
   */
  public void addLocations();
  
  /**
   * Adds a new door between two locations.
   * @param location1 first location of the edge
   * @param location2 second location of the edge
   */
  public void addDoor(Location location1, Location location2);
  
  /**
   * Gives the info for a location.
   * @param location Location for which info are asked.
   * @return String with location info
   */
  public String giveLocationInfo(Location location);
  
  /**
   * Adds the necessary doors to build the maze.
   */
  public void addMazeDoors();
  
  /**
   * Generates a random number.
   * @param bound bound of the number generation
   * @return random number
   */
  public int generateOneRandNumber(int bound);
  
  /**
   * Creates all the walls to build the maze.
   */
  public void createWalls();
  
  /**
   * Maps the location from coordinates of the supplied location.
   * @param location location whom to map
   * @return mapped location
   */
  public Location mapLocation(Location location);
  
  /**
   * Gives the move towards East if there is a door.
   * @param curLoc whose direct is asked for
   * @return location to the east
   */
  public Location giveEastMove(Location curLoc);
  
  /**
   * Gives the move towards East if there is a door.
   * @param curLoc whose direct is asked for
   * @return location to the west
   */
  public Location giveWestMove(Location curLoc);
  
  /**
   * Gives the move towards North if there is a door.
   * @param curLoc whose direct is asked for
   * @return location to the north
   */
  public Location giveNorthMove(Location curLoc);
  
  /**
   * Gives the move towards South if there is a door.
   * @param curLoc whose direct is asked for
   * @return location to the south
   */
  public Location giveSouthMove(Location curLoc);

}
