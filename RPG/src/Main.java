import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{
	private static final int WIDTH =1800;
	private static final int HEIGHT=1600;
	
	public Main () {
		super("Forest Quest");
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		Game play = new Game();
		((Component) play).setFocusable(true);
		
		Color darkGreen = new Color(0,60,0);
		
		
		setBackground(darkGreen);
		
		
		getContentPane().add(play);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}
