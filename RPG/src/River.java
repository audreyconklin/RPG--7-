import java.util.ArrayList;

import javax.swing.ImageIcon;

public class River extends Characters {
    public River() {
        super();
    }

    public River(int x, int y, int w, int h, int sp) {
        super(x, y, w, h, sp, 5, 10, 1, new ImageIcon("River.png"), "SLINGSHOT");

    }

    public String toString() {
        return getName() + super.toString();
    }

    @Override
    public String getName() {
        return "4: River";
    }

    public ArrayList<Weapons> setList() {
        ArrayList<Weapons> temp = new ArrayList<Weapons>();
        temp.add(new Slingshot());
        return temp;
    }
}