package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Main extends JFrame implements KeyListener, ActionListener {

    Snake snake;

    public Main() {
        //Create the snake
        this.snake = new Snake(this);

        //Timer for redrawing the screen
        Timer timer = new Timer(150, this);
        timer.start();

        //Timer for drawing apples on the screen every 3 seconds
        java.util.Timer drawApples = new java.util.Timer();
        Fruit st = new Fruit(this.snake);
        drawApples.schedule(st,0,3000);

        //Create window and drawing
        add(this.snake);
        setTitle("Snake Game");
        setSize(900, 900);
        this.addKeyListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        if (c == 39 && !this.snake.getDirection().equals("left")) {
            this.snake.setDirection("right"); //Right arrow pressed
        }

        else if (c == 37 && !this.snake.getDirection().equals("right")) {
            this.snake.setDirection("left"); //Left arrow pressed
        }

        else if (c == 38 && !this.snake.getDirection().equals("down")) {
            this.snake.setDirection("up"); //Up arrow pressed
        }

        else if (c == 40 && !this.snake.getDirection().equals("up")) {
            this.snake.setDirection("down"); //Down arrow pressed
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //redraw the screen
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Main::new);
    }

}
