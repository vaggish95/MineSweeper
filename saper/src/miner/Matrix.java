package miner;

 class Matrix {
    private Box [] [] matrix;

    Matrix (Box boxByDefault) {
        matrix = new Box [Ranges.getSize().x ] [ Ranges.getSize().y ];
        for (Position position : Ranges.getAllPositions())
            matrix [position.x] [position.y] = boxByDefault;
    }

    Box get (Position position) {
        return matrix [position.x] [position.y];
    }

    void set (Position position, Box box) {
        if (Ranges.inRange(position)){
            matrix [position.x] [position.y] = box;
        }
    }
}
