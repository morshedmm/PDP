package mazes;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Controller for the game that control the game.
 * 
 */
public class GameController {
  
  private final Readable in;
  private final Appendable out;
  
  /**
   * Builds a controller object.
   * @param in takes input in Readable
   * @param out gives output in appendable
   */
  public GameController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }
  
  
  /**
   * Starts the game for the given game model.
   * @param player the model used of type Game
   * @throws IOException if inputs are not legal
   */
  public void start(Game player) throws IOException {
    
    Scanner s = new Scanner(this.in);
    
    this.out.append("Enter maze type, perfect, room or wrapped\n");
    final String mazeType = s.nextLine();
    this.out.append("Enter remaining walls for room maze, will be ignored for perfect maze\n");
    final int remainingWalls = s.nextInt();
    this.out.append("Enter X coordinate of player\n");
    final int startLocX = s.nextInt();
    this.out.append("Enter Y coordinate of player\n");
    final int startLocY = s.nextInt();
    this.out.append("Enter number of rows of maze\n");
    final int numOfRows = s.nextInt();
    this.out.append("Enter number of cols of maze\n");
    final int numOfCols = s.nextInt();
    this.out.append("Enter number of arrows of player\n");
    int numOfArrows = s.nextInt();
    this.out.append("Enter number of caves with bottomless pit\n");
    int numOfPits = s.nextInt();
    this.out.append("Enter number of caves with superbats\n");
    int numOfBats = s.nextInt();
    
    player.launchGame(startLocX, startLocY, mazeType, numOfRows, numOfCols, remainingWalls, 
        numOfArrows, numOfPits, numOfBats);
    
    this.out.append(player.toString());
    this.out.append("Here are the options to move from current location:\n");
    this.out.append(player.getAvailableMoves());
    
    while (s.hasNext()) {
      
      
      String input = s.next();
      HuntCommand cmd = null;
      switch (input) {
          
        case "q":
        case "quit":
          this.out.append("Player ended the game!");
          s.close();
          return;
        
        case "move":
          cmd = new Move(s.next());
          break;
      
        case "shoot":
          cmd = new Shoot(s.next(), s.nextInt());
          break;
          
        default:
          this.out.append("not a valid input");
        
      
      }
      
      if (cmd != null) {
        String response = cmd.execute(player);
        String[] resSplit = response.split(" ");
        if (resSplit[resSplit.length - 1].equals("Over.") || resSplit[resSplit.length - 1]
            .equals("Won")) {
          this.out.append(response);
          this.out.append("\n");
          
          s.close();
          return;
        }
        this.out.append(response);
        this.out.append("\n");
        
        this.out.append("Here are the options to move from current location:\n");
        
        this.out.append(player.getAvailableMoves());
        
      }
      
      
    }
    s.close();

  }
}

