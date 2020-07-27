
import java.util.Random;

public class Universe {

    public static int size;

    public static String[][] universe;

    public static int livesNo = 0;
    public static int generationNo = 1;


    public Universe(int size) {
        Universe.size = size;
        Universe.universe = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Random random = new Random();
                universe[i][j] = random.nextBoolean() ? "O" : " ";
            }
        }

    }

    public static void generateNextUniverse() {

        String[][] nextUniverse = new String[size][size];

        for (int i = 0; i < universe.length; i++) {
            for (int k = 0; k < universe.length; k++) {
                if (universe[i][k].equals("O")) {
                    nextUniverse[i][k] = checkNeighbours(i, k) < 2 || checkNeighbours(i, k) > 3 ? " " : "O";
                } else if (checkNeighbours(i, k) == 3) {
                    nextUniverse[i][k] = "O";
                } else nextUniverse[i][k] = " ";

                if (universe[i][k].equals("O")) {
                    livesNo++;
                }
            }
        }

        universe = nextUniverse;
    }

    public static int checkNeighbours(int i, int k) {

        int numberOfNeighbours = 0;
        int size = universe.length;

        for (int l = i - 1; l <= i + 1; l++) {
            for (int n = k - 1; n <= k + 1; n++) {
                if (universe[l < 0 ? size - 1 : l == size ? 0 : l][n < 0 ? size - 1 : n == size ? 0 : n].equals("O")) {
                    numberOfNeighbours++;
                }
            }
        }
        return universe[i][k].equals("O") ? numberOfNeighbours - 1 : numberOfNeighbours;
    }
}
