import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 500, HEIGHT = 500;

    private Thread thread;
    private boolean running;
    private boolean right = true, left = false, up = false, down = false;

    private BodyPart bodyPart;
    private ArrayList<BodyPart> snake;
    private int xCoor = 10, yCoor = 10, size = 5;
    private int ticks = 0;

    public GamePanel(){

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        snake = new ArrayList<BodyPart>();

        start();
    }

    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public void stop(){

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void tick(){

        if(snake.size()==0){
            bodyPart = new BodyPart(xCoor, yCoor, 10);
            snake.add(bodyPart);
        }
        ticks++;
        if(ticks > 25000) {
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;

            ticks = 0;

            bodyPart = new BodyPart(xCoor, yCoor, 10);
            snake.add(bodyPart);

            if(snake.size() > size){
                snake.remove(0);
            }
        }
    }

    public void paint(Graphics g){
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for(int i = 0 ; i < WIDTH/1 ; i++){
            g.drawLine(i*10, 0, i*10, HEIGHT);
        }
        for(int i = 0 ; i < HEIGHT/1 ; i++){
            g.drawLine(0, i*10, HEIGHT, i*10);
        }

        for(int i = 0 ; i < snake.size() ; i++){

        }
    }

    @Override
    public void run() {

        while (running){
            tick();
            repaint();
        }
    }
}
