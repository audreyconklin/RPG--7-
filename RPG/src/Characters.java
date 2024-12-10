
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.*;

public class Characters {

    private int x, y, w, h, speed, health, damage, stam, dx, dy;
    private ImageIcon pic;
    private Ranged weap;
    private String weaponType;
    private int originalX, originalY;
    private boolean isHit = false;
    private int shakeCount = 0;
    private final int MAX_SHAKE_COUNT = 10; // Number of shake iterations
    

    public Characters() {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        speed = 0;
        health = 0;
        damage = 0;
        stam = 0;
        dx = 0;
        dy = 0;
        pic = new ImageIcon();

    }

    public Characters(int xV, int yV, int width, int height, int sp, int hea, int dam, int st, ImageIcon p,
            String weaponType) {
        x = xV;
        y = yV;
        w = width;
        h = height;
        speed = sp;
        health = hea;
        damage = dam;
        stam = st;
        pic = p;
        dx = 0;
        dy = 0;
        this.weaponType = weaponType;
        this.weap = createNewWeapon();
        // weap = we;
        // weaponList = list;

    }

    // Method to create a new weapon after the old one is thrown
    public Ranged createNewWeapon() {

        switch (weaponType.toUpperCase()) {
            case "KNIFE":
                return new Knife(getX() + getWidth(), getY() + getHeight() / 2);
            case "SPEAR":
                return new Spear(getX() + getWidth(), getY() + getHeight() / 2);
            case "MACE":
                return new Mace(getX() + getWidth(), getY() + getHeight() / 2);
            case "SCYTHE":
                return new Scythe(getX() + getWidth(), getY() + getHeight() / 2);
            case "VECTOR":
                return new Vector(getX() + getWidth(), getY() + getHeight() / 2);
            // Add more cases for other weapons
            default:
                throw new IllegalArgumentException("Unknown weapon type: " + weaponType);
        }

    }

    public Weapons getWeapon() {
        return weap;
    }

    public Characters(int xV, int yV, int width, int height, int sp, int hea, int dam, int st, ImageIcon p) {
        x = xV;
        y = yV;
        w = width;
        h = height;
        speed = sp;
        health = hea;
        damage = dam;
        stam = st;
        pic = p;
        dx = 0;
        dy = 0;

    }

    public Ranged throwWeapon() {
        // Create and return a new projectile
        return createNewWeapon();
    }

    public void drawChar(Graphics g2d) {
        // Draw character
        g2d.drawImage(pic.getImage(), x, y, w, h, null);
        if (weap != null) {
            weap.drawWeap(g2d);
        }

        // Ensure the weapon follows the character

        if (weap != null) {
            weap.setX(x + w - 45); // Adjust weapon position based on character's position
            weap.setY(y + (h / 2));
            weap.drawWeap(g2d);
        }
    }

    public String toString() {
        return "   Health: " + getHealth() + "    Speed: " + getSpeed() + "    Damage: " + getDamage() + "    Weapon: "
                + getWeapon();
    }

    public String getName() {
        return "not set";
    }

    // Getters
    public int getX() {
        return x;
    }

    // check for mouse colision
    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getStam() {
        return stam;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public void setHeight(int h) {
        this.h = h;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setStam(int stam) {
        this.stam = stam;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Characters pic(ImageIcon pic) {
        setPic(pic);
        return this;

    }

        public boolean isMovingLeft() {
            return dx < 0;
        }
    
        public boolean isMovingRight() {
            return dx > 0;
        }
    
        public boolean isMovingUp() {
            return dy < 0;
        }
    
        public boolean isMovingDown() {
            return dy > 0;
        }
        public boolean move(int screenWidth, int screenHeight, boolean enemyDefeated) {
            int offset = screenHeight - 60;
            boolean shouldChangeBackground = false;
        
            // Update position using speed
            x += dx * speed;
            y += dy * speed;
        
            // Ensure the character stays within bounds or transitions
            if (x < 0) {
                x = 0; // Left edge
            } else if (x + w > screenWidth) {
                if (enemyDefeated) {
                    x = -w; // Transition to the left side
                    shouldChangeBackground = true; // Indicate that the background should be changed
                } else {
                    x = screenWidth - w; // Right edge, if enemies are not defeated
                }
            }
        
            if (y < 0) {
                y = 0; // Top edge
            } else if (y + h > offset) {
                y = offset - h; // Bottom edge
            }
        
            return shouldChangeBackground;
        }
        
    
   /* public boolean move(int screenWidth, int screenHeight, boolean enemyDefeated) {
        int offset = screenHeight - 60;
        boolean shouldChangeBackground = false;
    
        // Update position
        x += dx;
        y += dy;
    
        // Ensure the character stays within bounds or transitions
        if (x < 0) {
            x = 0; // Left edge
        } else if (x + w > screenWidth) {
            if (enemyDefeated) {
                x = -w; // Transition to the left side
                shouldChangeBackground = true; // Indicate that the background should be changed
            } else {
                x = screenWidth - w; // Right edge, if enemies are not defeated
            }
        }
    
        if (y < 0) {
            y = 0; // Top edge
        } else if (y + h > offset) {
            y = offset - h; // Bottom edge
        }
    
        return shouldChangeBackground;
    }
    
*/
 
    public void onHit() {
        if (!isHit) {
            isHit = true;
            originalX = x;
            originalY = y;
            shakeCount = 0;
        }
    }

    public void updateHitEffect() {
        if (isHit) {
            // Shake effect
            if (shakeCount < MAX_SHAKE_COUNT) {
                int shakeX = (int)(Math.random() * 30 - 15); // Random shake between -5 and 5
                int shakeY = (int)(Math.random() * 30 - 15);
                
                x = originalX + shakeX;
                y = originalY + shakeY;
                
                shakeCount++;
            } else {
                // Reset to original position after shaking
                x = originalX;
                y = originalY;
                isHit = false;
            }
        }
    }

}
