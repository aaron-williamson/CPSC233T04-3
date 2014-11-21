import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RPGGUI extends JFrame implements KeyListener, ActionListener {
	private static final int MAP_PANEL_SIZE_OFFSET=180;
	private static final int DEFAULT_WIDTH=800;
	private static final int DEFAULT_HEIGHT=600;
	private JTextArea textbox=new JTextArea();
	private String[] buttonText={"Attack","Defend","Start Game","Pause Game","Cheat"};
	private int textboxMaxLines=6;
	private JButton buttons[]= new JButton[buttonText.length];
	private MapPanel mapPanel;
	private boolean[] keyDownArray=new boolean[256];
	private boolean gamePaused = false;
	
    public RPGGUI() {
    	super(Game.GAME_TITLE);
		
		//add key listener, make frame focusable so it works properly
		this.addKeyListener(this);
		this.setFocusable(true);
	
		//make a new jpanel on our form
		JPanel p = new JPanel();
		JPanel p2 = new JPanel(new BorderLayout());
		
		//add the map panel
		mapPanel=new MapPanel();
		mapPanel.setSize(RPGGUI.DEFAULT_WIDTH,RPGGUI.DEFAULT_HEIGHT-RPGGUI.MAP_PANEL_SIZE_OFFSET);
		add(mapPanel,BorderLayout.SOUTH);
		
		//set up the combat log textbox
		for(int i=0;i<textboxMaxLines-1;i++){
			textbox.append(" \n");
		}
		textbox.setEditable(false);
		textbox.setFocusable(false);
		p2.add(textbox,BorderLayout.NORTH);
		
		//set up the buttons
		for(int i=0;i<buttonText.length;i++){
			buttons[i]=new JButton(buttonText[i]);
			buttons[i].setFocusable(false);
			p.add(buttons[i]);
		}
		
		buttons[0].setActionCommand(Combat.COMBAT_ATTACK_ACTION_CMD);
		buttons[0].addActionListener(Game.getInstance().getCombat());
		buttons[0].setEnabled(false);
		buttons[1].setActionCommand(Combat.COMBAT_DEFEND_ACTION_CMD);
		buttons[1].addActionListener(Game.getInstance().getCombat());
		buttons[1].setEnabled(false);
		buttons[2].setActionCommand("Start");
		buttons[2].addActionListener(this);
		buttons[3].setActionCommand("Pause");
		buttons[3].addActionListener(this);
		buttons[4].setActionCommand(Combat.COMBAT_CHEAT_ACTION_CMD);
		buttons[4].addActionListener(Game.getInstance().getCombat());
		buttons[4].setEnabled(false);
		
		p2.add(p,BorderLayout.SOUTH);
		add(p2,BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(RPGGUI.DEFAULT_WIDTH,RPGGUI.DEFAULT_HEIGHT));
		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * Checks if a specific key is being held down on the keyboard
     * @param key int id of the key
     * @return returns true if the key is down
     */
    public boolean isKeyDown(int key){
    	if(key<0||key>255){
    		return false;
    	}
    	return keyDownArray[key];
    }
    
    /**
     * Prints a line into the text area of the gui
     * @param line
     */
    public void printLine(String line){
    	String[] textboxLines=textbox.getText().split("\n");
		textbox.setText(line);
		for(int i=0;i<textboxMaxLines-1;i++){
			textbox.append("\n"+textboxLines[i]);
		}
    }

	public void keyPressed(KeyEvent e) {
		int keycode=e.getKeyCode();
		keyDownArray[keycode]=true;
	}

	public void keyReleased(KeyEvent e) {
		int keycode=e.getKeyCode();
		keyDownArray[keycode]=false;
	}

	public void keyTyped(KeyEvent e) {
	
	}
	
	/**
	 * Redraws the map panel
	 */
	public void redrawMap(){
		mapPanel.setSize(getSize().width,getSize().height-RPGGUI.MAP_PANEL_SIZE_OFFSET);
		mapPanel.repaint();
	}
	
	public void actionPerformed(ActionEvent event) {
		String command=event.getActionCommand();
		//When you press the button "Start Game" it checks if the command Start was sent, then disables the button, disables title screen, and starts the game timer
		if (command == "Start"){
			printLine("You have started your adventure!");
			buttons[2].setEnabled(false);
			Game.getInstance().getGUI().getMapPanel().setTitleScreenShown(true);
			Game.getInstance().pauseTimer(false);
		}
		if (command == "Pause"){
			if(Game.getInstance().getCombat().isInCombat() == false && Game.getInstance().getGUI().getMapPanel().titleScreenShown()){
				if (gamePaused == false){
				Game.getInstance().pauseTimer(true);
				gamePaused = true;
				redrawMap();
				}
				else{
				Game.getInstance().pauseTimer(false);
				gamePaused = false;
				}
			}
		}
    }
	
	public MapPanel getMapPanel() {
		return mapPanel;
	}
	
	public void enableButton(int a){
		buttons[a].setEnabled(true);
	}
	
	public void disableButton(int a){
		buttons[a].setEnabled(false);
	}

	public boolean isGamePaused(){
		return gamePaused;
	}
}
