import javax.swing.ImageIcon;

public class Thorn extends Enemy{

    public Thorn(){
        super();
    }
    public String toString(){
        return "Thorn"  + super.toString();
    }
 
    public Thorn(int x, int y){
        super(x,y, 200, 200, 2, 1, 1, 5, 10, new ImageIcon("Thorn (2).png"), "SPEAR");
        
    }
   
} 