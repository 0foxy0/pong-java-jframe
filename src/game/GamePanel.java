package game;

import game.objects.Ball;
import game.objects.Paddle;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GamePanel extends JPanel {
    private Game game;
    private final Map<?, ?> desktopHints = (Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
    private final Font scoreFont = new Font("Arial", Font.BOLD, 32);
    private final int scoreY = 35;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(Color.BLACK);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHints(desktopHints);

        Paddle[] paddles = game.getPaddles();
        Ball ball = game.getBall();

        g.setColor(Color.WHITE);
        g.setFont(scoreFont);

        FontMetrics scoreFontMetrics = g.getFontMetrics(scoreFont);

        int player1ScoreX = game.getPaddles()[0].getX() + game.getPaddles()[0].getWidth() + 15;
        int player2ScoreX = getWidth() - scoreFontMetrics.stringWidth(String.valueOf(game.getPlayer2Score())) - (game.getPaddles()[1].getWidth() + 25);

        g.drawString(String.valueOf(game.getPlayer1Score()), player1ScoreX, scoreY);
        g.drawString(String.valueOf(game.getPlayer2Score()),  player2ScoreX, scoreY);

        for (Paddle paddle : paddles) {
            g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
        }
        g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
    }
}
