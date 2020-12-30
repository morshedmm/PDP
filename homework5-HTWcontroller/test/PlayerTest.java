

import static org.junit.Assert.assertEquals;

import mazes.Game;
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
  
  private Game player;
  private Game player1;
  private Game player2;
  private Game player3;
  private Game player4;
  
  /**
   * Sets up the testing.
   */
  @Before
  public void setUp() {
    
    player = new Player();
    player.launchGame(0, 0, "room", 3, 3, 2, 3, 3, 1);
    player1 = new Player();
    player1.launchGame(0, 0, "wrapped", 3, 3, 2, 3, 3, 1);
    player2 = new Player();
    player2.launchGame(0, 0, "perfect", 3, 3, 0, 3, 3, 1);
    player3 = new Player();
    player4 = new Player();
    player4.launchGame(0, 1, "wrapped", 3, 3, 2, 3, 3, 2);
    
    
  }

  @Test
  public void testGetAvailableMoves1() {
    
    assertEquals("east\nnorth\n", player.getAvailableMoves());
  }
  
  @Test
  public void testGetAvailableMoves2() {
    
    assertEquals("east\nnorth\nsouth\n", player1.getAvailableMoves());
  }
  
  @Test
  public void testGetAvailableMoves3() {
    
    assertEquals("east\nnorth\n", player2.getAvailableMoves());
  }
  
  @Test
  public void testMove1() {
    HuntCommand cmd = new Move("north");
    assertEquals("Your current location is:0.0,1.0You smell a Wumpus!You feel a Draft!", 
        cmd.execute(player));
    
  }
  
  @Test
  public void testMove2() {
    HuntCommand cmd = new Move("north");
    assertEquals("Your current location is:0.0,1.0You feel a Draft!", 
        cmd.execute(player1));
    
  }
  
  @Test
  public void testMove3() {
    HuntCommand cmd = new Move("north");
    assertEquals("Your current location is:0.0,1.0You feel a Draft!", 
        cmd.execute(player2));
    
  }
  
  @Test
  public void testMovePlayerThroughTunnel() {
    Game playerNew = new Player();
    playerNew.launchGame(0, 1, "room", 3, 3, 2, 3, 3, 0);
    HuntCommand cmd = new Move("south");
    assertEquals("Your current location is:2.0,1.0You are eaten by a Wumpus! Game Over.", 
        cmd.execute(playerNew));
    
  }
  
  @Test
  public void testMoveThroughInvalidDoor() {
    HuntCommand cmd = new Move("west");
    assertEquals("Your current location is:0.0,0.0You smell a Wumpus!", 
        cmd.execute(player));
    
  }
  
  @Test
  public void testShoot1() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player);
    cmd = new Shoot("east", 2);
    assertEquals("Wumpus killed!! You Won", cmd.execute(player));
  }
  
  @Test
  public void testShoot2() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player);
    cmd = new Shoot("south", 1);
    assertEquals("Wumpus killed!! You Won", cmd.execute(player));
  }
  
  @Test
  public void testShoot3() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player);
    cmd = new Shoot("south", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player));
  }
  
  @Test
  public void testShoot4() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Shoot("east", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player1));
  }
  
  @Test
  public void testShoot5() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Move("east");
    cmd.execute(player1);
    cmd = new Shoot("north", 1);
    assertEquals("Wumpus killed!! You Won", cmd.execute(player1));
  }
  
  @Test
  public void testShoot6() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Shoot("south", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player1));
  }
  
  @Test
  public void testShoot7() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player2);
    cmd = new Shoot("east", 1);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player2));
  }
  
  
  @Test
  public void testShoot8() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player2);
    cmd = new Shoot("south", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player2));
  }
  
  @Test
  public void testShoot9() {
    HuntCommand cmd = new Move("east");
    cmd.execute(player2);
    cmd = new Shoot("west", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player2));
  }
  
  @Test
  public void testShoot10() {
    HuntCommand cmd = new Move("east");
    cmd.execute(player2);
    cmd = new Shoot("north", 1);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player2));
  }
  
  // Checks Wrapped shoot.
  @Test
  public void testShoot11() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Shoot("west", 2);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player1));
  }
  
  @Test
  public void testShootInvalidDoor() {
    HuntCommand cmd = new Shoot("west", 1);
    assertEquals("You missed the target! Good Luck!", cmd.execute(player));
  }
  
  @Test
  public void testWrappedMove() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Move("west");
    assertEquals("Your current location is:2.0,1.0", cmd.execute(player1));
  }
  
  @Test
  public void testThrownBySuperBat() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Move("east");
    cmd.execute(player1);
    cmd = new Move("north");
    assertEquals("You were grabbed by a superbatYour current location is:1.0,"
        + "1.0You smell a Wumpus!", cmd.execute(player1));
  }
  
  @Test
  public void testDodgedSuperBat() {
    HuntCommand cmd = new Move("south");
    assertEquals("You dodged a superbat.Your current location is:0.0,0.0You "
        + "have fallen in the bottomless pit! Game Over.", cmd.execute(player4));
  }
  
  @Test
  public void testPlayerToString() {
    assertEquals("Current Player Location:0.0,0.0\n", player.toString());
  }
  
  @Test
  public void testPlayerLostByArrowFinished() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player1);
    cmd = new Shoot("north", 1);
    cmd.execute(player1);
    cmd = new Shoot("west", 1);
    cmd.execute(player1);
    cmd = new Shoot("east", 1);
    assertEquals("You missed the target! You have no more Arrow! Game Over.", 
        cmd.execute(player1));
  }
  
  @Test
  public void testPlayerFallenInPit() {
    HuntCommand cmd = new Move("north");
    cmd.execute(player);
    cmd = new Move("east");
    assertEquals("Your current location is:1.0,1.0You have fallen in the bottomless pit! "
        + "Game Over.", 
        cmd.execute(player));
  }
  
  @Test
  public void testPlayerKilledByWumpus() {
    Game player3 = new Player();
    player3.launchGame(0, 0, "room", 3, 3, 2, 3, 3, 0);
    HuntCommand cmd = new Move("east");
    cmd.execute(player3);
    assertEquals("Your current location is:2.0,1.0You are eaten by a Wumpus! Game Over.", 
        cmd.execute(player3));
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
    player3.launchGame(-1, 0, "perfect", 3, 3, 0, 3, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord2() {
    player3.launchGame(0, -1, "perfect", 3, 3, 0, 3, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMaze() {
    player3.launchGame(0, 0, "erfect", 3, 3, 0, 3, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMazeSize() {
    player3.launchGame(0, 0, "room", 0, 3, 0, 3, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMazeSize2() {
    player3.launchGame(0, 0, "room", 3, 0, 0, 3, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidRemainingWalls() {
    player3.launchGame(0, 0, "room", 3, 3, -1, 3, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfArrows() {
    player3.launchGame(0, 0, "room", 3, 3, 2, 0, 3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfPits() {
    player3.launchGame(0, 0, "wrapped", 3, 3, 2, 3, -3, 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNumOfBats() {
    player3.launchGame(0, 0, "wrapped", 3, 3, 2, 3, 3, -1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMoveDirection() {
    HuntCommand cmd = new Move("nomomve");
    cmd.execute(player);
  }


}
