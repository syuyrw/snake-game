package main;

import java.util.Random;
import java.util.TimerTask;

public class Fruit extends TimerTask {


    private int positionX;
    private int positionY;
    private Snake snake;


    //Get Apple Position
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Fruit(Snake snake) {
        this.snake = snake;
    }

    //Create Apple
    public Fruit() {
        //Set Apple Position
        this.positionX = 25 * new Random().nextInt(20);
        this.positionY = 25 * new Random().nextInt(20);
    }

    //Make New Apple After Old One Is Eaten Or 3 Seconds Have Passed
    @Override
    public void run() {
        if (this.snake.getApple() == null) {
            this.snake.setApple(new Fruit());
        }
    }
}
