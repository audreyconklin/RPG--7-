import javax.swing.ImageIcon;

public class Slingshot extends Ranged {
    public Slingshot(){
        super();
    }
    public Slingshot(int x, int y){
        super(x,y, 100,100, 100,2,159, new ImageIcon("Slingshot.png"));
         
    }
    public String toString(){
        return "Slingshot";
    }

    
}
