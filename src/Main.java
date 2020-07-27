public class Main {

    public static void main(String[] args) {

        Universe universe = new Universe(20);
        UniverseController universeController = new UniverseController(universe, new GameOfLife());
        universeController.advanceGeneration();
    }
}