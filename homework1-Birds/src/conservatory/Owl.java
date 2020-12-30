package conservatory;

/**
 * This class represent bird class named Owl which extends AbstractBird class.
 */
public final class Owl extends AbstractBird {
  
  private int discLen;
  
  /**
   * This function sets the value of disc length for Owl class.
   * @param discLen length of the disc in integer
   */
  public Owl(int id, int age, int numOfWings, int discLen, boolean isExtinct,
      String color, String description, String type, String foodNeeded) 
      throws IllegalArgumentException {
    if (id < 0 || age < 0 || numOfWings < 0 || discLen < 0) {
      throw new IllegalArgumentException("Negative values are not allowed for ID, Age or Wings");
    }
    
    this.id = id;
    this.age = age;
    this.numOfWings = numOfWings;
    this.discLen = discLen;
    this.isExtinct = isExtinct;
    this.color = color;
    this.description = description;
    this.type = type;
    this.foodNeeded = foodNeeded;
  }
  
  /**
   * This function returns the value of disc length for Owl class.
   * @return length of disc in Integer
   */
  public int getDiscLen() {
    return this.discLen;
  }

}
