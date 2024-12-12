import javax.swing.ImageIcon;

public class Orc extends Enemy{
    
    public Orc(){
        super();
    }
    public String toString(){
        return "Orc"  + super.toString();
    }

    public  Orc (int x, int y){
        super(x,y, 650, 650, 2, 3,5,5, 40,  new ImageIcon("Orc.png"), "SPEAR");
        
    }
   
} 