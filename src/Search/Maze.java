package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {

    // CELL enum
    public enum Cell {
        EMPTY(" "),
        BLOCKED("X"),
        START("S"),
        GOAL("G"),
        PATH("*");

        private final String code;

        private Cell(String c) {
            code = c;
        }

        @Override
        public String toString() {
            return code;
        }

    }

    // nested class MAZELOCATION
    public static class MazeLocation {
        public final int row;
        public final int column;

        public MazeLocation(int row, int column) {
            this.row = row;
            this.column = column;
        }
        

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + column;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }

            MazeLocation other = (MazeLocation) obj;
            if (column != other.column) {
                return false;
            }
            if (row != other.row) {
                return false;
            }
            return true;
        }
    }

    private final int rows, columns;
    private final MazeLocation start, goal;
    private Cell[][] grid;

    public Maze(int rows, int columns, MazeLocation startingPoint, MazeLocation goalPoint, double sparseness) {
        this.rows = rows;
        this.columns = columns;
        start = startingPoint;
        goal = goalPoint;

        grid = new Cell[rows][columns];
        for (Cell[] row : grid) {
            Arrays.fill(row, Cell.EMPTY);
        }
        randomlyFill(sparseness);
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    public Maze() {
        this(10, 10, new MazeLocation(0, 0), new MazeLocation(9, 9), 0.2);
    }

    private void randomlyFill(double sparseness) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (Math.random() < sparseness) {
                    grid[row][column] = Cell.BLOCKED;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                sb.append(cell.toString());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public boolean goalTest(MazeLocation ml) {
        return goal.equals(ml);
    }

    // now, we defined the maze and all the functions that let us operate on it
    // but we need a way to MOVE in out maze

    // this function returns the possible cells to which we
    // could move (checks above, below, to the right and to
    // the left of a MazeLocation to see where is free)
    public List<MazeLocation> successors(MazeLocation ml) {
        List<MazeLocation> locations = new ArrayList<>();
        if (ml.row + 1 < rows && grid[ml.row + 1][ml.column] != Cell.BLOCKED) {
            locations.add(new MazeLocation(ml.row + 1, ml.column));
        }
        if (ml.row - 1 >= 0 && grid[ml.row - 1][ml.column] != Cell.BLOCKED) {
            locations.add(new MazeLocation(ml.row - 1, ml.column));
        }
        if (ml.column + 1 < columns && grid[ml.row][ml.column + 1] != Cell.BLOCKED) {
            locations.add(new MazeLocation(ml.row, ml.column + 1));
        }
        if (ml.column - 1 >= 0 && grid[ml.row][ml.column - 1] != Cell.BLOCKED) {
            locations.add(new MazeLocation(ml.row, ml.column - 1));
        }
        return locations;
    }

    // depth-first-search

    // breadth-first-search

    // a* algo

    public static void main(String... strings) {
        Maze m = new Maze();
        System.out.println(m);
    }

}
