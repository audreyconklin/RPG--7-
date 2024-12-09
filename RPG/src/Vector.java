import javax.swing.ImageIcon;

public class Vector extends Ranged {
    public Vector (){
        super();
    }
    public Vector(int x, int y){
        super(x,y, 70,70, 100,2,159, new ImageIcon("Vector.png"));
         
    }
    public String toString(){
        return "Vector";
    }

    
}
