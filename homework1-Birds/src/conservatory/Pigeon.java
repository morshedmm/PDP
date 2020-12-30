package conservatory;

/**
 * This class represent bird class named Pigeon which extends AbstractBird class. 
 *
 */
public final class Pigeon extends AbstractBird {
  
  private int milkFeedingAge;
  
  /**
   * This function sets the value of milk feeding age limit for Pigeon class.
   * 
   * @param milkFeedingAge how long milk is fed in integer
   * @throws IllegalArgumentException if age is negative exceptions is thrown
   */
  public Pigeon(int id, int age, int numOfWings, boolean isExtinct,
      int milkFeedingAge, String color, String description, String type, String foodNeeded) 
      throws IllegalArgumentException {
    if (id < 0 || age < 0 || numOfWings < 0 || milkFeedingAge < 0) {
      throw new IllegalArgumentException("Negative values are not allowed for ID, Age or Wings");
    }
    
    this.id = id;
    this.age = age;
    this.numOfWings = numOfWings;
    this.isExtinct = isExtinct;
    this.color = color;
    this.description = description;
    this.type = type;
    this.foodNeeded = foodNeeded;
    this.milkFeedingAge = milkFeedingAge;
  }
  
  /**
   * Gives the age for which milk is fed. 
   * 
   * @return age in integer
   */
  public int getMilkFeedingAge() {
    return this.milkFeedingAge;
  }

}
