import javax.swing.ImageIcon;

public class Crossbow extends Ranged {
    public Crossbow (){
        super();
    }
    public Crossbow(int x, int y){
        super(x,y, 70,70, 100,2,159, new ImageIcon("Crossbow.png"));
         
    }
    public String toString(){
        return "Crossbow";
    }

    
}
