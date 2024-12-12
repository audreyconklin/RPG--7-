import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class Main extends JFrame{
	//private static final int WIDTH =1800;
	//private static final int HEIGHT=1600;
	
	public Main () {
		super("Forest Quest");
		// setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

		// Set fixed width and height 
		int width = 1800; // your desired width 
		int height = 1600; // your desired height 
		setSize(width, height);


		Game play = new Game(width, height);
		((Component) play).setFocusable(true);
		
		Color darkGreen = new Color(0,60,0);
				
		setBackground(darkGreen);
				
		getContentPane().add(play);
		
		setVisible(true);
		addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			play.createFile();
			play.readFile();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				play.writeToFile();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}
