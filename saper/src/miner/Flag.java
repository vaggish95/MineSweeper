package miner;

import static miner.Box.*;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;
    private int foundMines;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    void setOpenedToBox(Position position) {
        flagMap.set(position, Box.OPENED);
        countOfClosedBoxes--;
    }

    public void flagSignSwitcher(Position position) {
        switch (flagMap.get(position)) {
            case FLAGED:
                setClosedToBox(position);
                break;

            case CLOSED:
                setFlagedToBox(position);
                break;
        }
    }

    private void setClosedToBox(Position position) {
        flagMap.set(position, CLOSED);
        foundMines--;
        System.out.println(foundMines);
    }

    void setFlagedToBox(Position position) {
        flagMap.set(position, FLAGED);
        foundMines++;
        System.out.println(foundMines);
    }

    public void setOpenedToClosedBombBox(Position position) {
        if (flagMap.get(position) == Box.CLOSED)
            flagMap.set(position, Box.OPENED);
    }

    public void setNoBombToFlagedSafeBox(Position position) {
        if (flagMap.get(position) == Box.FLAGED)
            flagMap.set(position, Box.NOBOMB);
    }

    public void setBombedToBox(Position position) {
        flagMap.set(position, BOMBED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    Box get(Position position) {
        return flagMap.get(position);
    }

    public int getFoundMines() {
        return foundMines;
    }

}
