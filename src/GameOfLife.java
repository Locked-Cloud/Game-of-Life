import java.util.Arrays;

public class GameOfLife {
    private int width;
    private int height;
    private boolean[][] grid;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[height][width];
    }

    public void setCell(int x, int y, boolean alive) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[y][x] = alive;
        }
    }

    public boolean getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[y][x];
        }
        return false;
    }

    public void nextGeneration() {
        boolean[][] newGrid = new boolean[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveNeighbors = countAliveNeighbors(x, y);

                if (grid[y][x]) {
                    newGrid[y][x] = aliveNeighbors == 2 || aliveNeighbors == 3;
                } else {
                    newGrid[y][x] = aliveNeighbors == 3;
                }
            }
        }

        grid = newGrid;
    }

    private int countAliveNeighbors(int x, int y) {
        int count = 0;

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx != 0 || dy != 0) {
                    if (getCell(x + dx, y + dy)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x] ? "O" : ".");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife(20, 10);

        // Initialize a simple pattern (e.g., a blinker)
        gol.setCell(1, 0, true);
        gol.setCell(1, 1, true);
        gol.setCell(1, 2, true);

        // Print the initial state
        gol.printGrid();

        // Run a few generations
        for (int i = 0; i < 5; i++) {
            System.out.println();
            gol.nextGeneration();
            gol.printGrid();
        }
    }
}
