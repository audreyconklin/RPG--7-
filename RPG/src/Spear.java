import javax.swing.ImageIcon;

public class Spear extends Ranged {
    public Spear(){
        super();
    }
    public Spear(int x, int y){
        super(x,y, 100,100, 100,2,159, new ImageIcon("Spear.png"));
         
    }
    public String toString(){
        return "Spear";
    }

    
}
