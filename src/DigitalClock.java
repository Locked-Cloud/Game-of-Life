import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitalClock extends JPanel {
    private static final int CELL_SIZE = 10;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 60;
    private static final int DIGIT_WIDTH = 6;
    private static final int DIGIT_HEIGHT = 7;

    private GameOfLife gol;
    private Timer timer;

    private static final boolean[][][] DIGITS = {
            {
                    {false, true, true, true, true, false},
                    {true, false, false, false, false, true},
                    {true, false, false, false, false, true},
                    {true, false, false, false, false, true},
                    {true, false, false, false, false, true},
                    {true, false, false, false, false, true},
                    {false, true, true, true, true, false}
            },
            {
                    {false, false, true, true, false, false},
                    {false, true, false, true, false, false},
                    {false, false, false, true, false, false},
                    {false, false, false, true, false, false},
                    {false, false, false, true, false, false},
                    {false, false, false, true, false, false},
                    {false, false, true, true, true, false}
            },
            // Define the rest of the digits (2-9) here...
    };

    public DigitalClock() {
        gol = new GameOfLife(WIDTH, HEIGHT);

        // Set up a timer to update the Game of Life and repaint the panel every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gol.nextGeneration();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (gol.getCell(x, y)) {
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else {
                    g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    private void drawDigit(int digit, int startX, int startY) {
        boolean[][] pattern = DIGITS[digit];

        for (int y = 0; y < DIGIT_HEIGHT; y++) {
            for (int x = 0; x < DIGIT_WIDTH; x++) {
                gol.setCell(startX + x, startY + y, pattern[y][x]);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conway's Game of Life Digital Clock");
        DigitalClock clock = new DigitalClock();

        frame.add(clock);
        frame.setSize(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        clock.drawDigit(1, 2, 2);
        clock.drawDigit(2, 10, 2);
    }
}
