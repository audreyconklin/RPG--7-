import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Ivy extends Characters {
    public Ivy() {
        super();
    }

    public Ivy(int x, int y, int w, int h, int dx, int dy) {
        super(x, y, w, h, dx, dy, 1000, 5, 10, 1, new ImageIcon("Ivy.png"), new Knife(x + 200, y + 140));
        // super.getWeapon().setX(super.getX()+super.getWidth());
        // super.setWeapons(setList());

    }

    public String toString() {
        return "Ivy! " + "    Health: " + super.getHealth() + "    Speed: " + super.getSpeed() + "    Damage: "
                + super.getDamage() + "   Stamina: " + super.getStam() + "      Weapon: " + super.getWeapon();
    }

    @Override
    public String getName() {
        return "1: Ivy ";
    }

    public ArrayList<Weapons> setList() {
        ArrayList<Weapons> temp = new ArrayList<Weapons>();
        temp.add(new Knife());
        return temp;
    }
}