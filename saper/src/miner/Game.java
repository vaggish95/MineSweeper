package miner;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public Game (int columns, int rows, int bombs){
        Ranges.setSize (new Position(columns, rows) );
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start () {
        bomb.start();
        flag.start();
        gameState = GameState.PLAYED;
    }

    public void pressLeftButton(Position position)  {
        if (gameOver()) return;
        if (bomb.get(position) == Box.BOMB) {
            openBombs(position);
        }
        else
            flag.setOpenedToBox (position);
        checkWinner();
    }

    private void openBombs (Position bombed) {
        gameState = GameState.BOMBED;
        for (Position position : Ranges.getAllPositions()) {
            if (bomb.get(position) == Box.BOMB)
                flag.setOpenedToClosedBombBox(position);
            else
                flag.setNoBombToFlagedSafeBox(position);
        }
        flag.setBombedToBox(bombed);
    }

    public void pressRightButton(Position position) {
        if (gameOver()) return;
        flag.flagSignSwitcher(position);
    }

    private void checkWinner () {
        if (gameState == GameState.PLAYED){
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBoms ()) {
                gameState = GameState.WINNER;
            }
        }
    }

    public Box getBox (Position position) {
        if (flag.get(position) == Box.OPENED)
            return bomb.get(position) ;
        else return flag.get(position);
    }

    public GameState getState () {
        return gameState;
    }

    private boolean gameOver () {
        if (gameState == GameState.PLAYED) return false;
        else  start();
        return true;
    }

    public int getFoundMines () {
        return flag.getFoundMines();
    }

}
