import battle.Battle;


/**
 * Driver class to test the battle.
 * 
 */
public class BattleDriver {
  
  /**
   * First test function.
   */
  public static void test1() {
    Battle battle = new Battle();
    battle.addCharacter("Spiderman", 500, 450);
    battle.addCharacter("Batman", 250, 200);
    battle.addHeadGear("Stronger Helmet", 5);
    battle.addHeadGear("Zombie Cap", 7);
    battle.addHandGear("Scary Gloves", 13);
    battle.addHandGear("Beautiful Ring", 23);
    battle.addHandGear("Larger Gloves", 16);
    battle.addHandGear("Pathetic Nail", 30);
    battle.addFootWear("Beautiful Sandals", 20, 10);
    battle.addFootWear("Ugly Boot", 10, 5);
    battle.addFootWear("Smaller Sock", 17, 8);
    battle.addFootWear("Larger Shoe", 8, 11);
    
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    battle.assignDress("Spiderman");
    
    
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman");
    battle.assignDress("Batman"); 
    
    System.out.println("Spiderman:");
    System.out.println(battle.characterInfo("Spiderman"));
    System.out.println("Batman:");
    System.out.println(battle.characterInfo("Batman"));
    System.out.println(battle.printWinner());
    
  }
  
  /**
   * Second test function.
   */
  public static void test2() {
    Battle battle = new Battle();
    battle.addCharacter("Spiderman", 500, 450);
    battle.addCharacter("Batman", 250, 200);
    battle.addHeadGear("Stronger Helmet", 5);
    battle.addHeadGear("Zombie Cap", 7);
    battle.addHandGear("Scary Gloves", 13);
    battle.addHandGear("Beautiful Ring", 23);
    battle.addHandGear("Larger Gloves", 16);
    battle.addHandGear("Pathetic Nail", 30);
    battle.addFootWear("Beautiful Sandals", 20, 10);
    battle.addFootWear("Ugly Boot", 10, 5);
    battle.addFootWear("Smaller Sock", 17, 8);
    battle.addFootWear("Larger Shoe", 8, 11);
    
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman");
    battle.assignDress("Batman");
    battle.assignDress("Spiderman"); 
    
    System.out.println("Spiderman:");
    System.out.println(battle.characterInfo("Spiderman"));
    System.out.println("Batman:");
    System.out.println(battle.characterInfo("Batman"));
    System.out.println(battle.printWinner());
    
  }
  
  /**
   * Gives the results after the battle.
   * @param args no argument
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      int arg1 = Integer.parseInt(args[0]);
      if (arg1 == 1) {
        test1();
      } else if (arg1 == 2) {
        test2();
      }
    }
  }



}
