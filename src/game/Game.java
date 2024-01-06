package game;

import game.objects.Ball;
import game.objects.Paddle;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public final static int FPS = 60;

    private int windowWidth, windowHeight;
    private GameWindow window;
    private GamePanel panel;
    private Paddle[] paddles;
    private Ball ball;
    private int player1Score = 0, player2Score = 0;

    public Game(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        panel = new GamePanel(this);
        window = new GameWindow(this);

        int paddleWidth = panel.getWidth() / 25, paddleHeight = panel.getHeight() / 3;
        paddles = new Paddle[]{
                new Paddle(10, panel.getHeight() / 2 - paddleHeight / 2, paddleWidth, paddleHeight, false),
                new Paddle(panel.getWidth() - paddleWidth - 10, panel.getHeight() / 2 - paddleHeight / 2, paddleWidth, paddleHeight, true)
        };

        int ballRadius = panel.getWidth() / 50;
        ball = new Ball(panel.getWidth() / 2 - ballRadius, panel.getHeight() / 2 - ballRadius, ballRadius);

        TimerTask interval = new TimerTask() {
            @Override
            public void run(){
                panel.repaint();
                gameLoop();
            }
        };

        new Timer().scheduleAtFixedRate(interval, 0, 1000 / FPS);
    }

    public void gameLoop() {
        ball.update(this);

        for (int i = 0; i < paddles.length; i++) {
            paddles[i].update(this);
            if (paddles[i].collides(ball)) {
                ball.bounceOff(paddles[i]);
            }
        }
    }

    public void increaseScore(boolean isPlayer1) {
        if (isPlayer1) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle[] getPaddles() {
        return paddles;
    }

    public GameWindow getWindow() {
        return window;
    }

    public GamePanel getPanel() {
        return panel;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
