
import javax.swing.ImageIcon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Characters {

    private double accuracy;
    private int reactionTime;
    private boolean isUnderFire;
    // private long lastShotTime;
    private Timer timer;
    private Random random;
    private ArrayList<Ranged> enemyProjectiles;

    public Enemy() {
        super();
    }

    public Enemy(int x, int y, int w, int h, int speed, int hea, int dam, int st, ImageIcon pic, Weapons wea) {
        super(x, y, w, h, speed, hea, dam, st, pic, wea);

        this.isUnderFire = false;
        this.accuracy = 0.8;
        this.reactionTime = 400;
        this.random = new Random();
        // this.lastShotTime = System.currentTimeMillis();
        this.timer = new Timer();
        this.enemyProjectiles = new ArrayList<Ranged>();

    }

    public void fireBack() {
        // Create a new projectile for the enemy
        Ranged enemyProjectile = new Spear(
                getX(), // Adjust starting position based on enemy's x
                getY() + getHeight() / 2 // Center vertically
        // speed // You might want a different speed
        );

        enemyProjectiles.add(enemyProjectile);
        System.out.println("Enemy fired a projectile!");
    }

    public void moveProjectiles() {
        for (Ranged projectile : enemyProjectiles) {
            projectile.move(-10); // Update projectile position
        }
    }

    public void drawChar(Graphics g2d) {
        // Draw enemy character here
        // ...

        super.drawChar(g2d);

        // Draw enemy projectiles
        for (Ranged projectile : enemyProjectiles) {
            projectile.drawWeap(g2d);
        }
    }

    // public Enemy reduceHealth(){
    // // return reduceHealth();
    // }

    // public void takeHit(Graphics g2d) {
    // // hea -= damage;
    // isUnderFire = true;
    // // lastShotTime = System.currentTimeMillis();
    // Ranged projectile = (Ranged) getWeapon();
    // rangedWeapons.add(projectile);
    // //returnFire(g2d);
    // int currentX = projectile.getX();
    // projectile.setX(currentX - 15);
    // System.out.println("enemy projectile is at " + getWeapon().toString());
    // // Draw the projectile
    // projectile.drawWeap(g2d);

    // }

    // private void returnFire(Graphics g2d) {

    // // Add randomness to reaction time (between 80% and 120% of base)
    // int actualReactionTime = (int) (reactionTime * (0.8 + random.nextDouble() *
    // 0.4));

    // // timer.schedule(new TimerTask() {
    // // @Override
    // // public void run() {
    // // if (isUnderFire) {
    // // boolean hitTarget = random.nextDouble() < accuracy;
    // // // fir back all the missiles in the array

    // // for (Ranged projectile : rangedWeapons) {
    // // // Move the projectile
    // // int currentX = projectile.getX();
    // // projectile.setX(currentX - 10);
    // // System.out.println("enemy projectile is at " + getWeapon().toString());
    // // // Draw the projectile
    // // projectile.drawWeap(g2d);

    // // }
    // // // if (hitTarget) {
    // // // System.out.println("Enemy landed a hit with a " +
    // getWeapon().toString());
    // // // // Here you would typically call a method to damage the player
    // // // // player.takeDamage(currentWeapon.damage);
    // // // } else {
    // // // System.out.println("Enemy missed!");
    // // // }

    // // // ammo--;

    // // // Chance for follow-up shots
    // // // if (ammo > 0 && random.nextDouble() < 0.3) {
    // // // returnFire();
    // // // }
    // // }
    // // }
    // // }, 1000);

    // }

}
