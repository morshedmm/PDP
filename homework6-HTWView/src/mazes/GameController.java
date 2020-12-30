package mazes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the Controller for the game that control the game.
 * 
 */
public class GameController implements GameControllerInterface {
  
  private final Readable in;
  private final Appendable out;
  private List<Player> playersList = new ArrayList<Player>();
  private int curPlayerId;
  private Map<Player, String> playerMap = new HashMap<Player, String>();
  private Map<String, String> mazeParams = new HashMap<String, String>();
  private GameControllerHelper gameControllerHelper;
  private GameViewInterface view;
  private Maze maze = null;
  
  
  /**
   * Builds a controller object.
   * @param in takes input in Readable
   * @param out gives output in appendable
   */
  public GameController(Readable in, Appendable out) 
      throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Illegal arguments passed!!");
    }
    this.in = in;
    this.out = out;
    playersList = new ArrayList<Player>();
    curPlayerId = 0;
    playerMap = new HashMap<Player, String>();
    
  }
  
  private void playerStatus() throws IOException {
    
    for (int idx = 0; idx < Integer.valueOf(mazeParams.get("numOfPlayers")); idx++) {
      this.out.append("This is the curent location of Player" + String.valueOf(idx) + "\n");
      this.out.append(this.playersList.get(idx).toString());
      this.out.append("These are the options to move:\n");
      this.out.append(gameControllerHelper.getAvailableMoves(this.playersList.get(idx)));
      
    }
    
  }
  
  // Removes player after player is killed.
  private void removePlayer() {
    
    if (this.playersList.size() == 1) {
      gameControllerHelper.resetGame();
      return;
    } else {
      this.playersList.remove(curPlayerId);
    }
  }
  
  // Adds players before the game begins.
  private void handlePlayers() throws IOException {
    
    playersList = new ArrayList<Player>();
    playerMap = new HashMap<Player, String>();
    
    playersList.add(new Player(Integer.valueOf(mazeParams.get("player1StartX")), 
        Integer.valueOf(mazeParams.get("player1StartY")), 
        Integer.valueOf(mazeParams.get("numOfArrows"))));
    playerMap.put(playersList.get(0), "0");
    
    if (Integer.valueOf(mazeParams.get("numOfPlayers")) == 2) {
      playersList.add(new Player(Integer.valueOf(mazeParams.get("player2StartX")), 
          Integer.valueOf(mazeParams.get("player2StartY")), 
          Integer.valueOf(mazeParams.get("numOfArrows"))));
      playerMap.put(playersList.get(1), "1");
    }
    this.playerStatus();
    
  }
  
  // Updates player tag to show active player during the game.
  private void updateGuiPlayerTag(Player curPlayer, GameViewInterface view) {
    
    int x = Integer.valueOf(curPlayer.giveCurLocation().getLocationCoord().x);
    int y = Integer.valueOf(curPlayer.giveCurLocation().getLocationCoord().y);
    
    view.updateGuiPlayerTag(x, y);
    
  }

  // Updates the GUI.
  private void updateGui(Player curPlayer, String directions, GameViewInterface view, int id) 
      throws IOException {
    
    int x = Integer.valueOf(curPlayer.giveCurLocation().getLocationCoord().x);
    int y = Integer.valueOf(curPlayer.giveCurLocation().getLocationCoord().y);
    view.updateGuiImage(x, y, directions, Integer.valueOf(this.playerMap.get(playersList.get(id))));
    
  }
  
  
  /**
   * Starts the game for the given game model.
   * @throws IOException if inputs are not legal
   */
  @Override
  public void start(Maze maze) throws IOException {
    
    gameControllerHelper = new GameControllerHelper(maze);
    
    Scanner s = new Scanner(this.in);
    
    
    this.out.append("Enter maze type, perfect, room or wrapped\n");
    final String mazeType = s.nextLine();
    mazeParams.put("mazeType", mazeType);
    
    this.out.append("Enter remaining walls for room maze, will be ignored for perfect maze\n");
    final int remainingWalls = s.nextInt();
    mazeParams.put("remainingWalls", String.valueOf(remainingWalls));
    
    this.out.append("Enter number of rows of maze\n");
    final int numOfRows = s.nextInt();
    mazeParams.put("numOfRows", String.valueOf(numOfRows));
    this.out.append("Enter number of cols of maze\n");
    final int numOfCols = s.nextInt();
    mazeParams.put("numOfCols", String.valueOf(numOfCols));
    
    this.out.append("Enter number of caves with bottomless pit\n");
    int numOfPits = s.nextInt();
    mazeParams.put("numOfPits", String.valueOf(numOfPits));
    this.out.append("Enter number of caves with superbats\n");
    int numOfBats = s.nextInt();
    mazeParams.put("numOfBats", String.valueOf(numOfBats));
    
    gameControllerHelper.createGame(mazeParams);
    
    
    this.out.append("Enter number of Players\n");
    final int numOfPlayers = s.nextInt();
    mazeParams.put("numOfPlayers", String.valueOf(numOfPlayers));
    
    
    for (int idx = 0; idx < numOfPlayers; idx++) {
      this.out.append("Enter X coordinate of player" + String.valueOf(idx) + "\n");
      int startLocX = s.nextInt();
      mazeParams.put("player" + String.valueOf(idx + 1) + "StartX", String.valueOf(startLocX));
      this.out.append("Enter Y coordinate of player" + String.valueOf(idx) + "\n");
      int startLocY = s.nextInt();
      mazeParams.put("player" + String.valueOf(idx + 1) + "StartY", String.valueOf(startLocY));
      this.out.append("Enter number of arrows of player" + String.valueOf(idx) + "\n");
      int numOfArrows = s.nextInt();
      mazeParams.put("numOfArrows", String.valueOf(numOfArrows));
      playersList.add(new Player(startLocX, startLocY, numOfArrows));
      playerMap.put(playersList.get(idx), String.valueOf(idx));
    }
    
    this.handlePlayers();
    
    this.out.append("It is time for Player" + String.valueOf(this.curPlayerId) 
        + " to take action\n");
    
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
        String response = cmd.execute(gameControllerHelper, this.playersList.get(curPlayerId));
        String[] resSplit = response.split(" ");
        StringBuilder resString = new StringBuilder(response);
        if (resSplit[resSplit.length - 1].equals("Over.")) {
          resString.append("for Player" + playerMap.get(this.playersList.get(curPlayerId)));
          if (this.playersList.size() == 1) {
            this.out.append(resString.toString());
            s.close();
            return;
          } else {
            this.playersList.remove(curPlayerId);
          }
        } else if (resSplit[resSplit.length - 1]
            .equals("Won")) {
          resString.append(" Player" + playerMap.get(this.playersList.get(curPlayerId)));
          this.out.append(resString);
          this.out.append("\n");
          
          s.close();
          return;
        }
        this.out.append(resString);
        this.out.append("\n");
        
        if (this.playersList.size() > 1) {
          this.curPlayerId = (this.curPlayerId + 1) % this.playersList.size();
        } else {
          this.curPlayerId = 0;
        }
        
        
        this.out.append("It is time for Player" + playerMap.get(this.playersList.get(curPlayerId)) 
            + " to take action\n");
        
        this.out.append("Here are the options to move from current location:\n");
        
        this.out.append(gameControllerHelper.getAvailableMoves(this.playersList.get(curPlayerId)));
        
      }
      
      
    }
    s.close();

  }
  
  @Override
  public void start(GameViewInterface gameView, Maze maze) {
    gameView.setFeatures(this);
    this.maze = maze;
  }
  
  @Override
  public void exitProgram() {
    System.exit(0);
  }
  
  @Override
  public void startGame(String mazeType, String remainingWalls, String numOfRows, 
      String numOfCols, String numOfPits, String numOfBats, GameViewInterface view, 
      String player1StartX, String player1StartY, String player2StartX, String player2StartY, 
      String numOfArrows, String numOfPlayers) throws IOException {
    
    this.view = view;
    gameControllerHelper = new GameControllerHelper(this.maze);
    mazeParams.put("mazeType", mazeType);
    mazeParams.put("remainingWalls", remainingWalls);
    mazeParams.put("numOfRows", numOfRows);
    mazeParams.put("numOfCols", numOfCols);
    mazeParams.put("numOfPits", numOfPits);
    mazeParams.put("numOfBats", numOfBats);
    
    mazeParams.put("player1StartX", player1StartX);
    mazeParams.put("player1StartY", player1StartY);
    mazeParams.put("player2StartX", player2StartX);
    mazeParams.put("player2StartY", player2StartY);
    mazeParams.put("numOfArrows", numOfArrows);
    mazeParams.put("numOfPlayers", numOfPlayers);
    
    gameControllerHelper.createGame(mazeParams);
    view.createField(Integer.valueOf(numOfRows), Integer.valueOf(numOfCols));
    
    this.handlePlayers();
    
    this.updateGui(this.playersList.get(curPlayerId), gameControllerHelper
        .getAvailableMoves(this.playersList.get(curPlayerId)), view, 0);
    if (this.playersList.size() > 1) {
      this.updateGui(this.playersList.get(1), gameControllerHelper
          .getAvailableMoves(this.playersList.get(1)), view, 1);
    }
    view.toggleColor(curPlayerId);
    view.resetFocus();
  }
  
  @Override
  public void moveNorth() throws IOException {
    HuntCommand cmd = new Move("north");
    this.handlAction(cmd);
    
  }
  
  @Override
  public void moveSouth() throws IOException {
    HuntCommand cmd = new Move("south");
    this.handlAction(cmd);
    
  }
  
  @Override
  public void moveEast() throws IOException {
    HuntCommand cmd = new Move("east");
    this.handlAction(cmd);
    
  }
  
  @Override
  public void moveWest() throws IOException {
    HuntCommand cmd = new Move("west");
    this.handlAction(cmd);
    
  }
  
  @Override
  public void shootArrow(String direction, String distance) throws IOException {
    
    HuntCommand cmd = new Shoot(direction, Integer.valueOf(distance));
    this.handlAction(cmd);
    
  }
  
  // Handles actions of move or shoot.
  private void handlAction(HuntCommand cmd) throws IOException {
    if (cmd != null) {
      this.updateGuiPlayerTag(this.playersList.get(curPlayerId), view);
      String response = cmd.execute(gameControllerHelper, this.playersList.get(curPlayerId));
    
      // Asking GUI to update tunnel info
      ArrayList<Location> tunnel = (ArrayList<Location>) this.gameControllerHelper.getTunnel();
      for (int idx = 0; idx < tunnel.size(); idx++) {
        Player dummyPlayer = new Player((int) tunnel.get(idx).getLocationCoord().getX(), 
            (int) tunnel.get(idx).getLocationCoord().getY(), 1);
        int x = Integer.valueOf(dummyPlayer.giveCurLocation().getLocationCoord().x);
        int y = Integer.valueOf(dummyPlayer.giveCurLocation().getLocationCoord().y);
        this.view.updateGuiImageTunnel(x, y, gameControllerHelper.getAvailableMoves(dummyPlayer));
      
      }
      
      // Checking if a danger or reward found
      String[] resSplit = response.split(" ");
      int dangerFound = 0;
      for (String eachStr: resSplit) {
        if (eachStr.equals("grabbed")) {
          Player dummyPlayer2 = new Player((int) (Double.parseDouble(resSplit[0])), 
              (int) (Double.parseDouble(resSplit[1])), 1);
          this.updateGui(dummyPlayer2, "bats", this.view, this.curPlayerId);
        }
        if (eachStr.equals("smell")) {
          break;
        }
        if (eachStr.equals("Wumpus!")) {
          dangerFound = 1;
          this.updateGui(this.playersList.get(curPlayerId), "wumpus", this.view, this.curPlayerId);
          this.removePlayer();
          break;
        } else if (eachStr.equals("pit!")) {
          dangerFound = 1;
          this.updateGui(this.playersList.get(curPlayerId), "pit", this.view, this.curPlayerId);
          this.removePlayer();
          break;
        } else if (eachStr.equals("Won")) {
          dangerFound = 1;
          this.updateGui(this.playersList.get(curPlayerId), "player", this.view, this.curPlayerId);
          break;
        }
      }
      if (dangerFound == 0) {
        this.updateGui(this.playersList.get(curPlayerId), gameControllerHelper
            .getAvailableMoves(this.playersList.get(curPlayerId)), this.view, this.curPlayerId);
      }
    
      if (this.playersList.size() > 1) {
        this.curPlayerId = (this.curPlayerId + 1) % this.playersList.size();
      } else {
        this.curPlayerId = 0;
      }
      view.toggleColor(Integer.valueOf(playerMap.get(this.playersList.get(curPlayerId))));
      this.view.resetFocus();
    }
  }
}

