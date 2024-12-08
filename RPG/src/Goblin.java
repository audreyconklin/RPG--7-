import javax.swing.ImageIcon;

public class Goblin extends Enemy{

    public Goblin(){
        super();
    }
    public String toString(){
        return "Goblin";

    }
    public  Goblin (int x, int y){
        super(x,y, 200, 200, 2, 172,2000,5, 20, new ImageIcon("Goblin).png"), "SPEAR");
        
    }
   
} 