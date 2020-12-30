package conservatory;


/**
 * This class implements the common functionalities of different bird classifications those are 
 * in Bird class.
 *
 */
public abstract class AbstractBird implements Bird {
  
  protected int id;
  protected int age;
  protected int numOfWings;
  protected boolean isExtinct;
  protected String color;
  protected String description;
  protected String type;
  protected String foodNeeded;

  @Override
  public int getBirdId() {
    return this.id;
  }

  @Override
  public String getBirdColor() {
    return this.color;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  @Override
  public int getNumOfWings() {
    return this.numOfWings;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public String getFoodNeeded() {
    return this.foodNeeded;
  }

  @Override
  public boolean getIsExtinct() {
    return this.isExtinct;
  }
  
  /**
   * Used to know the class of the bird by overriding the default toString method.
   */
  @Override
  public String toString() {
    return String.format("%s", this.getClass());
  }

}
