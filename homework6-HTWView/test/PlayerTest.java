

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import mazes.GameControllerHelper;
import mazes.HuntCommand;
import mazes.Move;
import mazes.PerfectMaze;
import mazes.Player;
import mazes.RoomMaze;
import mazes.Shoot;
import mazes.WrappedRoomMaze;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for Player class.
 * 
 */
public class PlayerTest {
  
  private Player player;
  private GameControllerHelper gameControllerHelper = new GameControllerHelper(new RoomMaze());
  private Map<String, String> mazeParams = new HashMap<String, String>();
  
  /**
   * Sets up the testing.
   */
  @Before
  public void setUp() {
    
    player = new Player(0, 0, 3);
    mazeParams.put("mazeType", "room");
    mazeParams.put("remainingWalls", "2");
    mazeParams.put("numOfRows", "3");
    mazeParams.put("numOfCols", "3");
    mazeParams.put("numOfPits", "2");
    mazeParams.put("numOfBats", "1");
    
    mazeParams.put("player1StartX", "0");
    mazeParams.put("player1StartY", "0");
    mazeParams.put("player2StartX", "0");
    mazeParams.put("player2StartY", "1");
    mazeParams.put("numOfArrows", "3");
    mazeParams.put("numOfPlayers", "1");
    gameControllerHelper.createGame(mazeParams);
    
    
  }

  @Test
  public void testGetAvailableMoves1() {
    
    assertEquals("east\nnorth\n", gameControllerHelper.getAvailableMoves(player));
  }
  
  
  @Test
  public void testGetAvailableMoves2() {
    mazeParams.put("mazeType", "wrapped");
    gameControllerHelper.createGame(mazeParams);
    assertEquals("west\nnorth\nsouth\n", gameControllerHelper.getAvailableMoves(player));
  }
  
  
  
  @Test
  public void testMove1() {
    HuntCommand cmd = new Move("north");
    assertEquals("Your current location is:1.0,1.0You smell a Wumpus!You feel a Draft!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  @Test
  public void testMove2() {
    HuntCommand cmd = new Move("north");
    cmd.execute(gameControllerHelper, player);
    cmd = new Move("east");
    assertEquals("1.0 0.0 You were grabbed by a superbat Your current location is:1.0,1.0You "
        + "smell a Wumpus!You feel a Draft!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  
  @Test
  public void testMove3() {
    HuntCommand cmd = new Move("north");
    mazeParams.put("mazeType", "wrapped");
    gameControllerHelper.createGame(mazeParams);
    assertEquals("Your current location is:0.0,1.0You smell a Wumpus!You feel a Draft!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  @Test
  public void testMovePlayerThroughTunnel() {
    mazeParams.put("player1StartX", "1");
    mazeParams.put("player1StartY", "1");
    mazeParams.put("numOfBats", "0");
    gameControllerHelper.createGame(mazeParams);
    HuntCommand cmd = new Move("east");
    assertEquals("Your current location is:1.0,0.0You are eaten by a Wumpus! Game Over.", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  @Test
  public void testMoveThroughInvalidDoor() {
    HuntCommand cmd = new Move("west");
    assertEquals("Your current location is:0.0,0.0You smell a Wumpus!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  
  @Test
  public void testShoot1() {
    HuntCommand cmd = new Move("north");
    cmd.execute(gameControllerHelper, player);
    cmd = new Shoot("east", 1);
    assertEquals("Wumpus killed!! You Won", cmd.execute(gameControllerHelper, player));
  }
  
  
  @Test
  public void testWrappedMissedShoot1() {
    HuntCommand cmd = new Move("north");
    cmd.execute(gameControllerHelper, player);
    cmd = new Shoot("east", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(gameControllerHelper, player));
  }
  
    
  // Checks Wrapped shoot.
  @Test
  public void testShoot11() {
    mazeParams.put("mazeType", "wrapped");
    gameControllerHelper.createGame(mazeParams);
    HuntCommand cmd = new Shoot("west", 1);
    assertEquals("You missed the target! Good Luck!", cmd.execute(gameControllerHelper, player));
  }
  
  // Checks Wrapped Move
  @Test
  public void testMoveWrapped() {
    mazeParams.put("mazeType", "wrapped");
    gameControllerHelper.createGame(mazeParams);
    HuntCommand cmd = new Move("west");
    assertEquals("Your current location is:2.0,0.0You smell a Wumpus!You feel a Draft!", 
        cmd.execute(gameControllerHelper, player));
  }

  
  @Test
  public void testShootInvalidDoor() {
    HuntCommand cmd = new Shoot("west", 1);
    assertEquals("You missed the target! Good Luck!", cmd.execute(gameControllerHelper, player));
  }
  
  @Test
  public void testMovePerfectmaze() {
    HuntCommand cmd = new Move("north");
    mazeParams.put("mazeType", "perfect");
    gameControllerHelper.createGame(mazeParams);
    assertEquals("Your current location is:0.0,1.0You feel a Draft!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  @Test
  public void testShootPerfectmaze() {
    HuntCommand cmd = new Shoot("north", 1);
    mazeParams.put("mazeType", "perfect");
    gameControllerHelper.createGame(mazeParams);
    assertEquals("You missed the target! Good Luck!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  @Test
  public void testMoveShootPerfectmaze() {
    HuntCommand cmd = new Move("east");
    mazeParams.put("mazeType", "perfect");
    gameControllerHelper.createGame(mazeParams);
    cmd.execute(gameControllerHelper, player);

    cmd = new Shoot("north", 1);
    assertEquals("You missed the target! Good Luck!", 
        cmd.execute(gameControllerHelper, player));
    
  }
  
  
  @Test
  public void testPlayerToString() {
    assertEquals("Current Player Location:0.0,0.0\n", player.toString());
  }
  
  
  @Test
  public void testPlayerLostByArrowFinished() {
    player = new Player(0, 0, 1);
    mazeParams.put("numOfArrows", "1");
    gameControllerHelper.createGame(mazeParams);
    HuntCommand cmd = new Move("north");
    cmd = new Shoot("north", 1);
    assertEquals("You missed the target! You have no more Arrow! Game Over.", 
        cmd.execute(gameControllerHelper, player));
  }
  
  
  @Test
  public void testPlayerFallenInPit() {
    mazeParams.put("numOfPits", "4");
    mazeParams.put("mazeType", "room");
    mazeParams.put("player1StartX", "0");
    mazeParams.put("player1StartY", "0");
    gameControllerHelper.createGame(mazeParams);
    HuntCommand cmd = new Move("north");
    assertEquals("Your current location is:1.0,1.0You have fallen in the bottomless pit! "
        + "Game Over.", 
        cmd.execute(gameControllerHelper, player));
  }
  
  
  
  @Test
  public void testRoomMazeCreatedWallsCount() {
    assertEquals("10", new RoomMaze(3, 3, 2, 1, 1).toString());
  }
  
  
  @Test
  public void testWrappedRoomMazeCreatedWallsCount() {
    assertEquals("16", new WrappedRoomMaze(3, 3, 2, 1, 1).toString());
  }
  
  
  @Test
  public void testPerfectMazeCreatedWallsCount() {
    assertEquals("8", new PerfectMaze(3, 3, 1, 1).toString());
  }
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord1() {
    new Player(-1, 0, 1);
  } 
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord2() {
    new Player(1, -1, 1);
  } 
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord3() {
    new Player(1, 0, 0);
  } 
  
  
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMoveDirection() {
    new Move("nomomve");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidShootDirection() {
    new Shoot("nomomve", 1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidShootDistance() {
    new Shoot("north", -1);
  }
  
}
