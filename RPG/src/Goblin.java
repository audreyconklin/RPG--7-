import javax.swing.ImageIcon;

public class Goblin extends Enemy{

    public Goblin(){
        super();
    }
    public String toString(){
        return "Goblin"  + super.toString();
    }

    public  Goblin (int x, int y){
        super(x,y, 200, 200, 2, 2,2,5, 20, new ImageIcon("Goblin.png"), "SPEAR");
        
    }
   
} 