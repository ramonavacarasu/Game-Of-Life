
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;

enum State {
    RUNNING, PAUSED, RESET,
}

public class GameOfLife extends JFrame {

    public volatile static State state = State.RUNNING;
    public static int speed = 1;

    public static Color filledCellColor = Color.BLACK;
    public static Color gridColor = Color.BLACK;
    public static JLabel generation = new JLabel();
    public static JLabel alive = new JLabel();
    public static JLabel speedLabel = new JLabel();
    public static GraphicsGame graphicsGame;
    public static boolean changeFillColor = false;
    public static boolean changeGridColor = false;


    public GameOfLife() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Game Of Life");
        setMinimumSize(new Dimension(1200, 620));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel spacePanelNorth = new JPanel();
        spacePanelNorth.setPreferredSize(new Dimension(1200, 20));
        add(spacePanelNorth, BorderLayout.NORTH);

        generation.setName("GenerationLabel");
        generation.setText("Generation #0");

        alive.setName("AliveLabel");
        alive.setText("Alive: 0");


        JToggleButton pausePlay = new JToggleButton("Pause");
        pausePlay.setName("PlayToggleButton");
        pausePlay.addActionListener(actionEvent -> {
            switch (state) {
                case PAUSED -> {
                    state = State.RUNNING;
                    pausePlay.setText("Pause");
                }
                case RUNNING -> {
                    state = State.PAUSED;
                    pausePlay.setText("Resume");
                }
            }
        });


        // Universe Size: set size -> RESET
        JLabel setSizeLabel = new JLabel();
        setSizeLabel.setText("Universe Size: ");

        JTextField setSizeField = new JTextField();
        setSizeField.setText("20");
        setSizeField.setPreferredSize(new Dimension(50, 20));

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(actionEvent -> {
            try {
                Universe.size = Integer.parseInt(setSizeField.getText());
            } catch (NumberFormatException ignored) {
            }
            state = State.RESET;
        });

        JPanel setParamsPanel = new JPanel();
        setParamsPanel.setLayout(new BoxLayout(setParamsPanel, BoxLayout.X_AXIS));
        setParamsPanel.add(setSizeLabel);
        setParamsPanel.add(setSizeField);
        setParamsPanel.add(resetButton);


        //increasing/decreasing evolution speed
        speedLabel.setName("SpeedLabel");
        speedLabel.setText("Speed: " + speed);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(changeEvent -> speed = speedSlider.getValue());


        //change color
        JRadioButton filledCellsColorRadioBtn = new JRadioButton("Filled Cell Color");
        filledCellsColorRadioBtn.addActionListener(actionEvent -> changeFillColor = !changeFillColor);

        JRadioButton gridColorRadioBtn = new JRadioButton("Grid color");
        gridColorRadioBtn.addActionListener(actionEvent -> changeGridColor = !changeGridColor);

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (!accp.getDisplayName().equals("HSV")) {
                colorChooser.removeChooserPanel(accp);
            }
        }

        colorChooser.getSelectionModel().addChangeListener(changeEvent -> {
            Color color = colorChooser.getColor();

            if (changeFillColor) {
                filledCellColor = color;
            }

            if (changeGridColor) {
                gridColor = color;
            }
        });


        // organise left part with GroupLayout
        JPanel leftPanel = new JPanel();
        GroupLayout layout = new GroupLayout(leftPanel);
        leftPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pausePlay)
                        .addComponent(generation)
                        .addComponent(alive)
                        .addComponent(speedLabel)
                        .addComponent(speedSlider)
                        .addComponent(setParamsPanel, 100, 150, 200)
                        .addComponent(filledCellsColorRadioBtn)
                        .addComponent(gridColorRadioBtn)
                        .addComponent(colorChooser))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pausePlay))
                .addComponent(generation)
                .addComponent(alive)
                .addComponent(speedLabel)
                .addComponent(speedSlider)
                .addComponent(setParamsPanel, 20, 30, 30)
                .addComponent(filledCellsColorRadioBtn)
                .addComponent(gridColorRadioBtn)
                .addComponent(colorChooser)
        );


        graphicsGame = new GraphicsGame();
        JPanel gamePanel = new JPanel();
        gamePanel.setMinimumSize(new Dimension(100, 100));
        gamePanel.setPreferredSize(new Dimension(600, 600));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.add(graphicsGame);

        add(leftPanel, BorderLayout.WEST);
        add(gamePanel, BorderLayout.CENTER);

    }

}