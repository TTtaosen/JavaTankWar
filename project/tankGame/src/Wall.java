import java.awt.*;

public class Wall {

    public static int NotLongWall = 0;
    public static int WidthLongWall = 1;
    public static int HeightLongWall = 2;
    public static int DouleWidthLongWall = 3;
    public static int StoneWall = 4;
    public static int DoubleStoneWall = 5;

    private int x,y;
    private int w,h;
    private TankGame tankGame;  //主类权限
    private int whichWall;
    // 第一个值代表宽
    // 第二个值代表高
    private int theWallLevel[] = new int[2];

    // wall image
    public static Image wall_5_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_5_5.png");
    public static Image wall_5_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_5_4.png");
    public static Image wall_5_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_5_3.png");
    public static Image wall_5_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_5_2.png");
    public static Image wall_5_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_5_1.png");

    public static Image wall_4_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_4_5.png");
    public static Image wall_4_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_4_4.png");
    public static Image wall_4_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_4_3.png");
    public static Image wall_4_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_4_2.png");
    public static Image wall_4_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_4_1.png");

    public static Image wall_3_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_3_5.png");
    public static Image wall_3_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_3_4.png");
    public static Image wall_3_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_3_3.png");
    public static Image wall_3_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_3_2.png");
    public static Image wall_3_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_3_1.png");

    public static Image wall_2_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_2_5.png");
    public static Image wall_2_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_2_4.png");
    public static Image wall_2_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_2_3.png");
    public static Image wall_2_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_2_2.png");
    public static Image wall_2_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_2_1.png");

    public static Image wall_1_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_1_5.png");
    public static Image wall_1_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_1_4.png");
    public static Image wall_1_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_1_3.png");
    public static Image wall_1_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_1_2.png");
    public static Image wall_1_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_1_1.png");


    // heightlongwall image
    public static Image heightLongWall_5_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_5_5.png");
    public static Image heightLongWall_5_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_5_4.png");
    public static Image heightLongWall_5_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_5_3.png");
    public static Image heightLongWall_5_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_5_2.png");
    public static Image heightLongWall_5_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_5_1.png");

    public static Image heightLongWall_4_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_4_5.png");
    public static Image heightLongWall_4_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_4_4.png");
    public static Image heightLongWall_4_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_4_3.png");
    public static Image heightLongWall_4_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_4_2.png");
    public static Image heightLongWall_4_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_4_1.png");

    public static Image heightLongWall_3_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_3_5.png");
    public static Image heightLongWall_3_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_3_4.png");
    public static Image heightLongWall_3_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_3_3.png");
    public static Image heightLongWall_3_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_3_2.png");
    public static Image heightLongWall_3_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_3_1.png");

    public static Image heightLongWall_2_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_2_5.png");
    public static Image heightLongWall_2_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_2_4.png");
    public static Image heightLongWall_2_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_2_3.png");
    public static Image heightLongWall_2_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_2_2.png");
    public static Image heightLongWall_2_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_2_1.png");

    public static Image heightLongWall_1_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_1_5.png");
    public static Image heightLongWall_1_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_1_4.png");
    public static Image heightLongWall_1_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_1_3.png");
    public static Image heightLongWall_1_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_1_2.png");
    public static Image heightLongWall_1_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_heightLong_1_1.png");


    // widthlongwall image
    public static Image widthLongWall_5_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_5_5.png");
    public static Image widthLongWall_5_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_5_4.png");
    public static Image widthLongWall_5_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_5_3.png");
    public static Image widthLongWall_5_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_5_2.png");
    public static Image widthLongWall_5_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_5_1.png");

    public static Image widthLongWall_4_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_4_5.png");
    public static Image widthLongWall_4_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_4_4.png");
    public static Image widthLongWall_4_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_4_3.png");
    public static Image widthLongWall_4_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_4_2.png");
    public static Image widthLongWall_4_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_4_1.png");

    public static Image widthLongWall_3_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_3_5.png");
    public static Image widthLongWall_3_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_3_4.png");
    public static Image widthLongWall_3_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_3_3.png");
    public static Image widthLongWall_3_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_3_2.png");
    public static Image widthLongWall_3_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_3_1.png");

    public static Image widthLongWall_2_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_2_5.png");
    public static Image widthLongWall_2_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_2_4.png");
    public static Image widthLongWall_2_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_2_3.png");
    public static Image widthLongWall_2_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_2_2.png");
    public static Image widthLongWall_2_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_2_1.png");

