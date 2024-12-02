import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Ivy extends Characters {
    public Ivy() {
        super();
    }

    public Ivy(int x, int y, int w, int h, int sp) {
        super(x, y, w, h, sp, 5, 10, 1, new ImageIcon("Ivy.png"), "KNIFE" );

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