import javax.swing.ImageIcon;

public class Mace extends Ranged {
    public Mace(){
        super();
    }
    public Mace(int x, int y){
        super(x,y, 100,100, 100,2,159, new ImageIcon("Mace.png"));
         
    }
    public String toString(){
        return "Mace";
    }

    
}
