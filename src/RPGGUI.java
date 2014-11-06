import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RPGGUI extends JFrame implements KeyListener, ActionListener {
	private JTextArea textbox=new JTextArea();
	private int max=7;
	private int textboxMaxLines=6;
	private JButton buttons[]= new JButton[max];
	private JLabel maplabel=new JLabel("<html>RPG Game of AWESOMENESS!!<br>Made by: T04-03<br>Press Space Bar to continue</html>",JLabel.CENTER);
	private String[] keyCodesDictionary=new String[255];
	private boolean exitTitle = false;
	public static MapMakerV1 rpgmap=new MapMakerV1();
	//taken from DebugGraphics
	static String[] Mapgraphics = {"█" , "X"};
	
    public RPGGUI() {
		//set up the keycodes dictionary, this is only for the testing and tuesday demo
		keyCodesDictionary[32]="Enter";
		keyCodesDictionary[38]="North";
		keyCodesDictionary[87]="North";
		keyCodesDictionary[39]="East";
		keyCodesDictionary[68]="East";
		keyCodesDictionary[40]="South";
		keyCodesDictionary[83]="South";
		keyCodesDictionary[37]="West";
		keyCodesDictionary[65]="West";
		
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
		for(int i=0;i<max;i++){
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

	public void keyPressed(KeyEvent e) {
		//Check if title screen should still be displayed
		if (exitTitle == false) {
			int key = e.getKeyCode();
			//if title screen is exited, then the game will continue, print out the map and all.
			if(key == 32){
				exitTitle = true;
				rpgmap.makerowmap();

				//add a player
				Ent_Player player=new Ent_Player();
				player.setPos(1,rpgmap.mapheight-2);
				Ents.addEnt(player);
		
				//add a goal to the bottom right corner
				Ent_Goal goal=new Ent_Goal();
				goal.setPos(rpgmap.mapwidth-2,rpgmap.mapheight-2);
				Ents.addEnt(goal);
		
				//add an enemy
				Ent_Enemy enemy=new Ent_Enemy();
				enemy.setPos(1,1);
				Ents.addEnt(enemy);

				//Taken printall from debug graphics
		String[][] printbuffer=new String[rpgmap.mapheight][rpgmap.mapwidth];
		
		//add the map to the print buffer
		for(int i = 0; i < rpgmap.mapheight; i++){
			for(int j = 0; j < rpgmap.mapwidth; j++){
					printbuffer[i][j]=Mapgraphics[rpgmap.mapGrid[i][j]];
			}
		}	
		
		//add the entities to the print buffer, overwriting the map graphics
		Entity[] entsarray=Ents.getAll();

		for(int i=0;i<entsarray.length;i++){
			Entity ent=entsarray[i];
			printbuffer[ent.getY()][ent.getX()]=ent.debuggraphic();
		}
				
				//print map
				maplabel.setFont(maplabel.getFont().deriveFont(25f));
				maplabel.setText("<html>");
				
				for(int i = 0; i < rpgmap.mapheight; i++){
					for(int j = 0; j < rpgmap.mapwidth; j++){
						maplabel.setText(maplabel.getText() + printbuffer[i][j]);
					}
					maplabel.setText(maplabel.getText() + "<br>");
				}
				maplabel.setText(maplabel.getText() + "</html>");
			}
		}
		//possibly update the map????
		else
			maplabel.setText(keyCodesDictionary[e.getKeyCode()]);
//			Ents.allThink();
		}

	public void keyReleased(KeyEvent e) {
	
	}

	public void keyTyped(KeyEvent e) {
	
	}
	
	public void actionPerformed(ActionEvent event) {
		String[] textboxLines=textbox.getText().split("\n");
		textbox.setText(">"+event.getActionCommand()+" pressed.");
		for(int i=0;i<textboxMaxLines-1;i++){
			textbox.append("\n"+textboxLines[i]);
		}
    }
	
    public static void main(String[] args){
        RPGGUI RPGGUI = new RPGGUI();
    }
}