    public static Image widthLongWall_1_5 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_1_5.png");
    public static Image widthLongWall_1_4 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_1_4.png");
    public static Image widthLongWall_1_3 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_1_3.png");
    public static Image widthLongWall_1_2 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_1_2.png");
    public static Image widthLongWall_1_1 = Toolkit.getDefaultToolkit().createImage("pictures/wall_widthLong_1_1.png");

    public static Image doubleWidthLongWall = Toolkit.getDefaultToolkit().createImage("pictures/wall_doubleWidthLong.png");
    public static Image stoneWall = Toolkit.getDefaultToolkit().createImage("pictures/stoneWall.png");
    public static Image doubleWidthStoneWall = Toolkit.getDefaultToolkit().createImage("pictures/stoneWall_double.png");

    Wall(int x, int y, TankGame tankGame,int whichWall) {
        this.x = x;
        this.y = y;
        this.tankGame = tankGame;
        this.whichWall = whichWall;

        // 第一个值代表宽
        // 第二个值代表高
        this.theWallLevel[0] = 5;
        this.theWallLevel[1] = 5;

        if( whichWall == NotLongWall ){
            this.w = 50;
            this.h = 50;
        }
        else if( whichWall == WidthLongWall ){
            this.w = 55;
            this.h = 50;
        }
        else if( whichWall == HeightLongWall ){
            this.w = 50;
            this.h = 55;
        }
        else if( whichWall == DouleWidthLongWall){
            this.w = 60;
            this.h = 50;
        }
        else if( whichWall == StoneWall){
            this.w = 50;
            this.h = 50;
        }
        else if( whichWall == DoubleStoneWall){
            this.w = 60;
            this.h = 50;
        }

    }

    // 基地的构造函数
    Wall(int x, int y, TankGame tankGame,int whichWall,int theWidthLevel,int theHeightLevel) {
        this.x = x;
        this.y = y;
        this.tankGame = tankGame;
        this.whichWall = whichWall;

        // 第一个值代表宽
        // 第二个值代表高
        this.theWallLevel[0] = theWidthLevel;
        this.theWallLevel[1] = theHeightLevel;

        if(theWidthLevel == 2){
            this.w = 20;
            this.h = 50;
        }
        else if(theWidthLevel == 5){
            this.w = 50;
            this.h = 20;
        }

    }

    // 获取墙的范围
    public Rectangle getRect(){
        return new Rectangle(this.x, this.y, this.w, this.h);
    }

