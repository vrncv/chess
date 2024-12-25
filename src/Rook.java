import java.util.ArrayList;
import java.util.List;

class Rook extends Piece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        // Проверка, что движение либо по горизонтали, либо по вертикали
        if (from.getX() != to.getX() && from.getY() != to.getY()) {
            return false; // Ладья не может двигаться по диагонали
        }

        // Проверка, что между начальной и конечной позициями нет других фигур
        return isPathClear(board, from, to);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> validMoves = new ArrayList<>();

        // Проверка всех возможных вертикальных и горизонтальных ходов
        for (int x = 0; x < 8; x++) { // По горизонтали
            if (x != from.getX()) { // Исключить текущую позицию
                Position to = new Position(x, from.getY());
                if (isValidMove(board, from, to)) {
                    validMoves.add(to);
                }
            }
        }

        for (int y = 0; y < 8; y++) { // По вертикали
            if (y != from.getY()) { // Исключить текущую позицию
                Position to = new Position(from.getX(), y);
                if (isValidMove(board, from, to)) {
                    validMoves.add(to);
                }
            }
        }

        return validMoves;
    }

    private boolean isPathClear(Board board, Position from, Position to) {
        int deltaX = Integer.signum(to.getX() - from.getX());
        int deltaY = Integer.signum(to.getY() - from.getY());

        int x = from.getX() + deltaX;
        int y = from.getY() + deltaY;

        // Проверяем все клетки между начальным и конечным положением
        while (x != to.getX() || y != to.getY()) {
            if (board.getPiece(new Position(x, y)) != null) {
                return false; // Если на пути есть фигура, путь не свободен
            }
            x += deltaX;
            y += deltaY;
        }

        return true; // Путь свободен
    }
}
