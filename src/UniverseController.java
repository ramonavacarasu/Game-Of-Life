
public class UniverseController {

    private Universe univ;

    public UniverseController(Universe universe, GameOfLife gameOfLife) {
        this.univ = universe;
    }
    public void advanceGeneration() {

        while (true) {
            switch (GameOfLife.state) {
                case RUNNING:
                    Universe.generateNextUniverse();
                    updateView();
                    break;
                case PAUSED:
                    break;
                case RESET:
                    Universe.generationNo = 0;
                    GameOfLife.state = State.RUNNING;
                    univ = new Universe(Universe.size);
                    break;
            }
        }

    }

    public void updateView() {
        try {
            Thread.sleep(1000 / GameOfLife.speed);
        } catch (InterruptedException ignored) {}
        GameOfLife.generation.setText("Generation #" + Universe.generationNo);
        GameOfLife.alive.setText("Alive: " + Universe.livesNo);
        GameOfLife.speedLabel.setText("Speed: " + GameOfLife.speed);
        GameOfLife.graphicsGame.draw(univ);
        Universe.generationNo++;
        Universe.livesNo = 0;
    }
}