    // 画墙
    // g.drawImage(wall, this.x, this.y, this.tankGame);
    public void draw(Graphics g) {

        if( whichWall == NotLongWall){

            // theWallLevel[1] == 5
            if(theWallLevel[1] == 5){
                if(theWallLevel[0] == 5){
                    g.drawImage(wall_5_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(wall_4_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(wall_3_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(wall_2_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(wall_1_5, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 4
            else if(theWallLevel[1] == 4){
                if(theWallLevel[0] == 5){
                    g.drawImage(wall_5_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(wall_4_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(wall_3_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(wall_2_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(wall_1_4, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 3
            else if(theWallLevel[1] == 3){
                if(theWallLevel[0] == 5){
                    g.drawImage(wall_5_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(wall_4_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(wall_3_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(wall_2_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(wall_1_3, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 2
            else if(theWallLevel[1] == 2){
                if(theWallLevel[0] == 5){
                    g.drawImage(wall_5_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(wall_4_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(wall_3_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(wall_2_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(wall_1_2, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 1
            else if(theWallLevel[1] == 1){
                if(theWallLevel[0] == 5){
                    g.drawImage(wall_5_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(wall_4_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(wall_3_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(wall_2_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(wall_1_1, this.x, this.y, this.tankGame);
                }
            }

        }

        else if( whichWall == HeightLongWall){

            // theWallLevel[1] == 5
            if(theWallLevel[1] == 5){
                if(theWallLevel[0] == 5){
                    g.drawImage(heightLongWall_5_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(heightLongWall_4_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(heightLongWall_3_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(heightLongWall_2_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(heightLongWall_1_5, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 4
            else if(theWallLevel[1] == 4){
                if(theWallLevel[0] == 5){
                    g.drawImage(heightLongWall_5_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(heightLongWall_4_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(heightLongWall_3_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(heightLongWall_2_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(heightLongWall_1_4, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 3
            else if(theWallLevel[1] == 3){
                if(theWallLevel[0] == 5){
                    g.drawImage(heightLongWall_5_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(heightLongWall_4_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(heightLongWall_3_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(heightLongWall_2_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(heightLongWall_1_3, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 2
            else if(theWallLevel[1] == 2){
                if(theWallLevel[0] == 5){
                    g.drawImage(heightLongWall_5_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(heightLongWall_4_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(heightLongWall_3_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(heightLongWall_2_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(heightLongWall_1_2, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 1
            else if(theWallLevel[1] == 1){
                if(theWallLevel[0] == 5){
                    g.drawImage(heightLongWall_5_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(heightLongWall_4_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(heightLongWall_3_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(heightLongWall_2_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(heightLongWall_1_1, this.x, this.y, this.tankGame);
                }
            }

        }

        else if( whichWall == WidthLongWall){

            // theWallLevel[1] == 5
            if(theWallLevel[1] == 5){
                if(theWallLevel[0] == 5){
                    g.drawImage(widthLongWall_5_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(widthLongWall_4_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(widthLongWall_3_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(widthLongWall_2_5, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(widthLongWall_1_5, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 4
            else if(theWallLevel[1] == 4){
                if(theWallLevel[0] == 5){
                    g.drawImage(widthLongWall_5_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(widthLongWall_4_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(widthLongWall_3_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(widthLongWall_2_4, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(widthLongWall_1_4, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 3
            else if(theWallLevel[1] == 3){
                if(theWallLevel[0] == 5){
                    g.drawImage(widthLongWall_5_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(widthLongWall_4_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(widthLongWall_3_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(widthLongWall_2_3, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(widthLongWall_1_3, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 2
            else if(theWallLevel[1] == 2){
                if(theWallLevel[0] == 5){
                    g.drawImage(widthLongWall_5_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(widthLongWall_4_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(widthLongWall_3_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(widthLongWall_2_2, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(widthLongWall_1_2, this.x, this.y, this.tankGame);
                }
            }

            // theWallLevel[1] == 1
            else if(theWallLevel[1] == 1){
                if(theWallLevel[0] == 5){
                    g.drawImage(widthLongWall_5_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 4){
                    g.drawImage(widthLongWall_4_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 3){
                    g.drawImage(widthLongWall_3_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 2){
                    g.drawImage(widthLongWall_2_1, this.x, this.y, this.tankGame);
                }
                else if(theWallLevel[0] == 1){
                    g.drawImage(widthLongWall_1_1, this.x, this.y, this.tankGame);
                }
            }

        }


        else if( whichWall == DouleWidthLongWall){
            g.drawImage(doubleWidthLongWall, this.x, this.y, this.tankGame);
        }
        else if( whichWall == StoneWall ){
            g.drawImage(stoneWall, this.x, this.y, this.tankGame);
        }
        else if( whichWall == DoubleStoneWall){
            g.drawImage(doubleWidthStoneWall, this.x, this.y, this.tankGame);
        }

    }

    public void decreseWidthWallLevel(int direction){
        if(this.whichWall == NotLongWall){
            this.theWallLevel[0] --;

            if(direction == Tank.Right){
                this.w = this.w - 10;
                this.x = this.x + 10;
            }
            if(direction == Tank.Left){
                this.w = this.w - 10;
            }
        }
        else if(this.whichWall == HeightLongWall){

            this.theWallLevel[0] --;

            if(direction == Tank.Right){
                this.w = this.w - 10;
                this.x = this.x + 10;
            }
            if(direction == Tank.Left){
                this.w = this.w - 10;
            }
        }
        else if(this.whichWall == WidthLongWall){

            this.theWallLevel[0] --;

            if(direction == Tank.Right){
                this.w = this.w - 11;
                this.x = this.x + 11;
            }
            if(direction == Tank.Left){
                this.w = this.w - 11;
            }

        }

    }

    public void decreaseHeightWallLevel(int direction){
        if(this.whichWall == NotLongWall){
            this.theWallLevel[1] --;

            if(direction == Tank.Up){
                this.h = this.h - 10;
            }
            if(direction == Tank.Down){
                this.h = this.h - 10;
                this.y = this.y + 10;
            }
        }
        else if(this.whichWall == HeightLongWall){
            this.theWallLevel[1] --;

            if(direction == Tank.Up){
                this.h = this.h - 11;
            }
            if(direction == Tank.Down){
                this.h = this.h - 11;
                this.y = this.y + 11;
            }
        }
        else if(this.whichWall == WidthLongWall){
            this.theWallLevel[1] --;

            if(direction == Tank.Up){
                this.h = this.h - 10;
            }
            if(direction == Tank.Down){
                this.h = this.h - 10;
                this.y = this.y + 10;
            }
        }



    }

}


