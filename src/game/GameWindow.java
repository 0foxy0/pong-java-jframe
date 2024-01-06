package game;

import game.listeners.KeyListener;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(Game game) {
        setTitle("Pong");
        setSize(game.getWindowWidth(), game.getWindowHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setContentPane(game.getPanel());

        KeyListener keyListener = new KeyListener(game);
        addKeyListener(keyListener);

        setVisible(true);
    }
}
