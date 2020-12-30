package mazes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 * Creates a GUI using JFrame. It is able to take all actions and show the results on it's
 * Pane using a separate Game are and Control area.
 *
 */
public class GameView extends JFrame implements GameViewInterface {
  
  private static final long serialVersionUID = 1L;
  private static final String filePath = "./images";

  private JPanel mazeArea = new JPanel();
  
  
  private JLabel displayPlayer0 = new JLabel();
  private JLabel displayPlayer1 = new JLabel();
  private JButton exitButton = new JButton();
  private JComboBox<String> mazeTypeField = new JComboBox<String>();
  private JComboBox<String> remainingWallsField = new JComboBox<String>();
  private JComboBox<String> numOfRowsField = new JComboBox<String>();
  private JComboBox<String> numOfColsField = new JComboBox<String>();
  private JComboBox<String> numOfPitsField = new JComboBox<String>();
  private JComboBox<String> numOfBatsField = new JComboBox<String>();
  private JComboBox<String> numOfPlayersField = new JComboBox<String>();
  private JComboBox<String> player1StartXField = new JComboBox<String>();
  private JComboBox<String> player1StartYField = new JComboBox<String>();
  private JComboBox<String> player2StartXField = new JComboBox<String>();
  private JComboBox<String> player2StartYField = new JComboBox<String>();
  private JComboBox<String> numOfArrowsField = new JComboBox<String>();
  private JComboBox<String> shootDirectionField = new JComboBox<String>();
  private JComboBox<String> shootDistanceField = new JComboBox<String>();
  
  private JButton startGame = new JButton();
  private JButton moveNorth = new JButton();
  private JButton moveSouth = new JButton();
  private JButton moveEast = new JButton();
  private JButton moveWest = new JButton();
  private JButton shootButton = new JButton();
  
  private List<ArrayList<JLabel>> imageMap = new ArrayList<ArrayList<JLabel>>();
  
  /**
   * Constructs a GUI using the passed parameters.
   * @param caption a caption in String
   * @param displayP0 a text display in String 
   * @param displayP1 a text display in String
   * @param mazeType type of maze in String
   * @param remainWalls number of remaining walls in String
   * @param numRow number of rows in String
   * @param numCol number of columns in String
   * @param numPit number of pits in String
   * @param numBat number of bats in String
   * @param numPlayer number of players in String
   * @param playerX0 player0 x start location
   * @param playerY0 player0 y start location
   * @param playerX1 player1 x start location
   * @param playerY1 player1 y start location
   * @param numArro number of arrows in String
   * @param shootDir shoot direction of arrow in String
   * @param shootDist shoot distance of arrow in String
   * @throws IllegalArgumentException if the arguments are null
   */
  public GameView(String caption, String displayP0, String displayP1, 
      ComboBoxModel<String> mazeType, ComboBoxModel<String> remainWalls, 
      ComboBoxModel<String> numRow, ComboBoxModel<String> numCol, 
      ComboBoxModel<String> numPit, ComboBoxModel<String> numBat, 
      ComboBoxModel<String> numPlayer, ComboBoxModel<String> playerX0, 
      ComboBoxModel<String> playerY0, ComboBoxModel<String> playerX1, 
      ComboBoxModel<String> playerY1, ComboBoxModel<String> numArro, 
      ComboBoxModel<String> shootDir, ComboBoxModel<String> shootDist) 
          throws IllegalArgumentException {
    
    super(caption);
    
    if (caption == null || displayP0 == null || displayP1 == null || mazeType == null 
        || remainWalls == null || numRow == null || numCol == null || numBat == null
        || numPlayer == null || playerX0 == null || playerY0 == null || playerX1 == null
        || playerY1 == null || numArro == null || shootDir == null || shootDist == null) {
      throw new IllegalArgumentException("Illegal arguments passed to display!!");
    }
    
    
    displayPlayer0 = new JLabel(displayP0);
    displayPlayer1 = new JLabel(displayP1);
    exitButton = new JButton("");
    mazeTypeField = new JComboBox<String>(mazeType);
    remainingWallsField = new JComboBox<String>(remainWalls);
    numOfRowsField = new JComboBox<String>(numRow);
    numOfColsField = new JComboBox<String>(numCol);
    numOfPitsField = new JComboBox<String>(numPit);
    numOfBatsField = new JComboBox<String>(numBat);
    numOfPlayersField = new JComboBox<String>(numPlayer);
    player1StartXField = new JComboBox<String>(playerX0);
    player1StartYField = new JComboBox<String>(playerY0);
    player2StartXField = new JComboBox<String>(playerX1);
    player2StartYField = new JComboBox<String>(playerY1);
    numOfArrowsField = new JComboBox<String>(numArro);
    shootDirectionField = new JComboBox<String>(shootDir);
    shootDistanceField = new JComboBox<String>(shootDist);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.createGui(this.getContentPane());
    
    pack();
    setVisible(true);
    
  }
  
