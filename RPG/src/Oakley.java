import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Oakley extends Characters {
    public Oakley() {
        super();
    }

    public Oakley(int x, int y, int w, int h, int sp) {
        super(x, y, w, h, sp, 3, 10, 1, new ImageIcon("Oakley.png"), "MACE");

    }

    public String toString() {
        return getName() + super.toString();
    }

    @Override
    public String getName() {
        return "3: Oakley ";
    }

    public ArrayList<Weapons> setList() {
        ArrayList<Weapons> temp = new ArrayList<Weapons>();
        temp.add(new Mace());
        return temp;
    }
}