import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RPGGUI extends JFrame implements KeyListener, ActionListener {
	private JTextArea textbox=new JTextArea();
	private int buttonCount=7;
	private int textboxMaxLines=6;
	private JButton buttons[]= new JButton[buttonCount];
	private JLabel maplabel=new JLabel("<html>RPG Game of AWESOMENESS!!<br>Made by: T04-03<br>Press Space Bar to continue</html>",JLabel.CENTER);
	private boolean[] keyDownArray=new boolean[256];
	
    public RPGGUI() {
		/*keyCodesDictionary[32]="Enter";
		keyCodesDictionary[38]="North";
		keyCodesDictionary[87]="North";
		keyCodesDictionary[39]="East";
		keyCodesDictionary[68]="East";
		keyCodesDictionary[40]="South";
		keyCodesDictionary[83]="South";
		keyCodesDictionary[37]="West";
		keyCodesDictionary[65]="West";*/
		
		//add key listener, make frame focusable so it works properly
		this.addKeyListener(this);
		this.setFocusable(true);
	
		//make a new jpanel on our form
		JPanel p = new JPanel();
		JPanel p2 = new JPanel(new BorderLayout());
		
		maplabel.setFont(maplabel.getFont().deriveFont(42f));
		add(maplabel);
		
		//set up the combat log textbox
		textbox.setText(">Combat Log");
		for(int i=0;i<textboxMaxLines-1;i++){
			textbox.append(" \n");
		}
		textbox.setEditable(false);
		textbox.setFocusable(false);
		p2.add(textbox,BorderLayout.NORTH);
		
		//set up the buttons
		for(int i=0;i<buttonCount;i++){
			buttons[i]=new JButton("Button"+(i+1));
			buttons[i].setActionCommand("Button"+(i+1));
			buttons[i].addActionListener(this);
			buttons[i].setFocusable(false);
			p.add(buttons[i]);
		}
		
		p2.add(p,BorderLayout.SOUTH);
		add(p2,BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(800, 600));
		
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
	
	public void actionPerformed(ActionEvent event) {
		//test code, buttons need to do stuff
		printLine(">"+event.getActionCommand()+" pressed.");
    }
}
