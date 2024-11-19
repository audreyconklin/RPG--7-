import java.util.ArrayList;

import javax.swing.ImageIcon;
public class Willow extends Characters{
    public Willow(){
     super();
}
public Willow(int x, int y, int w, int h, int dx, int dy){
    super(x,y, w, h, dx, dy , 1000, 5, 10, 1, new ImageIcon("Willow.png"),new Crossbow(x+200,y+140));
    //super.getWeapon().setX(super.getX()+super.getWidth());
    //super.setWeapons(setList());
    

}
public String toString(){
    return "Willow! " + "    Health: "+super.getHealth()+"    Speed: " +super.getSpeed()+"    Damage: "+super.getDamage()+"   Stamina: "+super.getStam()+"      Weapon: "+ super.getWeapon();
}
@Override   
public String getName(){
   return "2: Willow";
       }

public ArrayList <Weapons> setList(){
    ArrayList <Weapons> temp = new ArrayList <Weapons> ();
temp.add(new Crossbow());
return temp;
}
}