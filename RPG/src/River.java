import java.util.ArrayList;

import javax.swing.ImageIcon;
public class River extends Characters{
    public River(){
     super();
}
public River(int x, int y){
    super(x,y, 300, 200, 1000, 5, 10, 1, new ImageIcon("River.png"),new Slingshot(x+200,y+140));
    //super.getWeapon().setX(super.getX()+super.getWidth());
    //super.setWeapons(setList());
    

}
public String toString(){
    return "River! " + "    Health: "+super.getHealth()+"    Speed: " +super.getSpeed()+"    Damage: "+super.getDamage()+"   Stamina: "+super.getStam()+"      Weapon: "+ super.getWeapon();
}
@Override   
public String getName(){
   return "4: River";
       }
public ArrayList <Weapons> setList(){
    ArrayList <Weapons> temp = new ArrayList <Weapons> ();
temp.add(new Slingshot());
return temp;
}
}