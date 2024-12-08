import javax.swing.ImageIcon;

public class Orc extends Enemy{
    
    public Orc(){
        super();
    }
    public String toString(){
        return "Orc";

    }
    public  Orc (int x, int y){
        super(x,y, 200, 200, 2, 172,2000,5, 40, 
        new ImageIcon("Orc).png"), "SPEAR");
        
    }
   
} 