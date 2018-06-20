import java.awt.*;

public class Bird {

    private boolean live;
    private int x,y;
    public static Image birdImage = Toolkit.getDefaultToolkit().createImage("pictures/bird.png");
    private TankGame tankGame;
    private int Width = 50,Height = 50;

    Bird(int x, int y){
        this.x = x;
        this.y = y;
        this.live = true;
    }

    public void draw(Graphics g){
        g.drawImage(birdImage, this.x, this.y, this.tankGame);
    }

    public void setLive(boolean isAlive){
        this.live = isAlive;
    }

    public Rectangle getRect(){
        return new Rectangle(this.x, this.y, this.Width, this.Height);
    }

    public boolean isAlive(){
        return this.live;
    }

}
