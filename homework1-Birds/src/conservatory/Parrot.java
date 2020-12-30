package conservatory;

/**
 * This class represent bird class named Parrot which extends AbstractBird class.
 *
 */
public final class Parrot extends AbstractBird {
  
  private int wordCount;
  private String favWords;
  
  
  /**
   * This function sets the value of word count and favorite words for Parrot class.
   * 
   * @param wordCount number of words the bird can remember in Integer
   * @param favWords favorite words of the bird in String
   * @throws IllegalArgumentException exceptions are thrown for a negative word count number
   */
  public Parrot(int id, int age, int numOfWings, int wordCount, boolean isExtinct,
      String color, String description, String type, String foodNeeded, String favWords) 
      throws IllegalArgumentException {
    if (id < 0 || age < 0 || numOfWings < 0 || wordCount < 0) {
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
    this.wordCount = wordCount;
    this.favWords = favWords;
  }
  
  /**
   * This function gives number of words the bird can remember.
   * @return number of words remembered in integer
   */
  public int getWordCount() {
    return this.wordCount;
  }

  /**
   * This function gives favorite words of the bird.
   * @return favorite words in String
   */
  public String getFavWords() {
    return this.favWords;
  }
}
