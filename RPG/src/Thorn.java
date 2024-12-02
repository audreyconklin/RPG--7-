import javax.swing.ImageIcon;

public class Thorn extends Enemy{
    public Thorn(){
        super();
    }
    public String toString(){
        return "Thorn";

    }
    public Thorn(int x, int y){
        super(x,y, 200, 200, 2, 172,2000,5, new ImageIcon("Thorn (2).png"), "SPEAR");
        
    }
   
} 