package miner;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start () {
        bombMap = new Matrix((Box.ZERO));
        for (int x = 0; x < totalBombs; x ++) {
            placeBomb ();
        }
    }

    Box get (Position position) {
        return bombMap.get (position);
    }

    private void fixBombsCount () {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2 ;
        if (totalBombs > maxBombs) {
            totalBombs = maxBombs;
        }
    }

    private void placeBomb () {
        while (true) {
            Position position = Ranges.getRandomPosition();
            if (Box.BOMB == bombMap.get(position)) {
                continue;
            }
            bombMap.set(position, Box.BOMB);
            incNumbersAroundBomb(position);
            break;
        }
    }

    private void incNumbersAroundBomb (Position position) {
        for (Position around : Ranges.getPositionsAround(position))
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get (around).getNextNumberBox ());
            }
    }

     int getTotalBoms() {
        return totalBombs;
    }
}
