package conservatory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents Conservatory which has one or more aviaries, those can host birds.
 *
 */
public class Conservatory {
  
  static final int max_number_of_aviaries = 20;
  static final int max_number_of_birds_per_aviary = 5;
  static ArrayList<Aviary> aviaryList = new ArrayList<Aviary>();
  
  /**
   * This function adds an aviary if ID and location, both are unique.
   * 
   * @param locX X coordinate of an aviary
   * @param locY Y coordinate of an aviary 
   * @param id unique ID of an aviary
   * @return Aviary object is returned
   */
  public static Aviary addAviary(int locX, int locY, int id) {
    
    if (aviaryList.size() == max_number_of_aviaries) {
      return null;
    }
    
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      if (locX == aviaryList.get(idx).getLocationX() && locY == aviaryList.get(idx)
          .getLocationY()) {
        return null;
      }
      if (id == aviaryList.get(idx).getId()) {
        return null;
      }
    }
    
    Aviary newAviary = new Aviary(locX, locY, id, max_number_of_birds_per_aviary);
    aviaryList.add(newAviary);
    return newAviary;
    
  }
  
  /**
   * This function adds a bird to an aviary based upon some conditions.
   * 
   * @param bird object that represents a bird
   * @return a message is returned about the status of the bird assignment
   */
  public static String addBird(Bird bird) {
    
    if (bird.getIsExtinct()) {
      return "This bird can not be kept in this conservatory as it is extinct";
    }
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      boolean isAdded = aviaryList.get(idx).assignBird(bird);
      if (isAdded) {
        return  String.format("Bird %d was added to Aviary %d", bird.getBirdId(),
          aviaryList.get(idx).getId());
      }
    }
    return "No aviary has room to take this bird!!";
  }
  
  /**
   * Locates a bird in the conservatory from the ID.
   * 
   * @param birdId unique ID of the bird
   * @return Message returned to tell the aviary number
   */
  public static String locateBird(int birdId) {
    
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      ArrayList<Bird> curBirdsList = aviaryList.get(idx).getBirdsList();
      for (int jdx = 0; jdx < curBirdsList.size(); jdx++) {
        if (curBirdsList.get(jdx).getBirdId() == birdId) {
          return String.format("Bird %d lives in Aviary %d", 
            birdId, aviaryList.get(idx).getId());
        }
      }
    }
    return String.format("Bird %d was not found in this conservatory", birdId);
  }
  
  /**
   * Sign is printed of the aviary that prints all the bird types and descriptions.
   * 
   * @param aviaryId which aviary's sign will be printed
   */
  public static void aviarySign(int aviaryId) {
    
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      if (aviaryList.get(idx).getId() == aviaryId) {
        ArrayList<Bird> curBirdsList = aviaryList.get(idx).getBirdsList();
        if (curBirdsList.size() == 0) {
          System.out.println("No bird lives in this aviary");
          return;
        }
        System.out.println(String.format("Aviary %d hosts following birds:", aviaryId));
        for (int jdx = 0; jdx < curBirdsList.size(); jdx++) {
          System.out.println(String.format("Bird %d is a %s, %s colored and %s", 
              curBirdsList.get(jdx).getBirdId(), curBirdsList.get(jdx).getType(),
              curBirdsList.get(jdx).getBirdColor(), curBirdsList.get(jdx).getDescription()));
          System.out.println("********");
        }
      }
    }
    return;
  }
  
  /**
   * All the aviary locations are printed along with bird ID and type they host.
   */
  public static void aviaryMap() {
    
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      System.out.println(String.format("Aviary %d is located at (%d, %d)", 
          aviaryList.get(idx).getId(), aviaryList.get(idx).getLocationX(), 
          aviaryList.get(idx).getLocationY()));
      ArrayList<Bird> curBirdsList = aviaryList.get(idx).getBirdsList();
      if (curBirdsList.size() == 0) {
        System.out.println("It has no bird yet!!");
        continue;
      }
      System.out.println("It has following birds:");
      for (int jdx = 0; jdx < curBirdsList.size(); jdx++) {
        System.out.println(String.format("Bird %d, which is a %s", 
            curBirdsList.get(jdx).getBirdId(), curBirdsList.get(jdx).getType()));
      }      
    }
    return;
  }
  
  /**
   * All the birds are sorted and their locations are printed.
   */
  public static void aviaryIndex() {
    
    ArrayList<Integer> allBirds = new ArrayList<Integer>();
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      ArrayList<Bird> curBirdsList = aviaryList.get(idx).getBirdsList();
      for (int jdx = 0; jdx < curBirdsList.size(); jdx++) {
        allBirds.add(curBirdsList.get(jdx).getBirdId());
      }
    }
    
    Collections.sort(allBirds);
    for (int idx = 0; idx < allBirds.size(); idx++) {
      System.out.println(String.format("Bird %d:", allBirds.get(idx)));
      String birdLoc = locateBird(allBirds.get(idx));
      String[] birdLocSplit = birdLoc.split(" ");
      System.out.println(String.format("Located in Aviary %d", Integer.parseInt(birdLocSplit[5])));
      
    }
  }
  
  /**
   * The food requirements of the aviary in detail are returned.
   * 
   * @return food name and total quantity
   */
  public static HashMap<String, Integer> getFoodAmount() {
    
    HashMap<String, Integer> conservatoryFoodNeeded = new HashMap<String, Integer>();
    for (int idx = 0; idx < aviaryList.size(); idx++) {
      HashMap<String, Integer> foodMap = aviaryList.get(idx).getFoodQuantity(); 
      for (Map.Entry mapElement : foodMap.entrySet()) {
        String foodName = (String) mapElement.getKey();
        int foodQuantity = (int) mapElement.getValue();
        if (conservatoryFoodNeeded.containsKey(foodName)) {
          conservatoryFoodNeeded.put(foodName, (foodQuantity + conservatoryFoodNeeded
              .get(foodName)));
        } else {
          conservatoryFoodNeeded.put(foodName, foodQuantity);
        }
      }
    }
    return conservatoryFoodNeeded;
  }
  
  /**
   * Test Run 1.
   */
  public static void testRun1() {
    
    Bird myBird1 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", 
        false, "Black", "Was sick while captured", "Eagles", "fruit 2");
    Bird myBird2 = new Fightless(2, 1, 2, true, false,
        "White", "Was not sick while captured", "swans", "insects 5");
    Bird myBird3 = new WaterFowl(3, 3, 2, 1, false,
        "Brown", "Was not sick while captured", "swans", "insects 5 fish 3", "salt", true);
    Bird myBird4 = new Fightless(4, 1, 2, true, false,
        "Blue", "Was not sick while captured", "Duck", "insects 7");
    Bird myBird5 = new Parrot(5, 3, 2, 10, false,
        "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
    Bird myBird6 = new Prey(6, 1, 2, "Wings are damaged, cannot fly", "Likes Insects", false,
        "Black", "Was sick while captured", "Eagles", "nuts 12");
    Bird myBirdExtinct = new Fightless(51, 2, 2, true, true,
        "Blue", "Was not sick while captured", "Duck", "insects 7");
    Bird myBird7 = new ShoreBird(7, 3, 2, "Asia", false,
        "Black", "Was sick while captured", "Auk", "insects 15", "freshwater", true);
    Bird myBird8 = new Owl(8, 1, 2, 1, false,
        "White", "Very gentle", "nativeowl", "insects 3 fish 2");
    Bird myBird9 = new Owl(9, 1, 2, 1, false,
        "White", "Gentle", "asianeowl", "insects 15 fish 1");
    Bird myBird10 = new Pigeon(10, 2, 2, false, 1,
        "White", "Very active during day", "dove", "nuts 8");
    
    Aviary newAviary1 = addAviary(1, 1, 1);
    Aviary newAviaryCopy1 = addAviary(1, 1, 1);
    Aviary newAviary2 = addAviary(2, 2, 2);
    Aviary newAviary3 = addAviary(3, 3, 3);
    Aviary newAviary4 = addAviary(4, 4, 4);
    
    System.out.println("Adding Birds and printing Info:");
    System.out.println("********");
    System.out.println(addBird(myBird1));
    System.out.println(addBird(myBird2));
    System.out.println(addBird(myBird3));
    System.out.println(addBird(myBird4));
    System.out.println(addBird(myBird5));
    System.out.println(addBird(myBird6));
    System.out.println(addBird(myBirdExtinct));
    System.out.println(addBird(myBird7));
    System.out.println(addBird(myBird8));
    System.out.println(addBird(myBird9));
    System.out.println(addBird(myBird10));
    System.out.println("********");
    
    System.out.println("Printing total food requirements of conservatory");
    System.out.println("********");
    HashMap<String, Integer> foodMap = getFoodAmount();
    for (Map.Entry mapElement : foodMap.entrySet()) {
      String foodName = (String) mapElement.getKey();
      int foodQuantity = (int) mapElement.getValue();
      System.out.println(foodName + " : " + foodQuantity);
    }
    
    System.out.println("********");
    System.out.println("Locating a bird in all aviaries:");
    System.out.println(locateBird(2));
    System.out.println("********");
    
    System.out.println("PRINTING AVIARY SIGN");
    aviarySign(2);
    System.out.println("PRINTING AVIARY MAP");
    aviaryMap();
    System.out.println("PRINTING AVIARY INDEX");
    aviaryIndex();
    
  }
  
  /**
   * Test Run 2.
   */
  public static void testRun2() {
    Bird myBird7 = new ShoreBird(7, 3, 2, "Asia", false,
        "Black", "Was sick while captured", "Auk", "insects 15", "freshwater", true);
    Bird myBird5 = new Parrot(5, 3, 2, 10, false,
        "Green", "Was not sick while captured", "Duck", "insects 5 fish 3", "Hi");
    Bird myBird2 = new Fightless(2, 1, 2, true, false,
        "White", "Was not sick while captured", "swans", "insects 5");
    Bird myBird10 = new Pigeon(10, 2, 2, false, 1,
        "White", "Very active during day", "dove", "nuts 8");
    Bird myBird3 = new WaterFowl(3, 3, 2, 1, false,
        "Brown", "Was not sick while captured", "swans", "insects 5 fish 3", "salt", true);
    Bird myBird1 = new Prey(1, 1, 2, "Very Dangerous for other birds", "Captures other birds", 
        false, "Black", "Was sick while captured", "Eagles", "fruit 2");
    Bird myBird9 = new Owl(9, 1, 2, 1, false,
        "White", "Gentle", "asianeowl", "insects 15 fish 1");
    Bird myBird4 = new Fightless(4, 1, 2, true, false,
        "Blue", "Was not sick while captured", "Duck", "insects 7");
    Bird myBird8 = new Owl(8, 1, 2, 1, false,
        "White", "Very gentle", "nativeowl", "insects 3 fish 2");
    Bird myBird6 = new Prey(6, 1, 2, "Wings are damaged, cannot fly", "Likes Insects", false,
        "Black", "Was sick while captured", "Eagles", "nuts 12");
    
    Aviary newAviary1 = addAviary(1, 1, 1);
    Aviary newAviary2 = addAviary(2, 2, 2);
    Aviary newAviary3 = addAviary(3, 3, 3);
    Aviary newAviary4 = addAviary(4, 4, 4);
    
    System.out.println("Adding Birds and printing Info:");
    System.out.println("********");
    System.out.println(addBird(myBird7));
    System.out.println(addBird(myBird5));
    System.out.println(addBird(myBird2));
    System.out.println(addBird(myBird10));
    System.out.println(addBird(myBird3));
    System.out.println(addBird(myBird1));
    System.out.println(addBird(myBird9));
    System.out.println(addBird(myBird4));
    System.out.println(addBird(myBird8));
    System.out.println(addBird(myBird6));
    System.out.println("********");
    
    System.out.println("Printing total food requirements of conservatory");
    System.out.println("********");
    HashMap<String, Integer> foodMap = getFoodAmount();
    for (Map.Entry mapElement : foodMap.entrySet()) {
      String foodName = (String) mapElement.getKey();
      int foodQuantity = (int) mapElement.getValue();
      System.out.println(foodName + " : " + foodQuantity);
    }
    
    System.out.println("********");
    System.out.println("Locating a bird in all aviaries:");
    System.out.println(locateBird(6));
    System.out.println("********");
    
    System.out.println("PRINTING AVIARY SIGN");
    aviarySign(1);
    System.out.println("PRINTING AVIARY MAP");
    aviaryMap();
    System.out.println("PRINTING AVIARY INDEX");
    aviaryIndex();
    
  }
  
  /**
   * Main used as a driver.
   */
  public static void main(String[] args) {
    
    if (args.length > 0) {
      int arg1 = Integer.parseInt(args[0]);
      if (arg1 == 1) {
        testRun1();
      } else if (arg1 == 2) {
        testRun2();
      }
    }
    
  }

}
