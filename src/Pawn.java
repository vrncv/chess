import java.util.ArrayList;
import java.util.List;

class Pawn extends Piece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        // Проверка на правильность перемещения вперед
        int direction = color.equals("White") ? 1 : -1; // вперед для белых - вниз, для черных - вверх
        int startRow = color.equals("White") ? 1 : 6; // начальная позиция

        // Простой ход вперед
        if (to.getX() == from.getX() && to.getY() == from.getY() + direction && board.getPiece(to) == null) {
            return true;
        }

        // Первый ход: два поля вперед
        if (from.getY() == startRow && to.getX() == from.getX() && to.getY() == from.getY() + 2 * direction &&
            board.getPiece(to) == null && board.getPiece(new Position(from.getX(), from.getY() + direction)) == null) {
            return true;
        }

        // Захват: две клетки по диагонали
        if ((to.getX() == from.getX() - 1 || to.getX() == from.getX() + 1) && 
            to.getY() == from.getY() + direction && board.getPiece(to) != null && 
            !board.getPiece(to).getColor().equals(color)) {
            return true;
        }

        return false;
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> validMoves = new ArrayList<>();
        int direction = color.equals("White") ? 1 : -1; // направление движения

        // Простой ход вперед
        Position forward = new Position(from.getX(), from.getY() + direction);
        if (board.isPositionValid(forward) && board.getPiece(forward) == null) {
            validMoves.add(forward);
        }

        // Первый ход: два поля вперед
        Position doubleForward = new Position(from.getX(), from.getY() + 2 * direction);
        if (from.getY() == (color.equals("White") ? 1 : 6) && 
            board.isPositionValid(doubleForward) && 
            board.getPiece(doubleForward) == null && 
            board.getPiece(new Position(from.getX(), from.getY() + direction)) == null) {
            validMoves.add(doubleForward);
        }

        // Захват: две клетки по диагонали
        for (int dx = -1; dx <= 1; dx += 2) { // только -1 и 1
            Position capture = new Position(from.getX() + dx, from.getY() + direction);
            if (board.isPositionValid(capture) && 
                board.getPiece(capture) != null && 
                !board.getPiece(capture).getColor().equals(color)) {
                validMoves.add(capture);
            }
        }

        return validMoves;
    }
}
