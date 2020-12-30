package conservatory;

/**
 * This class represent bird class named Fightless which extends AbstractBird class.
 *
 */
public final class Fightless extends AbstractBird {
  
  private boolean ifHidesDuringDay;
  
  /**
   * Construct Fightless bird that has param to tell if it hides during day.
   * @param ifHidesDuringDay if the bird is active during day time
   */
  public Fightless(int id, int age, int numOfWings, boolean ifHidesDuringDay, boolean isExtinct,
      String color, String description, String type, String foodNeeded) 
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
    this.ifHidesDuringDay = ifHidesDuringDay;
  }
  
  /**
   * If this bird is active during day or night, known from this function.
   * @return if this bird hides during the day time
   */
  public boolean getIfHidesDuringDay() {
    return this.ifHidesDuringDay;
  }

}
