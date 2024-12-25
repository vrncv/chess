import java.util.ArrayList;
import java.util.List;

class Knight extends Piece {

    public Knight(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        // Проверка, что конь делает ход по шаблону "Г"
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());

        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            // Проверка, что конечная позиция не занята фигурой того же цвета
            Piece targetPiece = board.getPiece(to);
            return targetPiece == null || !targetPiece.getColor().equals(color);
        }
        
        return false; // Если не соответствует "Г"-образному шаблону
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> validMoves = new ArrayList<>();
        
        // Все возможные позиции, на которые конь может прыгнуть
        int[][] moves = {
            {2, 1}, {2, -1},
            {-2, 1}, {-2, -1},
            {1, 2}, {1, -2},
            {-1, 2}, {-1, -2}
        };
        
        for (int[] move : moves) {
            int newX = from.getX() + move[0];
            int newY = from.getY() + move[1];
            Position to = new Position(newX, newY);

            if (board.isPositionValid(to) && isValidMove(board, from, to)) {
                validMoves.add(to);
            }
        }

        return validMoves;
    }
}
