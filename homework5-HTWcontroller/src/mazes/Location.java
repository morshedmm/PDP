package mazes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cell in the maze where a player can stay.
 * 
 */
public class Location {
  
  private Point locationCoord;
  private List<CaveObject> prize = new ArrayList<CaveObject>();
  
  /**
   * Builds a location for the maze with coordinates and prize.
   * @param locationCoord coordinates of location using Point
   * @param object1 first object of this location
   * @param object2 second object of this location
   * @throws IllegalArgumentException if null is passed
   */
  public Location(Point locationCoord, CaveObject object1, CaveObject object2)
      throws IllegalArgumentException {
    if (locationCoord == null || object1 == null || object2 == null) {
      throw new IllegalArgumentException("arguments are invalid!!");
    }
    
    this.locationCoord = locationCoord;
    this.prize.add(object1);
    this.prize.add(object2);
  }
  
  /**
   * Builds a location for the maze with coordinates and prize.
   * @param locationCoord coordinates of location using Point
   * @param object only object of this location
   * @throws IllegalArgumentException if null is passed
   */
  public Location(Point locationCoord, CaveObject object)
      throws IllegalArgumentException {
    if (locationCoord == null || object == null) {
      throw new IllegalArgumentException("arguments are invalid!!");
    }
    
    this.locationCoord = locationCoord;
    this.prize.add(new EmptyObject());
    this.prize.add(object);
    
  }
  
  /**
   * Creates a location with no prize in it.
   * @param locationCoord coordinates of location using Point
   * @throws IllegalArgumentException if null is passed
   */
  public Location(Point locationCoord)
      throws IllegalArgumentException {
    if (locationCoord == null) {
      throw new IllegalArgumentException("arguments are invalid!!");
    }
    
    this.locationCoord = locationCoord;
    this.prize = new ArrayList<CaveObject>();
    this.prize.add(new EmptyObject());
    this.prize.add(new EmptyObject());
  }
  
  /**
   * Gives the Point object to give coordinates.
   * @return Point point of this location
   */
  public Point getLocationCoord() {
    return this.locationCoord;
  }
  
  /**
   * Gives the prize objects.
   * @return Prize the objects present in this location
   */
  public List<CaveObject> getprize() {
    
    return this.prize;
  }
  
  /**
   * Replaces objects of this location with new objects. 
   * @param newList list of objects used to replace the current one
   */
  public void addObjects(List<CaveObject> newList) {
    this.prize = newList;
    return;
  }
  
  
  @Override
  public boolean equals(Object other) {
    Location otherLocation = (Location) other;
    return this.locationCoord.equals(otherLocation.locationCoord);
  }

  @Override
  public String toString() {
    String locationInfo = String.valueOf(this.locationCoord.getX()) + ","
        + String.valueOf(this.locationCoord.getY());
    return locationInfo;
  }
  
  @Override
  public int hashCode() {
    return (int) (this.locationCoord.getX() + this.locationCoord.getY());
  }
  
}
