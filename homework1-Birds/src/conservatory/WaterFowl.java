package conservatory;

/**
 * This class represent bird class named WaterFowl which extends AbstractWaterBird class.
 * 
 */
public final class WaterFowl extends AbstractWaterBird {
  
  private int swimmingAge;
  
  /**
   * This function sets the age the bird starts swimming.
   * @param age age of the bird to swim in integer
   * @throws IllegalArgumentException age can not be negative, it is, exception is thrown
   */
  public WaterFowl(int id, int age, int numOfWings, int swimmingAge, boolean isExtinct,
      String color, String description, String type, String foodNeeded, String waterBody,
      boolean eatFish) 
      throws IllegalArgumentException {
    if (id < 0 || age < 0 || numOfWings < 0) {
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
    this.swimmingAge = swimmingAge;
    this.waterBody = waterBody;
    this.eatFish = eatFish;
  }
  
  /**
   * Gives the minimum age for this bird to swim.
   * @return swimming age for bird
   */
  public int getSwimmingAge() {
    return this.swimmingAge;
  }

}
