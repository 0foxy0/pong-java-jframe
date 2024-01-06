package game.objects;

import game.Game;

import java.util.Random;

public class Ball {
    private final int Velocity = 7;

    private int startX, startY;
    private int x, y, radius;
    private int xVelocity, yVelocity;
    private boolean active = false;

    public Ball(int x, int y, int radius) {
        startX = x;
        startY = y;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void update(Game game) {
        x += xVelocity;
        y += yVelocity;

        bounceOffFloorAndCeiling(game);

        if (x < 0) {
            game.increaseScore(false);
            reset();
        } else if (x + getDiameter() > game.getPanel().getWidth()) {
            game.increaseScore(true);
            reset();
        }
    }

    public void firstMove() {
        if (active) {
            return;
        }
        active = true;

        int rnd = new Random().nextInt(2);

        if (rnd == 0) {
            xVelocity = -Velocity;
        } else {
            xVelocity = Velocity;
        }
    }

    public void bounceOff(Paddle paddle) {
        if (paddle.getVelocity() < 0) {
            yVelocity -= Velocity + 1;
        } else if (paddle.getVelocity() > 0) {
            yVelocity += Velocity + 1;
        }

        if (paddle.isRightPaddle()) {
            xVelocity = -Velocity;
        } else {
            xVelocity = Velocity;
        }
    }

    private void bounceOffFloorAndCeiling(Game game) {
        if (y >= game.getPanel().getHeight()) {
            yVelocity = -Velocity;
        } else if (y <= 0) {
            yVelocity = Velocity;
        }
    }

    public void reset() {
        xVelocity = 0;
        yVelocity = 0;
        x = startX;
        y = startY;
        active = false;
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        return radius * 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
