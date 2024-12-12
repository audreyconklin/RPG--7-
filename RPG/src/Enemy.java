
import javax.swing.ImageIcon;

import java.awt.*;
import java.util.ArrayList;
// import java.util.Random;
/// import java.util.Timer;
//import java.util.TimerTask;

public class Enemy extends Characters {

    private double accuracy;
    private int reactionTime;
    private int weaponSpeed;
    private boolean isUnderFire;
    // private long lastShotTime;
    //private Timer timer;
    //private Random random;
    private ArrayList<Ranged> enemyProjectiles;

    public Enemy() {
        super();
    }

    public Enemy(int x, int y, int w, int h, int speed, int hea, int dam, int st, int wSpeed, ImageIcon pic, String weaponType) {

        super(x, y, w, h, speed, hea, dam, st, pic, weaponType);
        this.weaponSpeed= wSpeed;
        this.isUnderFire = false;
        this.accuracy = 0.8;
        this.reactionTime = 400;
        this.enemyProjectiles = new ArrayList<Ranged>();

    }

    public boolean checkProjectiles(){
        return enemyProjectiles !=null;
    }
   
    public void  fireBack() {
   
        Ranged enemyProjectile = new Spear(
                        getX(), // Adjust starting position based on enemy's x
                        getY() + getHeight() / 2 // Center vertically
                     
        );
        enemyProjectile.setEnemyProjectile(true);
        enemyProjectiles.add(enemyProjectile);  // Track this enemy's projectiles
   
    }

    public boolean isProjectileFromThisEnemy(Ranged projectile) {
        return enemyProjectiles.contains(projectile);
    }
   
     public ArrayList<Ranged> getEnemyProjectiles() {
        return enemyProjectiles;
    }
    
    public void moveProjectiles() {
        for (Ranged projectile : enemyProjectiles) {
            projectile.move(-this.weaponSpeed); // Update projectile position
        }
    }
    public void removeProjectiles(ArrayList<Ranged> enemyProjectilesToRemove ) {
        enemyProjectiles.removeAll(enemyProjectilesToRemove);
    }

    public void drawChar(Graphics g2d) {
        super.drawChar(g2d);
        
    }
    public void drawWeap(Graphics g2d) {
        // Draw enemy projectiles
        for (Ranged projectile : enemyProjectiles) {
            projectile.drawWeap(g2d);
        }
    }
    public void updateEnemyPosition(Characters player) {
        int playerX = player.getX();
        int playerY = player.getY();
    
        // Move enemy towards the player
        if (getX() < playerX) {
            setX(getX()+1);  // Move right
        } else if (getX() > playerX) {
            setX(getX()-1); // Move left
        }
    
        if (getY() < playerX) {
            setY(getY()+1);  // Move up
        } else if (getY() > playerX) {
            setY(getY()-1); // Move down
        }
    
        
    }
    public void reactToPlayerMovement(Characters player) {
        int playerX = player.getX();
        int playerY = player.getY();
    
        // Move enemy in the opposite direction
        if (player.isMovingLeft()) {
            setX(getX()+1); // Move right
        } else if (player.isMovingRight()) {
            setX(getX()-1);; // Move left
        }
    
        if (player.isMovingUp()) {
            setY(getY()+1);  // Move down
        } else if (player.isMovingDown()) {
            setY(getY()-1);  // Move up
        }
        Enemy enemy = new Enemy(); System.out.println(enemy.isEnemy(enemy));
    }
    // Check if enemy
    public boolean isEnemy(Object obj) 
    { return obj instanceof Enemy; } 

}
