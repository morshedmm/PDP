package conservatory;

/**
 * This class represent bird class named Prey which extends AbstractBird class.
 *
 */
public final class Prey extends AbstractBird {

  private String activity;
  private String whatIsCaptured;
  
  /**
   * This function sets the value of activities and what this bird captures.
   * 
   * @param activity description of the activities in String
   * @param whatIsCaptured kind of animals it captures in String
   * @throws IllegalArgumentException if values are negative for id, age or numOfWings
   */
  public Prey(int id, int age, int numOfWings, String activity, String whatIsCaptured,
      boolean isExtinct, String color, String description, String type, String foodNeeded) 
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
    this.activity = activity;
    this.whatIsCaptured = whatIsCaptured;
  }
  
  /**
   * It gives the activity of this bird.
   * @return activity in String
   */
  public String getActivity() {
    return this.activity;
  }
  
  /**
   * Used to know the animals this bird captures.
   * @return animals in String
   */
  public String getWhatIsCaptured() {
    return this.whatIsCaptured;
  }
}
