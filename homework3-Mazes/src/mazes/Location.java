package mazes;

import java.awt.Point;

/**
 * Represents a cell in the maze where a player can stay.
 * 
 */
public class Location {
  
  private Point locationCoord;
  private Prize prize;
  
  /**
   * Builds a location for the maze with coordinates and prize.
   * @param locationCoord coordinates of location using Point
   * @param prize prize of the location
   * @throws IllegalArgumentException if null is passed
   */
  public Location(Point locationCoord, Prize prize)
      throws IllegalArgumentException {
    if (locationCoord == null || prize == null) {
      throw new IllegalArgumentException("arguments are invalid!!");
    }
    
    this.locationCoord = locationCoord;
    this.prize = prize;
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
    this.prize = new NoPrize();
  }
  
  /**
   * Gives the Point object to give coordinates.
   * @return Point
   */
  public Point getLocationCoord() {
    return this.locationCoord;
  }
  
  /**
   * Gives the prize object.
   * @return Prize
   */
  public Prize getprize() {
    if (this.prize.equalsGold()) {
      Prize toGive = new Gold(this.prize.getPrizeValue());
      this.prize = new NoPrize();
      return toGive;
    }
    if (this.prize.equalsGoldThief()) {
      Prize toGive = new GoldThief(this.prize.getPrizeValue(), this.prize.getFactor());
      this.prize = new Thief(this.prize.getFactor());
      return toGive;
    }
    
    return this.prize;
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
