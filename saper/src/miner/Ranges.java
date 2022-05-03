package miner;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {

    private static Position size;
    private static ArrayList <Position> allPositionsList;
    private static Random random = new Random();

    public static void setSize (Position _size) {
        size = _size;
        allPositionsList = new ArrayList<Position>();
        for (int y = 0; y < size.y; y ++)
            for (int x = 0; x < size.x; x ++)
                allPositionsList.add(new Position(x, y));
    }

    public static Position getSize() {
        return size;
    }

    public static ArrayList <Position> getAllPositions() {
        return allPositionsList;
    }

    static boolean inRange (Position position){
        return position.x >= 0 && position.x< size.x &&
                   position.y >= 0 && position.y< size.y;
    }


    static Position getRandomPosition() {
      return new Position(
              random.nextInt (size.x),
              random.nextInt(size.y));
    }

    static ArrayList <Position> getPositionsAround(Position position) {
        Position around;
        ArrayList <Position> list = new ArrayList<>();
        for (int x = position.x - 1; x <= position.x + 1; x ++) {
            for (int y = position.y - 1; y <= position.y + 1; y ++) {
                if (inRange(around = new Position( x, y) ) )
                    if (!around.equals(position) )
                        list.add(around);
            }
        }
        return list;
    }
}
