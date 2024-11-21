import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	private BufferedImage back;
	private int key, x, y;
	private ArrayList<Characters> charList;
	private ArrayList<Ranged> rangedWeap;
	private String screen;
	private Characters player;
	private ImageIcon startBg;
	private ImageIcon gameBg;
	private ImageIcon fireText;
	private ImageIcon selectText;
	private ImageIcon chooseBg;
	
	private String welcome;
	private String welcome2;
	private boolean isVisible;
	private double time;
	private int i;
	private Queue<Enemy> enemies;
	private File saveFile;

	public Game() {
 
		new Thread(this).start();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key = -1;
		x = 0;
		y = 0;
		saveFile= new File("saved_file2.0.txt");
		rangedWeap = new ArrayList<Ranged>();
		screen = "start";
	    isVisible = true;
		startBg = new ImageIcon("Start.png");
		gameBg = new ImageIcon("Game.png");
		fireText = new ImageIcon("Fire.gif");
		selectText = new ImageIcon("selectC.gif");
		chooseBg = new ImageIcon("Forest.png");
		welcome = "Welcome to Forest Quest";
		welcome2 = "Press Space to Continue";
		time = System.currentTimeMillis();

		// create the enemy position
		enemies = setEs();

		// debug
		charList = setCharList();
		for (Characters c : charList) {
			System.out.println(c);
		}
		
	}

	// create enemy positions
	public Queue<Enemy> setEs() {
		Queue<Enemy> temp = new LinkedList<>();
	    temp.add(new Thorn(600, 550));
		temp.add(new Thorn(800, 550));
		temp.add(new Thorn(1000, 550));
		temp.add(new Thorn(1200, 550));
		return temp;
	}
	public void createFile(){
		try {
			if(saveFile.createNewFile()){
				System.out.println("Succesfully created file!");

			}
			else{
				System.out.println(" File already exists!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
	public void readFile(){
		Scanner sc;
		try {
			sc = new Scanner(saveFile); 
				while(sc.hasNext()){
				System.out.println(sc.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void writeToFile(){
	 FileWriter myWriter;
	try {
		myWriter = new FileWriter(saveFile);
	 //write whatever you want to save
		if(enemies.isEmpty()){
		   myWriter.write("win");
   
		}
		else{
		   myWriter.write("You have" +enemies.size()+" enemies left");
	   
	   }
	   myWriter.close();
	   System.out.println("Successfully wrote to file");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	

}
	


	// create characters
	public ArrayList<Characters> setCharList() {
		ArrayList<Characters> temp = new ArrayList<>();
		temp.add(new Ivy(150, 350, 300, 200, 2, 2));
		temp.add(new Willow(600, 350, 300, 200, 2, 2));
		temp.add(new Oakley(1050, 345, 300, 200, 2,2 ));
		temp.add(new River(1500, 350, 300, 200, 2, 2));
		return temp;
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}

	}

	public void paint(Graphics g) {
		Graphics2D twoDgraph = (Graphics2D) g;
		if (back == null)
			back = (BufferedImage) ((createImage(getWidth(), getHeight())));

		Graphics g2d = back.createGraphics();
		g2d.clearRect(0, 0, getSize().width, getSize().height);
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 90));
		g2d.setColor(Color.white);
		// draw the screen to kick off procesing
		drawScreen(g2d);
		welcome.substring(0, i);
		twoDgraph.drawImage(back, null, 0, 0);

	}

	// start screen
	public void drawStartScreen(Graphics g2d) {
		g2d.setColor(Color.black);
		g2d.drawImage(startBg.getImage(), 0, 0, getWidth(), getHeight(), this);
		g2d.drawString(welcome.substring(0, i), 550, 400);
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 60));
	   g2d.drawString(welcome2.substring(0, i), 650, 460);
		if (i < welcome.length()) {
			if (System.currentTimeMillis() - time > 100) {
				time = System.currentTimeMillis();
				i++;

			}
		}
		if (i < welcome2.length()) {
			if (System.currentTimeMillis() - time > 100) {
				time = System.currentTimeMillis();
				i++;

			}
		}

	}

	public void drawChooseScreen(Graphics g2d) {
		g2d.drawImage(chooseBg.getImage(), 0, 0, getWidth(), getHeight(), this);
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 50));
		g2d.setColor(Color.BLACK);
		//g2d.drawImage(selectText.getImage(), 500,y,1000, 1000,this);
		g2d.drawString("Select your character's number to begin", 550, 95);
		g2d.setColor(Color.white);
		int namex;

		namex = 150;
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 40));
		for (Characters c : charList) {
			c.drawChar(g2d);
			g2d.drawString(c.getName(), namex, 650);
			namex = namex += 450;
		}
	}

	// Method to draw game elements
	public void drawGameScreen(Graphics g2d) {

		// draw the background character
		     player.move();
		       // player.setX(100);
			//	player.setY(600);
				player.setWidth(150);
				player.setHeight(150);
		g2d.drawImage(gameBg.getImage(), 0, 0, getWidth(), getHeight(), this);
		if (isVisible) {
			g2d.drawImage(fireText.getImage(), 650, 0, 500, 180, this);
			}
			
		
		if (player != null) {
			player.drawChar(g2d);
			if (player.getWeapon() != null) {
				player.getWeapon().drawWeap(g2d);
			}
		}

		// Draw projectiles
		for (Ranged projectile : rangedWeap) {
			projectile.move(10);
			projectile.drawWeap(g2d);
		}

		// Draw only the current enemy
		Enemy currentEnemy = enemies.element();
		if (currentEnemy != null ) {
			currentEnemy.drawChar(g2d);

		}

		// Check for collisions and clean up
		handleCollisionsAndCleanup();
	}
	
	
	private boolean checkCollision(Ranged projectile, Enemy enemy) {
		Rectangle projectileBox = new Rectangle(
				projectile.getX(),
				projectile.getY(),
				projectile.getWidth(),
				projectile.getHeight());

		Rectangle enemyBox = new Rectangle(
				enemy.getX(),
				enemy.getY(),
				enemy.getWidth(),
				enemy.getHeight());

		return projectileBox.intersects(enemyBox);
	}

	// Method to handle collisions and cleanup
	private void handleCollisionsAndCleanup() {
		ArrayList<Ranged> projectilesToRemove = new ArrayList<>();
		boolean enemyDefeated = false;

		for (Ranged projectile : rangedWeap) {
			Enemy currentEnemy = enemies.peek();
			if (currentEnemy != null && checkCollision(projectile, currentEnemy)) {
				projectilesToRemove.add(projectile);
				enemies.poll(); // Remove hit enemy
				enemyDefeated = true;
			}
			if (projectile.getX() > getWidth()) {
				projectilesToRemove.add(projectile); // Remove if off-screen
			}
		}

		rangedWeap.removeAll(projectilesToRemove); // Clean up projectiles
		if (enemyDefeated) {
			repaint(); // Trigger repaint if an enemy was defeated
		}
	}

	private void drawScreen(Graphics g2d) {

		// TODO Auto-generated method stub

		switch (screen) {

			case "start":
				drawStartScreen(g2d);
				break;
			case "choose":
				drawChooseScreen(g2d);
				break;
			case "selection":
				drawSelectScreen(g2d);
				break;
			case "gameplay":
				drawGameScreen(g2d);
				break;
			default:
				System.out.println("Unknown screen: " + screen);
				break;

		}

	}

	public void drawSelectScreen(Graphics g2d) {

		player.drawChar(g2d);
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 35));
		g2d.drawString("You picked " + player.toString(), 360, 95);
		g2d.drawString(" Press 'B' to go back ", 700, 145);
		g2d.drawString(" Press 'Enter' to select", 700, 205);
		// add code that states \characters
	}

	// DO NOT DELETE
	@Override
	public void keyTyped(KeyEvent e) {

		// TODO Auto-generated method stub

	}
	
	// Method to fire the weapon
	public void fireWeapon() {
		if (player != null && !enemies.isEmpty()) {

			// throw the weapon
			Ranged projectile = player.throwWeapon();

			if (projectile != null) {
				rangedWeap.add(projectile);
				System.out.println("Projectile fired! Current count: " + rangedWeap.size());
				repaint(); // Trigger repaint to show the new projectile
			}

			// Make the enemy fire back
			Enemy currentEnemy = enemies.peek();
			if (currentEnemy != null && currentEnemy.checkProjectiles()) {
				currentEnemy.fireBack();
			}
		}
	}

	// DO NOT DELETE
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();

		System.out.println(key);

		if (key == 32) {
			screen = "choose";
		}
		if(key==39) {
		player.setDx(2);
	}
	//Left  key
	if(key==37) {
		player.setDx(-2);
	}
	//Down Key
	if(key==40) {
		player.setDy(2);
	}
	//Up Key
	if(key==38){
       player.setDy(-2);
	}
		if (key == 49) {
			screen = "selection";
			player = charList.get(0);
		}
		if (key == 50) {
			screen = "selection";
			player = charList.get(1);
		}
		if (key == 51) {
			screen = "selection";
			player = charList.get(2);
		}
		if (key == 52) {
			screen = "selection";
			player = charList.get(3);
		}
		if (key == 53) {
			screen = "selection";
			player = charList.get(4);
		}
		if (key == 70) { // F key
			fireWeapon();
			isVisible = false; 
				
				}
		
		

		if (key == 10) {
			if (player != null) {
				
				screen = "gameplay";

				System.out.println("Switching to Gameplay Screen");
			} else {
				System.out.println("No player selected. Cannot switch to gameplay.");
			}
			repaint();
		}
		if (key == 66) {
			screen = "choose";

		}
	}

	// DO NOT DELETE
	@Override
	public void keyReleased(KeyEvent e) {
		if(key==39) {
			player.setDx(0);
		}
		//Left  key
		if(key==37) {
			player.setDx(0);
		}
		//Down Key
		if(key==40) {
			player.setDy(0);
		}
		//Up Key
		if(key==38){
		   player.setDy(0);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		// TODO Auto-generated method stub

		System.out.println("you clicked at" + arg0.getY());

		x = arg0.getX();

		y = arg0.getY();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
