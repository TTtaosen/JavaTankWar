import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class MyListener extends WindowAdapter{

    public void windowClosing(WindowEvent e){
        System.exit(0);
    }

}

public class TankGame extends JFrame{

    private Image image;
    private Image GameOverImage;

    public static int countHitTank1 = 0;
    public static int countHitTank2 = 0;
    public static int countHitTank3 = 0;
    public static int countHitTank4 = 0;
    // 坦克1，100分一只
    // 坦克2，200分一只
    // 坦克3，400分一只
    // 坦克4，300分一只
    private int totalScore;

    // 游戏界面 710 * 710（最右边和最下面的5像素去掉）
    public static int GAME_WIDTH = 720;  //总界面宽（加上了基值）
    public static int GAME_HEIGTH = 750;  //总界面高（加上了基值）
    public static int WIDTH_BASE = 5; // 宽基值
    public static int HEIGHT_BASE = 35; // 高基值

    // 全局变量
    // 我方坦克(就一只，所以不需要列表)
    public Tank playerTank = new Tank( 3 * 55 + WIDTH_BASE, 12 * 55 + HEIGHT_BASE, true, this);
    // 敌方坦克集合
    public List<Tank> enemyTankList = new ArrayList<Tank>();
    // 子弹集合
    public List<Missile> missileList = new ArrayList<Missile>();
    // 墙集合
    public List<Wall> wallList = new ArrayList<Wall>();

    public Bird bird = new Bird(6 * 55 + WIDTH_BASE, 12 * 55 + HEIGHT_BASE);


    private void drawBufferedImage(){

        // 判断游戏是否结束
        if( enemyTankList.size() == 0 || !playerTank.isLive() || !bird.isAlive()){

            image = GameOverImage ;

        }
        else{

            // 创建缓冲区对象
            image = createImage(this.getWidth(), this.getHeight());
            // 获取图像画笔
            Graphics g = image.getGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));

        // 以下为绘制缓冲区内容
            bird.draw(g);

            playerTank.draw(g);
            playerTank.hitTanks(enemyTankList);

            for( int j = 0 ; j < wallList.size() ; j++){
                playerTank.hitWall( wallList.get(j) );
            }

            playerTank.hitBird(bird);

            for(int i = 0 ; i < wallList.size() ; i ++){
                wallList.get(i).draw(g);
            }

            // 循环子弹集合,如果列表里有子弹就画出来
            for (int i = 0; i < missileList.size(); i++){

                Missile missile = missileList.get(i);  //获取当前子弹

                // 每个missile对象都有一个isPlayer属性用于判断该子弹属于我方还是敌方

                // 自己子弹打死敌方坦克
                // 因为敌军坦克多，所以要用isHitTank判断
                missile.isHitTank(enemyTankList);
                // 敌人子弹打击自己的坦克
                // 因为我方坦克只有一只，直接判断敌军坦克是否击中自己即可
                missile.hitTank(playerTank);
                for(int j = 0 ; j < wallList.size() ; j++){
                    missile.hitWall(wallList.get(j));
                }
                missile.hitBird(bird);

                missile.draw(g);  //画子弹

            }

