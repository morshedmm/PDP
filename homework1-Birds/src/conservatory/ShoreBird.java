package conservatory;

/**
 * This class represent bird class named ShoreBird which extends AbstractWaterBird class.
 * 
 */
public final class ShoreBird extends AbstractWaterBird {
  
  private String inContinenetsTheyFound;
  
  /**
   * This function sets the contingents these kinds of birds are found.
   * @param continent name of the contingent in String
   * @throws IllegalArgumentException the name must start with capital letter and will be one
   *     of the seven contingent names.
   */
  public ShoreBird(int id, int age, int numOfWings, String continent, boolean isExtinct,
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
    this.inContinenetsTheyFound = continent;
    this.waterBody = waterBody;
    this.eatFish = eatFish;
  }
  
  /**
   * This function returns the contingent name. 
   * @return name of the contingent in String
   */
  public String getInContinentsTheyFound() {
    return this.inContinenetsTheyFound;
  }
  
}
