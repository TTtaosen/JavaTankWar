import java.awt.*;
import java.util.List;

public class Missile {

    public static final int Width = 14;
    public static final int Height = 20;

    // �ӵ��ƶ��ٶ�
    public static final int XSPEED = 15;
    public static final int YSPEED = 15;

    private boolean live;  // �ж��ӵ��Ĵ��
    private boolean isPlayer;  // �ж��ӵ�����Ӫ
    //�ӵ��ķ���
    private int direction;
    // �ӵ�λ��
    private int x;
    private int y;

    private TankGame tankGame;  // ����Ȩ��

    // ����λ��̬�����ӿ������ٶ�
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

    // ��ȡ�ӵ��Ƿ���
    public boolean isLive() {
        return this.live;
    }
    // �����ӵ��Ĵ��
    public void setLive(boolean live) {
        this.live = live;
    }

    // ����������ͼ
    public void draw(Graphics g){

        if(!live){
            tankGame.missileList.remove(this);
            return;
        }

        // ���ӵ�
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

        move();  //�ƶ�

    }

    public void move() {
    /*�ж��ƶ������ƶ�̹��λ��*/
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
        // �������ӵ���ʧ,���ӵ�������ɾȥ
        if (x < 0 || y < 0 || x > TankGame.GAME_WIDTH || y > TankGame.GAME_HEIGTH) {
            live = false;
        }


    }

    // ��ȡ�ӵ��ķ�Χ
    public Rectangle getRect(){
        return new Rectangle(x, y, Width, Height);
    }

    // �ӵ���̹����ײ����
    public boolean hitTank(Tank tank){
        // ����ӵ���̹����ͬһ��Χ���ӵ���̹��ͬʱ���������ӵ�ֻ��ɱ���Է�̹��
        // intersect����ΪRectangle���ڲ��������������ж����������Ƿ��ཻ
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

            tank.setLive(false);  // ̹������
            this.live = false;  // �ӵ�����

            return true; // �ӵ�����̹��

        }

        return false; // �ӵ�û�л���̹��

    }

    // ѭ���жϸ��ӵ��Ƿ������һ̹��
    public boolean isHitTank( List<Tank> tanks ){
        for (int i = 0; i < tanks.size(); i++){

            if( hitTank(tanks.get(i)) ){

                return true;
            }

        }

        return false;

    }


    // �ӵ���ǽ����ײ����
    public void hitWall(Wall w){
        // ����ӵ���ǽ�ķ�Χ�غ��ӵ�����
        if( this.live && this.getRect().intersects(w.getRect()) ){
            this.live=false;  // �ӵ�����

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