  /**
   * Constructs a GUI using passed caption and default parameters.
   * @param caption a caption in String
   * @throws IllegalArgumentException if caption is null
   */
  public GameView(String caption) 
      throws IllegalArgumentException {
    
    super(caption);
    if (caption == null) {
      throw new IllegalArgumentException("Illegal Title!!");
    }
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.createGui(this.getContentPane());
    
    pack();
    setVisible(true);

  }
  

  @Override
  public void createField(int numOfRows, int numOfCols) {
    
    imageMap = new ArrayList<ArrayList<JLabel>>();
    
    mazeArea.removeAll();
    mazeArea.revalidate();
    mazeArea.repaint();
    
    mazeArea.setLayout(new GridLayout(numOfRows, numOfCols));
    
    for (int idx = 0; idx < numOfCols; idx++) {
      imageMap.add(new ArrayList<JLabel>());
      for (int jdx = 0; jdx < numOfRows; jdx++) {
        imageMap.get(idx).add(null);
      }
      
    }
    
    // GUI is created from left to right starting at the top. So need to map 
    // according to match actual origin which is bottom left and other coordiantes.
    for (int jdx = numOfRows - 1; jdx >= 0; jdx--) {
      int idx = 0;
      
      while (idx < numOfCols) {
        JLabel label1 = new JLabel();
        imageMap.get(idx).set(jdx, label1);
      
        idx++;
    
        mazeArea.add(label1);
    
    
        try {
          BufferedImage myPicture1 = ImageIO.read(new File(filePath + "/black.png"));
          label1.setIcon(new ImageIcon(myPicture1));
      
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  
  
  @Override
  public void updateGuiPlayerTag(int x, int y) {
    
    this.imageMap.get(x).get(y).setText("");
    
  }
  
  @Override
  public void updateGuiImage(int x, int y, String newDirections, int id) throws IOException {
    
    if (newDirections.equals("bats")) {
      String imageName = filePath + "/bats.png";
      this.imageMap.get(x).get(y).setIcon(new ImageIcon(ImageIO.read(new File(imageName))));
      this.imageMap.get(x).get(y).setText(String.valueOf(id));
      return;
    }
    
    if (newDirections.equals("wumpus")) {
      String imageName = filePath + "/wumpus.png";
      this.imageMap.get(x).get(y).setIcon(new ImageIcon(ImageIO.read(new File(imageName))));
      this.imageMap.get(x).get(y).setText(String.valueOf(id));
      return;
    } else if (newDirections.equals("pit")) {
      String imageName = filePath + "/pit.png";
      this.imageMap.get(x).get(y).setIcon(new ImageIcon(ImageIO.read(new File(imageName))));
      this.imageMap.get(x).get(y).setText(String.valueOf(id));
      return;
    } else if (newDirections.equals("player")) {
      String imageName = filePath + "/player.png";
      this.imageMap.get(x).get(y).setIcon(new ImageIcon(ImageIO.read(new File(imageName))));
      this.imageMap.get(x).get(y).setText(String.valueOf(id));
      return;
    }
    
    String[] directions = newDirections.split("\\r?\\n");
    String[] allDirections = {"east", "north", "south", "west"};
    StringBuilder imageSegments = new StringBuilder();
    for (String curDirect: allDirections) {
      for (String givenDirect: directions) {
        if (curDirect.equals(givenDirect)) {
          imageSegments.append(curDirect);
          break;
        }
      }
    }
    String imageName = filePath + "/" + imageSegments.toString() + ".png";
    System.out.println(imageSegments.toString());
    this.imageMap.get(x).get(y).setIcon(new ImageIcon(ImageIO.read(new File(imageName))));
    this.imageMap.get(x).get(y).setText(String.valueOf(id));
    
  }
  
  @Override
  public void updateGuiImageTunnel(int x, int y, String newDirections) throws IOException {
    
    String[] directions = newDirections.split("\\r?\\n");
    String[] allDirections = {"east", "north", "south", "west"};
    StringBuilder imageSegments = new StringBuilder();
    for (String curDirect: allDirections) {
      for (String givenDirect: directions) {
        if (curDirect.equals(givenDirect)) {
          imageSegments.append(curDirect);
          break;
        }
      }
    }
    String imageName = filePath + "/" + imageSegments.toString() + ".png";
    System.out.println(imageSegments.toString());
    this.imageMap.get(x).get(y).setIcon(new ImageIcon(ImageIO.read(new File(imageName))));
    
    
  }
  
  
  private void createControlPanel(JPanel control) {
    
    String[] mazeValues = new String[] {"room", "wrapped", "perfect"};
    mazeTypeField = new JComboBox<String>(mazeValues);
    control.add(mazeTypeField);
    JLabel mazeType = new JLabel("MazeType");
    control.add(mazeType);
    
    String[] wallsValues = new String[] {"2", "3", "4"};
    remainingWallsField = new JComboBox<String>(wallsValues);
    remainingWallsField.setEditable(true);
    control.add(remainingWallsField);
    JLabel remainingWalls = new JLabel("RemainingWalls");
    control.add(remainingWalls);
    
    String[] rowValues = new String[] {"3", "4", "6"};
    numOfRowsField = new JComboBox<String>(rowValues);
    numOfRowsField.setEditable(true);
    control.add(numOfRowsField);
    JLabel numOfRows = new JLabel("NumberOfRows");
    control.add(numOfRows);
    
    String[] colValues = new String[] {"3", "4", "6"};
    numOfColsField = new JComboBox<String>(colValues);
    numOfColsField.setEditable(true);
    control.add(numOfColsField);
    JLabel numOfCols = new JLabel("NumOfCols");
    control.add(numOfCols);
    
    String[] pitValues = new String[] {"0", "2", "3"};
    numOfPitsField = new JComboBox<String>(pitValues);
    numOfPitsField.setEditable(true);
    control.add(numOfPitsField);
    JLabel numOfPits = new JLabel("NumOfPits");
    control.add(numOfPits);
    
    String[] batValues = new String[] {"0", "1", "2"};
    numOfBatsField = new JComboBox<String>(batValues);
    numOfBatsField.setEditable(true);
    control.add(numOfBatsField);
    JLabel numOfBats = new JLabel("NumOfBats");
    control.add(numOfBats);
    
    String[] playValues = new String[] {"1", "2"};
    numOfPlayersField = new JComboBox<String>(playValues);
    numOfPlayersField.setEditable(true);
    control.add(numOfPlayersField);
    JLabel numOfPlayers = new JLabel("NumOfPlayers");
    control.add(numOfPlayers);
    
    String[] valueOfX0 = new String[] {"0", "1", "2"};
    player1StartXField = new JComboBox<String>(valueOfX0);
    player1StartXField.setEditable(true);
    control.add(player1StartXField);
    JLabel player1StartX = new JLabel("Player1StartX");
    control.add(player1StartX);
    
    String[] valueOfY0 = new String[] {"0", "1", "2"};
    player1StartYField = new JComboBox<String>(valueOfY0);
    player1StartYField.setEditable(true);
    control.add(player1StartYField);
    JLabel player1StartY = new JLabel("Player1StartY");
    control.add(player1StartY);
    
    String[] valueOfX1 = new String[] {"0", "1", "2"};
    player2StartXField = new JComboBox<String>(valueOfX1);
    player2StartXField.setEditable(true);
    control.add(player2StartXField);
    JLabel player2StartX = new JLabel("Player2StartX");
    control.add(player2StartX);
    
    String[] valueOfY1 = new String[] {"0", "1", "2"};
    player2StartYField = new JComboBox<String>(valueOfY1);
    player2StartYField.setEditable(true);
    control.add(player2StartYField);
    JLabel player2StartY = new JLabel("Player2StartY");
    control.add(player2StartY);
    
    String[] arrowValues = new String[] {"1", "2", "3"};
    numOfArrowsField = new JComboBox<String>(arrowValues);
    numOfArrowsField.setEditable(true);
    control.add(numOfArrowsField);
    JLabel numOfArrows = new JLabel("NumOfArrows");
    control.add(numOfArrows);
    
    displayPlayer0 = new JLabel("Player 0");
    control.add(displayPlayer0);
    
    displayPlayer1 = new JLabel("Player 1");
    control.add(displayPlayer1);
    
    startGame = new JButton("StartGame");
    startGame.setActionCommand("Start Game");
    control.add(startGame);
    
    // exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    control.add(exitButton);
    
    moveEast = new JButton("MoveEast");
    moveEast.setActionCommand("Move East");
    control.add(moveEast);
    
    moveWest = new JButton("MoveWest");
    moveWest.setActionCommand("Move West");
    control.add(moveWest);
    
    moveNorth = new JButton("MoveNorth");
    moveNorth.setActionCommand("Move North");
    control.add(moveNorth);
    
    moveSouth = new JButton("MoveSouth");
    moveSouth.setActionCommand("Move South");
    control.add(moveSouth);
    
    String[] shootDistanceArr = new String[] {"1", "2", "3"};
    shootDistanceField = new JComboBox<String>(shootDistanceArr);
    control.add(shootDistanceField);
    JLabel shootDistance = new JLabel("ShootDistance");
    control.add(shootDistance);
    
    String[] shootDirectionArr = new String[] {"east", "west", "north", "south"};
    shootDirectionField = new JComboBox<String>(shootDirectionArr);
    control.add(shootDirectionField);
    JLabel shootDirection = new JLabel("ShootDirection");
    control.add(shootDirection);
    
    shootButton = new JButton("ShootArrow");
    shootButton.setActionCommand("Shoot Arrow");
    control.add(shootButton);
    
  }
  
  
  private void createGui(final Container pane) {
    
    JPanel control = new JPanel();
    control.setLayout(new GridLayout(20, 2));
    
    JScrollPane scrollPane = new JScrollPane(mazeArea);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
    this.createControlPanel(control);
    
    pane.add(scrollPane, BorderLayout.NORTH);
    pane.add(new JSeparator(), BorderLayout.CENTER);
    pane.add(control, BorderLayout.SOUTH);
  }
  
  @Override
  public void setFeatures(GameController f) {
    
    exitButton.addActionListener(l -> f.exitProgram());
    
    startGame.addActionListener(l -> {
      try {
        f.startGame((String) mazeTypeField.getSelectedItem(), 
            (String) remainingWallsField.getSelectedItem(), 
            (String) numOfRowsField.getSelectedItem(), 
            (String) numOfColsField.getSelectedItem(), (String) numOfPitsField.getSelectedItem(), 
            (String) numOfBatsField.getSelectedItem(), this, 
            (String) player1StartXField.getSelectedItem(), 
            (String) player1StartYField.getSelectedItem(), 
            (String) player2StartXField.getSelectedItem(), 
            (String) player2StartYField.getSelectedItem(), 
            (String) numOfArrowsField.getSelectedItem(), 
            (String) numOfPlayersField.getSelectedItem());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    
    moveNorth.addActionListener(l -> {
      try {
        f.moveNorth();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    
    moveSouth.addActionListener(l -> {
      try {
        f.moveSouth();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    
    moveEast.addActionListener(l -> {
      try {
        f.moveEast();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    
    moveWest.addActionListener(l -> {
      try {
        f.moveWest();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    
    shootButton.addActionListener(l -> {
      try {
        f.shootArrow((String) shootDirectionField.getSelectedItem(), 
            (String) shootDistanceField.getSelectedItem());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    
    
    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 't') {
          System.out.println("");
        }
      }

      
      @Override
      public void keyPressed(KeyEvent e) {
          System.out.println("");
      } 

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          try {
            f.moveNorth();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          try {
            f.moveSouth();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          try {
            f.moveEast();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          try {
            f.moveWest();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      } 
    });
    
  }
  
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
  
  @Override
  public void toggleColor(int idx) {
    if (idx == 0) {
      this.displayPlayer1.setForeground(Color.BLACK);
      this.displayPlayer0.setForeground(Color.GREEN);
    } else {
      this.displayPlayer0.setForeground(Color.BLACK);
      this.displayPlayer1.setForeground(Color.GREEN);
    }
    
    
  }

}
