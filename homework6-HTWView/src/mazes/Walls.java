package mazes;

/**
 * Represents obstacle between two cell locations.
 *
 */
public class Walls {
  
  private Location location1;
  private Location location2;
  
  /**
   * Build a wall.
   * @param location1 first location 
   * @param location2 second location
   * @throws IllegalArgumentException if null is passed
   */
  public Walls(Location location1, Location location2)
      throws IllegalArgumentException {
    if (location1 == null || location2 == null) {
      throw new IllegalArgumentException("Locations can not be null for walls");
    }
    this.location1 = location1;
    this.location2 = location2;
  }
  
  /**
   * Gives location1.
   * @return Location object
   */
  public Location getLocation1() {
    return this.location1;
  }
  
  /**
   * Gives location2.
   * @return Location object
   */
  public Location getLocation2() {
    return this.location2;
  }

}
