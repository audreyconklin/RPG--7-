import javax.swing.ImageIcon;

public class Scythe extends Ranged {
    public Scythe(){
        super();
    }
    public Scythe(int x, int y){
        super(x,y, 100,100, 100,2,159, new ImageIcon("Scythe.png"));
         
    }
    public String toString(){
        return "Scythe";
    }

    
}
