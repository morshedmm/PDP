import java.util.List;
import mazes.Location;
import mazes.Maze;
import mazes.Walls;

/**
 * Represents a Mock model with no active processing to test controller class in isolation.
 * 
 */
public class MockModel implements Maze {
  
  private StringBuilder log;
  
  /**
   * Creates a mock model object.
   * @param log saves the contents sent in string
   */
  public MockModel(StringBuilder log) {
    this.log = log;
    
  }

  @Override
  public void addLocations() {
    return;
    
  }

  @Override
  public List<List<Location>> addDoor(Location location1, Location location2,
      List<List<Location>> hallWays) {
    return null;
  }

  @Override
  public String giveLocationInfo(Location location) {
    
    return "a";
  }

  @Override
  public void addMazeDoors() {
    return;
    
  }

  @Override
  public int generateOneRandNumber(int bound) {
    return 0;
  }

  @Override
  public List<Walls> createWalls() {
    
    return null;
  }

  @Override
  public Location mapLocation(Location location) {
    
    return null;
  }

  @Override
  public Location giveEastMove(Location curLoc) {
    this.log.append(String.valueOf(curLoc.getLocationCoord().getX()));
    this.log.append(String.valueOf(curLoc.getLocationCoord().getY()));
    return curLoc;
  }

  @Override
  public Location giveWestMove(Location curLoc) {
    
    return null;
  }

  @Override
  public Location giveNorthMove(Location curLoc) {
    
    return null;
  }

  @Override
  public Location giveSouthMove(Location curLoc) {
    
    return null;
  }

  @Override
  public String ifSmelledSomething(Location curLoc) {
    
    return null;
  }

  @Override
  public Location giveRandomLocation() {
    
    return null;
  }

  @Override
  public Location findArrowLocation(Location curLocation, String direction, int distance) {
    this.log.append(String.valueOf(curLocation.getLocationCoord().getX()));
    this.log.append(direction);
    this.log.append(String.valueOf(distance));
    return curLocation;
  }

  @Override
  public void assignObjects() {
    return;
    
  }

  @Override
  public List<Location> getListOfCaves() {
    
    return null;
  }

  @Override
  public List<Location> giveTunnelLocations() {
    
    return null;
  }

}
