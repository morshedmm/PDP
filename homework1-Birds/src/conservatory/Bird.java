package conservatory;

/**
 * This Interface has generic getters for all kinds of birds.
 *
 */
public interface Bird {

  /**
   * Used to get the ID of the bird.
   * @return ID in integer will be returned
   */
  int getBirdId();
  
  /**
   * Used to get the color of the bird.
   * @return color in string will be returned
   */
  String getBirdColor();
  
  /**
   * Used to get the age of the bird.
   * @return age in integer will be returned
   */
  int getAge();
  
  /**
   * Used to get the number of wings of the bird.
   * @return number of wings in integer will be returned
   */
  int getNumOfWings();
  
  /**
   * Used to know any interesting or important info about the bird.
   * @return description in string will be returned
   */
  String getDescription();
  
  /**
   * Used to get the particular type of birds in a class.
   * @return bird type in certain class in string will be returned
   */
  String getType();
  
  /**
   * Used to get kind of food and amount of food in string.
   * @return food requirements in string
   */
  String getFoodNeeded();
  
  /**
   * Used to know if the bird is of type extinct or not.
   * @return if extinct in boolean
   */
  boolean getIsExtinct();
  
}

 
