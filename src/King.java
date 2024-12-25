import java.util.ArrayList;
import java.util.List;

class King extends Piece {

    public King(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());

        // Проверяем, что король может двигаться на одну клетку в любом направлении
        return (dx <= 1 && dy <= 1);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> validMoves = new ArrayList<>();

        // Возможные направления движения короля
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, // Горизонтально и вертикально
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Диагонали
        };

        for (int[] direction : directions) {
            int newX = from.getX() + direction[0];
            int newY = from.getY() + direction[1];
            Position to = new Position(newX, newY);

            // Проверяем на валидность позиции
            if (board.isPositionValid(to)) {
                Piece targetPiece = board.getPiece(to);
                // Добавляем позицию только если она свободна или там фигура противника
                if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                    validMoves.add(to);
                }
            }
        }

        return validMoves;
    }
}
