
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Characters {

    private int x, y, w, h, speed, health, damage, stam, dx, dy;
    private ImageIcon pic;
    private Weapons weap;
    private ArrayList<Weapons> weaponList;

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

    public Characters(int xV, int yV, int width, int height, int sp, int hea, int dam, int st, ImageIcon p, Weapons we,
            ArrayList<Weapons> list) {
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
        weap = we;
        weaponList = list;

    }

    public Characters(int xV, int yV, int width, int height, int dx, int dy,  int sp, int hea, int dam, int st, ImageIcon p,
            Weapons we) {
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
        weap = we;

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
        return new Spear(getX() + getWidth(), getY() + getHeight() / 2);
    }
    public void drawChar(Graphics g2d){
        // Draw character
     //g2d.drawImage(pic.getImage(), x, y, w, h, null);
     g2d.drawImage(pic.getImage(), x, y, w, h,null);
     if (weap!= null) {
         weap.drawWeap(g2d);
     }
  
     
     
     // Ensure the weapon follows the character
     if (weap != null) {
         weap.setX(x + w); // Adjust weapon position based on character's position
         weap.setY(y);
         weap.drawWeap(g2d);
     }
     }
    

   /*  public void drawChar(Graphics g2d) {
        g2d.drawImage(pic.getImage(), x, y, w, h, null);
        weap.drawWeap(g2d);
    }
*/
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
    public void move() {
		x+=dx;
		if(x+w >800)
			x=800-w;
		
		y+=dy;
		if(y+h>570)
			y=570-h;
		
		
		if(y<0)
			y=0;
		
	
		if(x<0)
			x=0;
		
		
	}
}
