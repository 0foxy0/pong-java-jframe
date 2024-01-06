package game.objects;

import game.Game;

public class Paddle {
    public final static int Velocity = 10;

    private int velocity;
    private int x, y, width, height;
    private boolean isRightPaddle;

    public Paddle(int x, int y, int width, int height, boolean isRightPaddle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isRightPaddle = isRightPaddle;
    }

    public void update(Game game) {
        move(velocity, game);

        Ball ball = game.getBall();
        if (collides(ball)) {
            ball.bounceOff(this);
        }
    }

    public void move(int velocity, Game game) {
        int nextY = y + velocity;

        if (nextY < 0) {
            y -= y;
            return;
        } else if (nextY + height > game.getPanel().getHeight()) {
            y += game.getPanel().getHeight() - (y + height);
            return;
        }

        this.y += velocity;
    }

    public boolean collides(Ball ball) {
        if (isRightPaddle) {
            return ball.getX() >= x && y <= ball.getY() && (ball.getY() + ball.getDiameter()) <= y + height;
        }
        return ball.getX() <= x + width && y <= ball.getY() && (ball.getY() + ball.getDiameter()) <= y + height;
    }

    public void setVelocity(int velocity) {
        if (velocity != Velocity && velocity != -Velocity && velocity != 0) {
            throw new IllegalStateException("paddle velocity can only be: "+ Velocity +" | "+ (-Velocity) +" | 0");
        }
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public boolean isRightPaddle() {
        return isRightPaddle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
