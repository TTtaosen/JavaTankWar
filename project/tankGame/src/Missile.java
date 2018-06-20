import java.awt.*;
import java.util.List;

public class Missile {

    public static final int Width = 14;
    public static final int Height = 20;

    // 子弹移动速度
    public static final int XSPEED = 15;
    public static final int YSPEED = 15;

    private boolean live;  // 判断子弹的存活
    private boolean isPlayer;  // 判断子弹和阵营
    //子弹的方向
    private int direction;
    // 子弹位置
    private int x;
    private int y;

    private TankGame tankGame;  // 主类权限

    // 设置位静态变量加快生成速度
    public static Image missile_Up = Toolkit.getDefaultToolkit().createImage("pictures/missile_Up.png");
    public static Image missile_Down = Toolkit.getDefaultToolkit().createImage("pictures/missile_Down.png");
    public static Image missile_Left = Toolkit.getDefaultToolkit().createImage("pictures/missile_Left.png");
    public static Image missile_Right = Toolkit.getDefaultToolkit().createImage("pictures/missile_Right.png");

    Missile(int direction, int x, int y, boolean isPlayer, TankGame tankGame){
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.isPlayer = isPlayer;
        this.tankGame = tankGame;
        this.live = true;
    }

    // 获取子弹是否存活
    public boolean isLive() {
        return this.live;
    }
    // 设置子弹的存活
    public void setLive(boolean live) {
        this.live = live;
    }

    // 在主类中作图
    public void draw(Graphics g){

        if(!live){
            tankGame.missileList.remove(this);
            return;
        }

        // 画子弹
        switch (this.direction){
            case Tank.Up:
                g.drawImage(Missile.missile_Up, x, y, tankGame);
                break;
            case Tank.Down:
                g.drawImage(Missile.missile_Down, x, y, tankGame);
                break;
            case Tank.Left:
                g.drawImage(Missile.missile_Left, x, y, tankGame);
                break;
            case Tank.Right:
                g.drawImage(Missile.missile_Right, x, y, tankGame);
                break;
        }

        move();  //移动

    }

    public void move() {
    /*判断移动方向移动坦克位置*/
        switch (direction) {
            case Tank.Left:
                x -= XSPEED;
                break;

            case Tank.Up:
                y -= YSPEED;
                break;

            case Tank.Right:
                x += XSPEED;
                break;

            case Tank.Down:
                y += YSPEED;
                break;
        }
        // 出界则子弹消失,在子弹集合中删去
        if (x < 0 || y < 0 || x > TankGame.GAME_WIDTH || y > TankGame.GAME_HEIGTH) {
            live = false;
        }


    }

    // 获取子弹的范围
    public Rectangle getRect(){
        return new Rectangle(x, y, Width, Height);
    }

    // 子弹与坦克碰撞过程
    public boolean hitTank(Tank tank){
        // 如果子弹与坦克在同一范围则子弹和坦克同时死亡；且子弹只能杀死对方坦克
        // intersect函数为Rectangle类内部方法，作用是判断两个矩形是否相交
        if( this.live && this.getRect().intersects(tank.getRect()) && tank.isLive() && this.isPlayer != tank.isPlayer() ){

            if(!tank.isPlayer()){
                if(tank.getTankType() == 0){
                    TankGame.countHitTank1 ++;
                }
                else if(tank.getTankType() == 1){
                    TankGame.countHitTank2 ++;
                }
                else if(tank.getTankType() == 2){
                    TankGame.countHitTank3 ++;
                }
                else{
                    TankGame.countHitTank4 ++;
                }
            }

            tank.setLive(false);  // 坦克死亡
            this.live = false;  // 子弹死亡

            return true; // 子弹击中坦克

        }

        return false; // 子弹没有击中坦克

    }

    // 循环判断该子弹是否击中任一坦克
    public boolean isHitTank( List<Tank> tanks ){
        for (int i = 0; i < tanks.size(); i++){

            if( hitTank(tanks.get(i)) ){

                return true;
            }

        }

        return false;

    }


    // 子弹与墙的碰撞过程
    public void hitWall(Wall w){
        // 如果子弹与墙的范围重合子弹死亡
        if( this.live && this.getRect().intersects(w.getRect()) ){
            this.live=false;  // 子弹死亡

            if(this.direction == Tank.Up || this.direction == Tank.Down){
                w.decreaseHeightWallLevel(this.direction);
            }
            else{
                w.decreseWidthWallLevel(this.direction);
            }
        }
    }

    public void hitBird(Bird bird){
        if( this.live && this.getRect().intersects( bird.getRect()) ){
            bird.setLive(false);
        }
    }

}
