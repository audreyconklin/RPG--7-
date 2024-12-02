import java.util.ArrayList;

import javax.swing.ImageIcon;
public class Oakley extends Characters{
    public Oakley(){
     super();
}
public Oakley(int x, int y, int w, int h, int sp){
    super(x,y, w, h, sp, 5, 10, 1, new ImageIcon("Oakley.png"),"SPEAR");
    //super.getWeapon().setX(super.getX()+super.getWidth());
    //super.setWeapons(setList());
    

}
public String toString(){
    return "Oakley! " + "    Health: "+super.getHealth()+"    Speed: " +super.getSpeed()+"    Damage: "+super.getDamage()+"   Stamina: "+super.getStam()+"      Weapon: "+ super.getWeapon();
}
@Override   
public String getName(){
   return "3: Oakley";
       }
public ArrayList <Weapons> setList(){
    ArrayList <Weapons> temp = new ArrayList <Weapons> ();
temp.add(new Spear());
return temp;
}
}