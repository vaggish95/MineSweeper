package miner;

public class Position {
    public  int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof Position) {
            Position to = (Position) o;
            return to.x == x && to.y == y;
        }
        return super.equals(o);
    }
}
