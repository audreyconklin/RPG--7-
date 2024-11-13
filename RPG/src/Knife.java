import javax.swing.ImageIcon;

public class Knife extends Ranged {
    public Knife (){
        super();
    }
    public Knife(int x, int y){
        super(x,y, 100,100, 100,2,159, new ImageIcon("Knife.png"));
         
    }
    public String toString(){
        return "Knife";
    }

    
}
