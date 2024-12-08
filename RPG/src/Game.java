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
import java.util.Random;
import java.io.IOException;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	private int screenWidth;
	private int screenHeight;
	private BufferedImage back;
	private int key, x, y;
	private ArrayList<Characters> charList;
	private ArrayList<Ranged> rangedWeap;
	private String screen;
	private Characters player;
	private ImageIcon startBg;
	private ImageIcon gameBg;
	private ImageIcon fireText;
	private ImageIcon level1;
	private ImageIcon selectText;
	private ImageIcon chooseBg;

	private String welcome;
	private String welcome2;
	private String textfromfile;
	private boolean isVisible;
	private boolean enemydefeated;
	private double time;
	private int i;
	private Queue<Enemy> enemies;
	private File saveFile;

	private int currentLevel = 1;
	private int seconds = 0; // Track seconds
	private int minutes = 0; // Track minutes
	private int milliseconds = 0;
	private Timer timer;

	private Timer enemyFireTimer;
	private Random random;

	public Game() {

		new Thread(this).start();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth = screenSize.width;
		this.screenHeight = screenSize.height;

		key = -1;
		x = 0;
		y = 0;
		saveFile = new File("saved_file2.0.txt");
		rangedWeap = new ArrayList<Ranged>();
		screen = "start";
		isVisible = true;
		enemydefeated = false;
		startBg = new ImageIcon("Start.png");
		gameBg = new ImageIcon("Game.png");
		fireText = new ImageIcon("Fire.gif");
		level1 = new ImageIcon("DefeatLevel1.gif");
		selectText = new ImageIcon("selectC.gif");
		chooseBg = new ImageIcon("Forest.png");
		welcome = "Welcome to Forest Quest";
		welcome2 = "Press Space to Continue";
		time = System.currentTimeMillis();

		// Timer to trigger enemy firing randomly
		random = new Random();
		enemyFireTimer = new Timer(1000, e -> triggerEnemyFire());
		enemyFireTimer.start(); // Start the timer when the game begins

		// create the enemy position
		enemies = setEs(1);

		charList = setCharList();
		// for (Characters c : charList) {
		// System.out.println(c);
		// }

		timer = new Timer(10, e -> updateTimer());

	}

	// create 4 enemy positions; use peek to get the first on in the stack; poll
	// removes it from the stack.
	public Queue<Enemy> setEs(int level) {
		Queue<Enemy> temp = new LinkedList<>();
		switch (level) {
			case 1:
				temp.add(new Thorn(600, 550));
				temp.add(new Thorn(800, 550));
				temp.add(new Thorn(1000, 550));
				temp.add(new Thorn(1200, 550));
				temp.add(new Thorn(1400, 550));
				break;
			case 2:
				temp.add(new Goblin(600, 550));
				temp.add(new Goblin(800, 550));
				temp.add(new Goblin(1000, 550));
				temp.add(new Goblin(1200, 550));
				temp.add(new Goblin(1400, 550));
				break;
			case 3:
				temp.add(new Orc(600, 550));
				temp.add(new Orc(800, 550));
				temp.add(new Orc(1000, 550));
				temp.add(new Orc(1200, 550));
				temp.add(new Orc(1400, 550));
				break;
			default:
				// Default to level 1 enemies if an invalid level is provided
				temp.add(new Thorn(600, 550));
				temp.add(new Thorn(800, 550));
				temp.add(new Thorn(1000, 550));
				temp.add(new Thorn(1200, 550));
				temp.add(new Thorn(1400, 550));
				break;
		}
		return temp;
	}
	

	public void createFile() {
		try {
			if (saveFile.createNewFile()) {
				System.out.println("Succesfully created file!");

			} else {
				System.out.println(" File already exists!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String readFile() {
		StringBuilder fileContent = new StringBuilder();
		Scanner sc;
		try {
			sc = new Scanner(saveFile);
			while (sc.hasNext()) {
				// System.out.println(sc.next());
				fileContent.append(sc.nextLine()); // Append the line to the StringBuilder

			}
			fileContent.append("\n");
			sc.close(); // Close the scanner after reading the file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return fileContent.toString(); // Return the accumulated content as a String

	}

	public void writeToFile() {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(saveFile);
			// // write whatever you want to save
			// if (enemies.isEmpty()) {
			// myWriter.write("win");

			// } else {
			String time = String.format("%02d:%02d", minutes, seconds);
			myWriter.write("You took " + time + " last time to defeat the enemy");

			// }
			myWriter.close();
			System.out.println("Successfully wrote to file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// create characters
	public ArrayList<Characters> setCharList() {
		int y, h, w, speed;

		// character metrics
		h = 300;
		w = 200;
		speed = 4;

		// Positioning at the bottom of the screen
		y = this.screenHeight - h - 120;

		ArrayList<Characters> temp = new ArrayList<>();
		temp.add(new Ivy(150, y, w, h, speed));
		temp.add(new Willow(600, y, w, h, speed));
		temp.add(new Oakley(1050, y, w, h, speed));
		temp.add(new River(1500, y, w, h, speed));
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
		g2d.setColor(Color.black);
		// draw the screen to kick off procesing
		drawScreen(g2d);
		welcome.substring(0, i);
		twoDgraph.drawImage(back, null, 0, 0);

	}

	// start screen
	public void drawStartScreen(Graphics g2d) {

		g2d.setColor(Color.white);
		g2d.drawImage(startBg.getImage(), 0, 0, getWidth(), getHeight(), this);
		g2d.drawString(welcome.substring(0, i), 250, 400);
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 60));
		g2d.drawString(welcome2.substring(0, i), 450, 460);
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

	// Method to change the level
	private void changeLevel() {
		currentLevel++;
		switch (currentLevel) {
			case 1:
				gameBg = new ImageIcon("Game.png");
				enemies = setEs(currentLevel);
				break;
			case 2:
				gameBg = new ImageIcon("Game2.png");
				enemies = setEs(currentLevel);
				break;
			case 3:
				gameBg = new ImageIcon("Game3.png");
				enemies = setEs(currentLevel);
				break;
			default:
				currentLevel = 1;
				gameBg = new ImageIcon("Game.png");
				enemies = setEs(currentLevel);
				break;
		}
			enemydefeated=false;
	}
	


	// win screen
	public void drawWinScreen(Graphics g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString("You Win!", 250, 400);
	}

	// loose screen
	public void drawLooseScreen(Graphics g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString("HEALTH IS 0! YOU LOOSE!!!!!", 250, 400);
	}

	// show the choose screen
	public void drawChooseScreen(Graphics g2d) {
		g2d.drawImage(chooseBg.getImage(), 0, 0, getWidth(), getHeight(), this);
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 50));
		g2d.setColor(Color.black);
		// g2d.drawImage(selectText.getImage(), 500,y,1000, 1000,this);
		g2d.drawString("Select your character's number to begin", 300, 95);
		g2d.setColor(Color.white);
		int namex;

		namex = 170;
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 40));
		for (Characters c : charList) {
			c.drawChar(g2d);
			g2d.drawString(c.getName(), c.getX() + 20, c.getY() + c.getHeight() + 30);
			namex = namex += 450;
		}

	}

	// Method to draw game elements
	public void drawGameScreen(Graphics g2d) {
		g2d.drawImage(gameBg.getImage(), 0, 0, getWidth(), getHeight(), this);

		// Display the time as MM:SS
		g2d.setFont(new Font("Times new Roman", Font.BOLD, 30));
		g2d.setColor(Color.white);
		// Format the time as mm:ss:SSS (minutes:seconds:milliseconds)
		String time = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
		g2d.drawString(time, 10, 30);

		// display the players health
		// String phealth = Integer.toString(player.getHealth());
		String phealth = "Health: " + player.getHealth();

		g2d.drawString(phealth, 10, 60);

		if (enemydefeated) {
			if(currentLevel==3 ) {
				screen = "win";
			}
			g2d.drawImage(level1.getImage(), 650, 0, 1000, 200, this);
		}
		// when firing turn off text
		if (isVisible) {

			g2d.drawImage(fireText.getImage(), 650, 0, 500, 180, this);

			String fileContents = readFile(); // Read the contents of the file
			if (fileContents != null) {

				g2d.setFont(new Font("Times new Roman", Font.BOLD, 40));
				g2d.setColor(Color.white);

				// You can now use the file contents, for example, to draw it on the screen
				g2d.drawString(fileContents, 450, 200); // Example of using the file content in the paint method

			}

		}

		/*********************
		 * The player
		 ********************/
		if (player != null) {

			// check on health
			if (player.getHealth() < 1) {
				screen = "loose";
			}

			// move the player with arrow keys
			boolean shouldChangeBackground = player.move(screenWidth, screenHeight, enemydefeated);
			if (shouldChangeBackground) {
				changeLevel();
			}

			// manage the weapons
			for (Ranged projectile : rangedWeap) {
				projectile.move(10); // move
				projectile.drawWeap(g2d); // draw
			}

			// make the character react to a hit
			player.updateHitEffect();

			// draw player
			player.drawChar(g2d);

		}

		/*********************
		 * The enemy stack
		 ********************/
		if (!enemies.isEmpty()) {
			Enemy currentEnemy = enemies.element();
			// move the projectiles
			currentEnemy.moveProjectiles();
			// draw the weapons
			currentEnemy.drawWeap(g2d);
			//enemy follow
			currentEnemy.updateEnemyPosition(player);
			currentEnemy.reactToPlayerMovement(player);
			// draw the enemy
			currentEnemy.drawChar(g2d);

			// no more enemeis; I am the winner!
		} else {

			timer.stop();
			enemydefeated = true;
			
			// screen = "win";
			// System.out.println("No enemies to draw.");

		}

		// Check for collisions
		handleCollision();

	}

	private void handleCollision() {
		// Create a list to track projectiles to remove
		ArrayList<Ranged> charProjectilesToRemove = new ArrayList<>();
		// Create a list to track enemy projectiles to remove
		ArrayList<Ranged> enemyProjectilesToRemove = new ArrayList<>();

		/*****************************************
		 * checking if character has hit enemy
		 *****************************************/
		for (Ranged projectile : rangedWeap) {

			// Check if there are enemies to collide with
			if (!enemies.isEmpty()) {
				Enemy currentEnemy = enemies.peek(); // Peek at the first enemy

				if (isProjectileCollidingWithCharacter(projectile, currentEnemy)) {
					System.out.println("Enemy hit!");
					charProjectilesToRemove.add(projectile);
					enemies.poll(); // Remove hit enemy
				}
			}

			// Remove projectile if it goes off-screen
			if (projectile.getX() > getWidth()) {
				charProjectilesToRemove.add(projectile);
			}
		}

		// Remove all projectiles that are flagged
		rangedWeap.removeAll(charProjectilesToRemove);

		/*****************************************
		 * checking if enemy has hit character
		 *****************************************/
		if (!enemies.isEmpty()) {
			Enemy currentEnemy = enemies.peek();

			// there must be a player for detecion to work
			if (player != null) {

				// Access the enemyProjectiles
				ArrayList<Ranged> enemyProjectiles = currentEnemy.getEnemyProjectiles();

				for (Ranged enemyProjectile : enemyProjectiles) {

					if (isProjectileCollidingWithCharacter(enemyProjectile, player)) {
						player.onHit();
						player.setHealth(player.getHealth() - 1);
						System.out.println("Player hit by enemy projectile - new health: " + player.getHealth());
						enemyProjectilesToRemove.add(enemyProjectile);
					}

					// Remove projectile if it goes off-screen
					if (enemyProjectile.getX() > getWidth()) {
						enemyProjectilesToRemove.add(enemyProjectile);
					}

				}

			}

			// Remove all projectiles that are flagged
			currentEnemy.removeProjectiles(enemyProjectilesToRemove);
		}

		// Trigger repaint if needed
		repaint();
	}

	public boolean isProjectileCollidingWithCharacter(Ranged projectile, Characters character) {
		// More robust collision detection
		Rectangle projectileBounds = new Rectangle(
				projectile.getX(),
				projectile.getY(),
				projectile.getWidth(),
				projectile.getHeight());

		Rectangle characterBounds = new Rectangle(
				character.getX(),
				character.getY(),
				character.getWidth(),
				character.getHeight());

		boolean isColliding = projectileBounds.intersects(characterBounds);

		return isColliding;
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
			case "loose":
				drawLooseScreen(g2d);
				break;
			case "win":
				drawWinScreen(g2d);
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
				System.out.println("player fired! Current count: " + rangedWeap.size());
				repaint(); // Trigger repaint to show the new projectile
			}

		}
	}

	// Method to trigger enemy firing
	private void triggerEnemyFire() {
		if (screen == "gameplay") {
			if (!enemies.isEmpty()) {
				Enemy currentEnemy = enemies.peek(); // Get the current enemy
				if (currentEnemy != null && random.nextBoolean()) { // 50% chance to fire

					// Ranged enemyProjectile = currentEnemy.fireBack();
					currentEnemy.fireBack();
					System.out.println("Enemy fired!");
					repaint(); // Ensure the firing is reflected visually
					// }

				}
			}
		}

	}

	// Update the timer every 10ms
	private void updateTimer() {
		milliseconds++; // Increment milliseconds

		if (milliseconds >= 100) { // 100 milliseconds = 1 second
			milliseconds = 0; // Reset milliseconds
			seconds++; // Increment seconds
		}

		if (seconds >= 60) { // 60 seconds = 1 minute
			seconds = 0; // Reset seconds
			minutes++; // Increment minutes
		}

		// Repaint the screen to update the displayed time
		repaint();
	}

	private void resetCharPosandSize() {

		player.setX(100);
		player.setY(600);
		player.setWidth(100);
		player.setHeight(100);
	}

	// DO NOT DELETE
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();

		// System.out.println(key);

		if (key == 32) {
			screen = "choose";
		}
		// right
		if (key == 39) {
			player.setDx(player.getSpeed());
		}
		// Left key
		if (key == 37) {
			player.setDx(-player.getSpeed());
		}
		// Down Key
		if (key == 40) {
			player.setDy(player.getSpeed());
		}
		// Up Key
		if (key == 38) {
			player.setDy(-player.getSpeed());
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
		// enter
		if (key == 10) {
			if (player != null) {

				screen = "gameplay";

				System.out.println("Switching to Gameplay Screen");
				resetCharPosandSize();
				timer.start(); // Start the timer

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
		if (key == 39) {
			player.setDx(0);
		}
		// Left key
		if (key == 37) {
			player.setDx(0);
		}
		// Down Key
		if (key == 40) {
			player.setDy(0);
		}
		// Up Key
		if (key == 38) {
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
