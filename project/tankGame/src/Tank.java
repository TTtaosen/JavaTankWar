import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Random;

public class Tank {

    private int tankType;

    //坦克的四个方向值
    public static final int Up = 0;
    public static final int Down = 1;
    public static final int Left = 2;
    public static final int Right = 3;

    public static final int XSPEED = 5;
    public static final int YSPEED = 5;

    //坦克宽和高是根据图片的大小得出来的
    //坦克宽
    public static final int Width = 50;
    //坦克高
    public static final int Height = 50;

    public int direction;

    // 坦克当前坐标
    private int x, y;
    // 坦克上一步坐标
    private int oldX, oldY;
    private TankGame tankGame;
    private boolean live;
    private boolean isPlayer;

    private Random random = new Random();

    public static Image playerTank_Up = Toolkit.getDefaultToolkit().createImage("pictures/playerTank_Up.png");
    public static Image playerTank_Down = Toolkit.getDefaultToolkit().createImage("pictures/playerTank_Down.png");
    public static Image playerTank_Left = Toolkit.getDefaultToolkit().createImage("pictures/playerTank_Left.png");
    public static Image playerTank_Right = Toolkit.getDefaultToolkit().createImage("pictures/playerTank_Right.png");
    public static Image enemyTank_Up = Toolkit.getDefaultToolkit().createImage("pictures/enemyTank_Up.png");
    public static Image enemyTank_Down = Toolkit.getDefaultToolkit().createImage("pictures/enemyTank_Down.png");
    public static Image enemyTank_Left = Toolkit.getDefaultToolkit().createImage("pictures/enemyTank_Left.png");
    public static Image enemyTank_Right = Toolkit.getDefaultToolkit().createImage("pictures/enemyTank_Right.png");

    public boolean isUpOrDownPress = false;
    public boolean isLeftOrRightPress = false;

    // tankType : default = 0
    Tank(int x, int y, boolean isPlayer, TankGame tankGame){
        this.x = x;
        this.y = y;
        this.oldX = 0;
        this.oldY = 0;
        this.isPlayer = isPlayer;
        this.tankType = 0;

        if(isPlayer){
            this.direction = Up;
        }
        else{
            this.direction = Down;
        }

        this.tankGame = tankGame;
        this.live = true;

    }

    Tank(int x, int y, boolean isPlayer, TankGame tankGame, int tankType){
        this.x = x;
        this.y = y;
        this.oldX = 0;
        this.oldY = 0;
        this.isPlayer = isPlayer;
        this.tankType = tankType;

        if(isPlayer){
            this.direction = Up;
        }
        else{
            this.direction = Down;
        }

        this.tankGame = tankGame;
        this.live = true;

    }

    public void draw(Graphics g){

        if(!live){
            if(!isPlayer){
                tankGame.enemyTankList.remove(this);  //敌方坦克死亡时在集合中删除
            }
        }

        if(isPlayer){

            switch (this.direction){
                case Up:
                    g.drawImage(playerTank_Up, x, y, tankGame);
                    break;
                case Down:
                    g.drawImage(playerTank_Down, x, y, tankGame);
                    break;
                case Left:
                    g.drawImage(playerTank_Left, x, y, tankGame);
                    break;
                case Right:
                    g.drawImage(playerTank_Right, x, y, tankGame);
                    break;
            }

            if(isUpOrDownPress || isLeftOrRightPress){
                // move才是真正让它移动的函数，其它都是作画
                move();
            }

        }
        else{

            switch (this.direction){
                case Up:
                    g.drawImage(enemyTank_Up, x, y, tankGame);
                    break;
                case Down:
                    g.drawImage(enemyTank_Down, x, y, tankGame);
                    break;
                case Left:
                    g.drawImage(enemyTank_Left, x, y, tankGame);
                    break;
                case Right:
                    g.drawImage(enemyTank_Right, x, y, tankGame);
                    break;
            }

            move();

        }

    }

    public void KeyPressed(KeyEvent e){

        int key = e.getKeyCode();

        switch(key){
            case KeyEvent.VK_W:

                this.isUpOrDownPress = true;
                this.direction = Up;
                break;

            case KeyEvent.VK_S:

                this.isUpOrDownPress = true;
                this.direction = Down;
                break;

            case KeyEvent.VK_A:

                this.isLeftOrRightPress = true;
                this.direction = Left;
                break;

            case KeyEvent.VK_D:

                this.isLeftOrRightPress = true;
                this.direction = Right;
                break;

        }


    }


    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        switch (key){
            case KeyEvent.VK_W:
                this.isUpOrDownPress = false;
                break;
            case KeyEvent.VK_S:
                this.isUpOrDownPress = false;
                break;
            case KeyEvent.VK_A:
                this.isLeftOrRightPress = false;
                break;
            case KeyEvent.VK_D:
                this.isLeftOrRightPress = false;
                break;
            case KeyEvent.VK_J:
                if( this.live ){
                    this.missileFire();
                }
                break;

        }


    }

    void move(){
        // 记录上一步的位置
        this.oldX = this.x;
        this.oldY = this.y;

        switch(direction){
            case Up:
                this.y -= YSPEED;
                break;
            case Down:
                this.y += YSPEED;
                break;
            case Left:
                this.x -= XSPEED;
                break;
            case Right:
                this.x += XSPEED;
                break;
        }

        // 判断坦克移动越界情况(游戏边界)
        if(x < 5){
            x = 5;
        }
        if(y < 35){
            y = 35;
        }
        if(x + Width > TankGame.GAME_WIDTH - 5){
            x = TankGame.GAME_WIDTH - Width - 5;
        }
        if(y + Height > TankGame.GAME_HEIGTH - 5){
            y = TankGame.GAME_HEIGTH - Height - 5;
        }

        if(!isPlayer){

            if( random.nextInt(100) > 90){
                this.direction = random.nextInt(4);
            }
            // 随机是否发射炮弹
            if( random.nextInt(100) > 90) {
                this.missileFire();
            }

        }

    }

    // 坦克子弹发射
    public void missileFire(){

        //控制子弹方向为坦克中间
        int x = this.x + Width/2 - Missile.Width/2;
        int y = this.y + Height/2 - Missile.Height/2;
        tankGame.missileList.add( new Missile(direction, x, y, isPlayer, tankGame) ); //创建新的子弹类加入到子弹集合中

    }

    /*回执上一步位置*/
    private void returnToOldPlace(){
        this.x = this.oldX;
        this.y = this.oldY;
    }

    public void hitTanks( List<Tank> tanks ){

        for(int i = 0; i < tanks.size(); i++){
            Tank tank = tanks.get(i);
            // 自己与自己不可相撞
            if( this != tank){
                // 如果相撞返回上一步位置
                if( this.live && tank.isLive() && this.getRect().intersects(tank.getRect())){
                    // 使用该方法改变后来的x, y 坐标到上一次的位置
                    this.returnToOldPlace();
                    tank.returnToOldPlace();
                }
            }
        }


    }

    // 如果撞墙，调用stay方法，返回上一步位置
    public void hitWall(Wall w){
        if( this.live && this.getRect().intersects(w.getRect()) ){
            this.returnToOldPlace();
        }
    }

    public void hitBird(Bird bird){
        if( this.live && this.getRect().intersects(bird.getRect()) ){
            this.returnToOldPlace();
        }
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,Width,Height);
    }

    public boolean isLive(){
        return live;
    }

    public boolean isPlayer(){
        return isPlayer;
    }

    public void setLive(boolean isAlive){
        this.live = isAlive;
    }

    public int getTankType(){
        return this.tankType;
    }


}
