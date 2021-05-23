import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SudokuTest {

    private ArrayList<Sudoku> sudokus = new ArrayList<>();


    @Before
    public void setup() {
        int[][] unsolved = new int[][]{
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };
        Sudoku test = new Sudoku(unsolved);

        int[][] arr = new int[][]{
                {5, 8, 0, 2, 0, 0, 4, 7, 0},
                {0, 2, 0, 0, 0, 0, 0, 3, 0},
                {0, 3, 0, 0, 5, 4, 0, 0, 0},
                {0, 0, 0, 5, 6, 0, 0, 0, 0},
                {0, 0, 7, 0, 3, 0, 9, 0, 0},
                {0, 0, 0, 0, 9, 1, 0, 0, 0},
                {0, 0, 0, 8, 2, 0, 0, 6, 0},
                {0, 7, 0, 0, 0, 0, 0, 8, 0},
                {0, 9, 4, 0, 0, 6, 0, 1, 5}
        };
        Sudoku sud = new Sudoku(arr);

        sudokus.add(test);
        sudokus.add(sud);
    }

    @Test
    public void testSolve() {
        for (Sudoku s : sudokus) {
            s.solvePuzzle();
        }
    }

}
