import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import miner.*;
import miner.Box;

public class Minesweeper extends JFrame {

    private final int COLUMNS = 10;
    private final int ROWS =10;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;// размер каждй карнтинки
    private JPanel WINDOW;
    private JLabel GAME_STATUS_LABEL;
    private JLabel BOMBS_COUNTER_LABEL;
    private Game game;

    public static void main(String[] args) {
        new Minesweeper();
    }

    private Minesweeper() {
        game = new Game (COLUMNS, ROWS, BOMBS );
        game.start ();
        window();
        initGameStatusLabel() ;
        initBombsCounterLabel();
        initFrame();
        setImages();
    }

    private void initGameStatusLabel () {
        GAME_STATUS_LABEL = new JLabel("Let's get started");
        add (GAME_STATUS_LABEL,  BorderLayout.SOUTH);
    }

    private void initBombsCounterLabel () {
        BOMBS_COUNTER_LABEL = new JLabel();
        add(BOMBS_COUNTER_LABEL, BorderLayout.NORTH);
    }

    private void window() {
        WINDOW = new JPanel () {
            @Override
            protected void paintComponent (Graphics g) {
                for ( Position position : Ranges.getAllPositions() ) {
                    g.drawImage( (Image) game.getBox(position).image,
                            position.x * IMAGE_SIZE, position.y * IMAGE_SIZE, this);
                }
            }
        };

        WINDOW.addMouseListener (new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY()/ IMAGE_SIZE;
                Position position = new Position(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton (position);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton (position);
                }
                GAME_STATUS_LABEL.setText (getMessage());
                BOMBS_COUNTER_LABEL.setText(mines());
                WINDOW.repaint();
            }
        });

        WINDOW.setPreferredSize (new Dimension (
                 Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y* IMAGE_SIZE + 10));
        add(WINDOW);
    }

    private String getMessage() {
        switch ((game.getState())) {
            case PLAYED : return "Find the next bomb";
            case BOMBED: return "YOU LOSE";
            case WINNER: return "YOU WON";
            default: return "ERROR";
        }
    }

    private String mines() {
        int minesAmount = BOMBS - game.getFoundMines();
        return "BOMBS : " + minesAmount ;
    }

    private void initFrame () {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        setVisible(true );
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private Image getImage (String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void setImages() {
        for (Box box : Box.values()){
            box.image = getImage(box.name().toLowerCase());
        }
    }

}