            // 画敌方坦克
            for (int i = 0; i < enemyTankList.size(); i++){

                Tank tank = enemyTankList.get(i);
                tank.draw(g);  //画敌方坦克
                tank.hitTanks(enemyTankList);
                for( int j = 0 ; j < wallList.size() ; j++){
                    tank.hitWall( wallList.get(j) );
                }

                tank.hitBird(bird);

            }
        }




    }


    public void createWallMap(){

        // 基地的墙
        // Wall(int x, int y, TankGame tankGame,int whichWall,int theWidthLevel,int theHeightLevel)
        wallList.add( new Wall( 6 * 55 - 20 + WIDTH_BASE, 12 * 55 + HEIGHT_BASE, this, Wall.NotLongWall,2, 5) );
        wallList.add( new Wall( 7 * 55 - 5 + WIDTH_BASE, 12 * 55 + HEIGHT_BASE, this, Wall.NotLongWall,2, 5) );
        wallList.add( new Wall( 6 * 55 + WIDTH_BASE, 12 * 55 - 20 + HEIGHT_BASE, this, Wall.NotLongWall,5, 2) );

        for( int i = 1 ; i <= 4 ; i++){
            if( i == 4){
                wallList.add( new Wall( 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE,this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall( 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE,this, Wall.HeightLongWall) );
            }
        }
        for( int i = 1 ; i <= 4 ; i++){
            if( i == 4){
                wallList.add( new Wall(3 * 55 + WIDTH_BASE , 55 * i + HEIGHT_BASE, this, Wall.NotLongWall) );
            }
            else{
                wallList.add( new Wall(3 * 55 + WIDTH_BASE , 55 * i + HEIGHT_BASE, this, Wall.HeightLongWall) );
            }

        }
        for( int i = 1 ; i <= 3 ; i++){
            if( i == 3 ){
                wallList.add( new Wall(5 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.NotLongWall) );
            }
            else{
                wallList.add( new Wall(5 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.HeightLongWall) );
            }
        }

        // doubleStoneWall
        wallList.add( new Wall(6 * 55 - 5 + WIDTH_BASE, 3 * 55 - 27 + HEIGHT_BASE, this, Wall.DoubleStoneWall) );

        for( int i = 1 ; i <= 3 ; i++){
            if(i == 3){
                wallList.add( new Wall(7 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.NotLongWall) );
            }
            else{
                wallList.add( new Wall(7 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        for( int i = 1 ; i <= 4 ; i++){
            if( i == 4){
                wallList.add( new Wall(9 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.NotLongWall) );
            }
            else{
                wallList.add( new Wall(9 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        for( int i = 1 ; i <= 4 ; i++){
            if( i == 4){
                wallList.add( new Wall(11 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.NotLongWall) );
            }
            else{
                wallList.add( new Wall(11 * 55 + WIDTH_BASE, 55 * i + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        wallList.add( new Wall(5 * 55 + WIDTH_BASE, 5 * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );
        wallList.add( new Wall(7 * 55 + WIDTH_BASE, 5 * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

        // left stone wall
        wallList.add( new Wall(WIDTH_BASE, 6 * 55 + HEIGHT_BASE, this, Wall.StoneWall) );

        wallList.add( new Wall(2 * 55 + WIDTH_BASE, 6 * 55 + HEIGHT_BASE, this, Wall.WidthLongWall) );
        wallList.add( new Wall(3 * 55 + WIDTH_BASE, 6 * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );
        wallList.add( new Wall(9 * 55 + WIDTH_BASE, 6 * 55 + HEIGHT_BASE, this, Wall.WidthLongWall) );
        wallList.add( new Wall(10 * 55 + WIDTH_BASE, 6 * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );
        // right stone wall
        wallList.add( new Wall(12 * 55 + WIDTH_BASE, 6 * 55 + HEIGHT_BASE, this, Wall.StoneWall) );

        for( int i = 8 ; i <= 11 ; i++){
            if( i == 11 ){
                wallList.add( new Wall(55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall(55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        for( int i = 8 ; i <= 11 ; i++){
            if( i == 11){
                wallList.add( new Wall(3 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall(3 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        for( int i = 7 ; i <= 10 ; i++){
            if(i == 10){
                wallList.add( new Wall(5 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall(5 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }

        // doubleWidthLongWall
        wallList.add( new Wall(6 * 55  - 5 + WIDTH_BASE, 8 * 55 - 12 + HEIGHT_BASE, this, Wall.DoubleStoneWall) );

        for( int i = 7 ; i <= 10 ; i++){
            if(i == 10){
                wallList.add( new Wall(7 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall(7 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        for( int i = 8 ; i <= 11 ; i++){
            if( i == 11){
                wallList.add( new Wall(9 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall(9 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }
        for( int i = 8 ; i <= 11 ; i++){
            if(i == 11){
                wallList.add( new Wall(11 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.NotLongWall) );

            }
            else{
                wallList.add( new Wall(11 * 55 + WIDTH_BASE, i * 55 + HEIGHT_BASE, this, Wall.HeightLongWall) );

            }
        }

    }

    private void lauchFrame(){

        this.createWallMap();

        // 初始化敌方坦克
        for (int i = 0; i < 7; i++){
            enemyTankList.add( new Tank(2 * i * 50 + WIDTH_BASE, HEIGHT_BASE, false, this) );
        }

        this.getContentPane().setBackground(Color.black);
        this.setSize(GAME_WIDTH, GAME_HEIGTH);
        this.setVisible(true);
        this.setTitle("Tank Game");
        this.setResizable(false);

        MyListener myListener = new MyListener();
        this.addWindowListener(myListener);

        //设置键盘监听
        this.addKeyListener( new KeyMoniton() );

        //开始运行PaintThread类run
        new Thread(new PaintThread()).start();

    }

    public void paint(Graphics g){

        if( enemyTankList.size() == 0 || !playerTank.isLive() || !bird.isAlive() ){

            try{
                GameOverImage = ImageIO.read(new File("pictures/gameOver.png"));
            }catch (IOException e){
                e.printStackTrace();
            }

            Font numberFont = new Font("score font",Font.PLAIN, 30);

            g.setColor(Color.black);
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGTH);
            g.drawImage(GameOverImage, 0, 0, null);
            g.setColor(Color.white);
            g.setFont(numberFont);
            g.drawString("" + countHitTank1, 300, 100);
            g.drawString("" + countHitTank2, 300, 250);
            g.drawString("" + countHitTank3, 300, 400);
            g.drawString("" + countHitTank4, 300, 550);
            g.drawString("" + countHitTank1 * 100, 50, 100);
            g.drawString("" + countHitTank2 * 200, 50, 250);
            g.drawString("" + countHitTank3 * 400, 50, 400);
            g.drawString("" + countHitTank4 * 300, 50, 550);
            totalScore = countHitTank1 * 100 +
                    countHitTank2 * 200 +
                    countHitTank3 * 400 +
                    countHitTank4 * 300;
            g.drawString("" + totalScore, 300, 650);

            Font overFont = new Font("over font",Font.ITALIC, 40);
            g.setFont(overFont);
            g.setColor(Color.red);
            if (enemyTankList.size() == 0 && playerTank.isLive() && bird.isAlive()){
                g.drawString("Congratu-", 480, 100);
                g.drawString("lations!", 550, 150);
            }else {
                g.drawString("you lose !", 500, 100);
                g.drawString("- - ", 600, 150);
            }


            try{
                Thread.sleep(3500);
                System.exit(0);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }else{

            drawBufferedImage();
            g.drawImage(image, 0, 0, this);

        }

    }

    private class PaintThread implements Runnable{

        public void run() {

            while(true){
                //repaint()在Component中实现，其会自动调用update和paint
                //运行顺序repaint->update->paint
                repaint();
                try{
                    //每隔多少毫秒刷新画面一次
                    Thread.sleep(30);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }

    }

    private class KeyMoniton extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            playerTank.KeyPressed(e);
        }

        public void keyReleased(KeyEvent e){
            super.keyReleased(e);
            playerTank.keyReleased(e);
        }

    }

    public static void main(String args[]){

        TankGame tankGame = new TankGame();
        tankGame.lauchFrame();

    }
}
