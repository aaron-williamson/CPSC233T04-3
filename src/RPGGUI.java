import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RPGGUI extends JFrame implements KeyListener, ActionListener {
	private JTextArea textbox=new JTextArea();
	private int max=7;
	private int textboxMaxLines=6;
	private JButton buttons[]= new JButton[max];
	private JLabel maplabel=new JLabel("Map Canvas",JLabel.CENTER);
	private String[] keyCodesDictionary=new String[255];
	
    public RPGGUI() {
		//set up the keycodes dictionary, this is only for the testing and tuesday demo
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
		maplabel.setText(keyCodesDictionary[e.getKeyCode()]);
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