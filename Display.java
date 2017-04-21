/**
 Class for displaying 3x3 cube using JFrame.  Contains methods to initialize
 JFrame and graphically display the cube to the user.

 @author Nathen St. Germain
 @version Apr 20 2017
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display implements MouseListener{
  /*Main frame*/
  private JFrame frame;

  /*Panels for displaying cube*/
  private JPanel panel_cube;
  private JPanel panel_top, panel_bottom, panel_front, panel_back, panel_left, panel_right;
  private JPanel panel_fill0, panel_fill1, panel_fill2, panel_fill3, panel_fill4, panel_fill5;
  private JPanel panel_buttons;
  private JPanel[][] panels;

  /*Dimensions for user screen*/
  private double width;
  private double height;

  /*Creating mouse listener*/
  private MouseListener listener;

  /**
   Constructor for Display class.

   @param title Title for JFrame
   */
  public Display (String title) {
    this.initFrame(title);
    this.initPanel();
    this.addPanel();
  }

  /**
   Graphically display a 2-D cube, display each side of the cube in a JFrame.

   @param cube A 3x3 cube object
   */
  public void displayCube (Cube cube) {
    this.panels = cube.getSides();

    for (int i = 0; i < 9; i++)
      this.panel_top.add(this.panels[0][i]);
    for (int i = 0; i < 9; i++)
      this.panel_left.add(this.panels[1][i]);
    for (int i = 0; i < 9; i++)
      this.panel_front.add(this.panels[2][i]);
    for (int i = 0; i < 9; i++)
      this.panel_right.add(this.panels[3][i]);
    for (int i = 0; i < 9; i++)
      this.panel_bottom.add(this.panels[4][i]);
    for (int i = 0; i < 9; i++)
      this.panel_back.add(this.panels[5][i]);

    this.addListenerPanel();
  }

  /**
   Initialize JFrame settings for use with displaying cube after.

   @param title Title to be displayed in the top bar of the JFrame
   */
  private void initFrame (String title) {
    this.frame = new JFrame(title);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setBackground(Color.BLACK);
  }

  /**
   Initialize JPanel settings.  Set the layout of panel holding the cube.
   */
  private void initPanel () {
    this.panel_cube = new JPanel();

    this.panel_top = new JPanel();
    this.panel_front = new JPanel();
    this.panel_bottom = new JPanel();
    this.panel_back = new JPanel();
    this.panel_left = new JPanel();
    this.panel_right = new JPanel();

    this.panel_fill0 = new JPanel();
    this.panel_fill1 = new JPanel();
    this.panel_fill2 = new JPanel();
    this.panel_fill3 = new JPanel();
    this.panel_fill4 = new JPanel();
    this.panel_fill5 = new JPanel();

    this.panel_buttons = new JPanel();

    this.panel_top.setLayout(new GridLayout(3,3));
    this.panel_front.setLayout(new GridLayout(3,3));
    this.panel_bottom.setLayout(new GridLayout(3,3));
    this.panel_back.setLayout(new GridLayout(3,3));
    this.panel_left.setLayout(new GridLayout(3,3));
    this.panel_right.setLayout(new GridLayout(3,3));

    this.panel_cube.setLayout(new GridLayout(4, 3));
  }

  /**
   Add panels to cube panel which will be added to the main frame.
   */
  private void addPanel () {
    Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();

    /*Row one*/
    this.panel_cube.add(this.panel_fill0);
    this.panel_cube.add(this.panel_top);
    this.panel_cube.add(this.panel_fill1);

    /*Row two*/
    this.panel_cube.add(this.panel_left);
    this.panel_cube.add(this.panel_front);
    this.panel_cube.add(this.panel_right);

    /*Row three*/
    this.panel_cube.add(this.panel_fill2);
    this.panel_cube.add(this.panel_bottom);
    this.panel_cube.add(this.panel_fill3);

    /*Row four*/
    this.panel_cube.add(this.panel_fill4);
    this.panel_cube.add(this.panel_back);
    this.panel_cube.add(this.panel_fill5);

    /*Add cube panel to frame*/
    this.frame.add(this.panel_cube);
    this.frame.add(this.panel_buttons);
    this.panel_buttons.setBackground(Color.RED);

    this.width = scr.getWidth();
    this.height = scr.getHeight();

    this.panel_cube.setSize((int)(height/2.33), (int)((height/2.33) * 1.3333));
    this.panel_cube.setLocation(0, 0);

    this.frame.setSize((int)(height/2.33), (int)((height/2.33) * 1.3333) + 90);
    this.frame.setLocationRelativeTo(null);
    this.frame.setVisible(true);
  }

  /**
   Add mouse listener to JPanels.
   */
  private void addListenerPanel () {
    String name;

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 9; j++) {
        /*Code for identifying with mouse listener*/
        name = Integer.toString(i) + Integer.toString(j);

        this.panels[i][j].addMouseListener(this);
        this.panels[i][j].setName(name);
      }
    }
  }

  public void mouseClicked(MouseEvent event) {
    this.changeSquare(((JPanel)event.getSource()).getName());
  }

  public void mouseExited(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}

  /**
   Call for the colour to be changed of the clicked square.

   @param key String to be parsed identifying clicked panel
   */
  private void changeSquare (String key) {
    int side;
    int square;

    side = Character.getNumericValue(key.charAt(0));
    square = Character.getNumericValue(key.charAt(1));

    this.panels[side][square].updateColour();
  }
}
