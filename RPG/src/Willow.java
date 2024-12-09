import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Willow extends Characters {
    public Willow() {
        super();
    }

    public Willow(int x, int y, int w, int h, int sp) {
        super(x, y, w, h, sp, 4, 10, 1, new ImageIcon("Willow.png"), "SCYTHE");
        // super.getWeapon().setX(super.getX()+super.getWidth());
        // super.setWeapons(setList());

    }

    public String toString() {
        return getName() + super.toString();
    }

    @Override
    public String getName() {
        return "2: Willow";
    }

    public ArrayList<Weapons> setList() {
        ArrayList<Weapons> temp = new ArrayList<Weapons>();
        temp.add(new Scythe());
        return temp;
    }
}