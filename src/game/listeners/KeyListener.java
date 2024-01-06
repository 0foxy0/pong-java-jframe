package game.listeners;

import game.Game;
import game.objects.Paddle;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    private Game game;

    public KeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                game.getPaddles()[0].setVelocity(-Paddle.Velocity);
                break;
            case KeyEvent.VK_S:
                game.getPaddles()[0].setVelocity(Paddle.Velocity);
                break;

            case KeyEvent.VK_UP:
                game.getPaddles()[1].setVelocity(-Paddle.Velocity);
                break;
            case KeyEvent.VK_DOWN:
                game.getPaddles()[1].setVelocity(Paddle.Velocity);
                break;

            case KeyEvent.VK_SPACE:
                game.getBall().firstMove();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                game.getPaddles()[0].setVelocity(0);
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                game.getPaddles()[1].setVelocity(0);
                break;
        }
    }
}
