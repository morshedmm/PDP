package codes;

/**
 * Represents character element with its count in the message.
 * 
 */
public class CharacterElement implements Comparable<CharacterElement> {
  
  private String element;
  private int count;
  
  /**
   * Creates a character element object.
   * @param element character in String
   * @param count number of occurrence in integer
   * @throws IllegalArgumentException if element is empty or count is less than one
   */
  public CharacterElement(String element, int count)
      throws IllegalArgumentException {
    if (element.length() < 1 || count < 1) {
      throw new IllegalArgumentException("Illegal arguments for character object!!");
    }
    
    this.element = element;
    this.count = count;
  }

  private int compareStrings(String str1, String str2) {
    
    int idx1 = 0;
    int idx2 = 0;
    
    while (idx1 < str1.length() && idx2 < str2.length()) {
      if (Character.compare(str1.charAt(idx1), str2.charAt(idx2)) < 0) {
        return -1;
      }
      if (Character.compare(str1.charAt(idx1), str2.charAt(idx2)) > 0) {
        return 1;
      }
      idx1++;
      idx2++;
    }
    if (str1.length() < str2.length()) {
      return -1;
    }
    if (str1.length() > str2.length()) {
      return 1;
    }
    return 0;
  }
  
  @Override
  public int compareTo(CharacterElement o) {
    
    String[] otherCharacter = o.toString().split("\\+");
    String otherElement = otherCharacter[0];
    int otherCount = Integer.valueOf(otherCharacter[1]);
    
    if (this.count < otherCount) {
      return -1;
    }
    
    if (this.count > otherCount) {
      return 1;
    }
    
    return this.compareStrings(this.element, otherElement);

  }
  
  @Override
  public String toString() {
    return String.format("%s+%d", this.element, this.count);
  }

  

}
