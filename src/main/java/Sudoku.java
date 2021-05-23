
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Sudoku {

    private final int[][] unsolved;
    private int[][] solved;
    private final Model model;
    private final int[] range = IntStream.range(1, 10).toArray();
    private final ArrayList<Integer> flatArray;
    private final int n = 9;

    public Sudoku(int[][] unsolved) {
        this.unsolved = unsolved;
        this.model = new Model("Sudoku");
        this.flatArray = readUnsolved();
        addIntVars();
        setRowConstraints();
        setColumnConstraints();
        setSquareConstraints();
    }

    public void solvePuzzle() {
        Solver solver = model.getSolver();
        if (solver.solve()) {
            solver.showStatistics();
            System.out.println(solver.findSolution());
        } else {
            System.out.println("Puzzle not possible");
        }
    }

    public int[][] getSolved(){
        return solved;
    }

    private ArrayList<Integer> readUnsolved() {
        ArrayList<Integer> array = new ArrayList<>();
        for (int[] row: unsolved) {
            for (int i: row) {
                array.add(i);
            }
        }
        return array;
    }

    private void setRowConstraints() {
        int[] indexes = IntStream.range(0, 73).filter(i -> i % 9 == 0).toArray();
        for (int i : indexes) {
            IntVar[] rowVars = new IntVar[9];
            int index = 0;
            for (int j = 0; j < n; j++) {
                rowVars[index] = getIntVar(i, j);
                index++;
            }
            model.allDifferent(rowVars).post();
        }
    }

    private void setColumnConstraints() {
        int[] indexes = IntStream.range(0, 9).toArray();
        for (int i : indexes) {
            IntVar[] colVars = new IntVar[n];
            int index = 0;
            for (int j = 0; j < 73; j += n) {
                colVars[index] = getIntVar(i, j);
                index++;
            }
            model.allDifferent(colVars).post();
        }
    }

    private void setSquareConstraints() {
        int[] squareStartIndex = new int[]{0, 3, 6, 27, 30, 33, 54, 57, 60};
        for (int i : squareStartIndex) {
            IntVar[] squareVars = new IntVar[n];
            int index = 0;
            for (int j = 0; j < 19; j += 9) {
                squareVars[index++] = getIntVar(i, j);
                squareVars[index++] = getIntVar(i, j + 1);
                squareVars[index++] = getIntVar(i, j + 2);
            }
            model.allDifferent(squareVars).post();
        }
    }

    private IntVar getIntVar(int i, int j) {
        IntVar var;
        int position = i + j;
        var = (IntVar) model.getVar(position);
        return var;
    }

    private void addIntVars() {
        int index = 0;
        for (int i : flatArray) {
            if (i == 0) {
                model.intVar(String.valueOf(index), range);
            }
            else {
                model.intVar(String.valueOf(index), i);
            }
            index++;
        }
    }
}
