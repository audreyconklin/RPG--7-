import javax.swing.ImageIcon;

public class Ranged extends Weapons {

    private boolean isEnemyProjectile = false;

    public Ranged(){
     super();
    }

    public Ranged(int xV, int yV, int width, int height, int d, int dur, int dp, ImageIcon p) {
        super(xV,yV,width, height, d,dur,dp,p);
    }

    public void setEnemyProjectile(boolean isEnemy) {
        this.isEnemyProjectile = isEnemy;
    }

    public boolean isEnemyProjectile() {
        return this.isEnemyProjectile;
    }

}