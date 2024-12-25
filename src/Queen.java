import java.util.ArrayList;
import java.util.List;

class Queen extends Piece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());

        // Проверяем, что движение происходит по вертикали, горизонтали или диагонали
        if (dx == 0 || dy == 0 || dx == dy) {
            return isPathClear(board, from, to);
        }

        return false; // Не соответствует правилам движения ферзя
    }

    @Override
    public List<Position> getValidMoves(Board board, Position from) {
        List<Position> validMoves = new ArrayList<>();

        // Возможные направления движения ферзя (горизонтально, вертикально и по диагоналям)
        int[][] directions = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}, // Горизонтальные и вертикальные
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Диагонали
        };

        for (int[] direction : directions) {
            int newX = from.getX();
            int newY = from.getY();

            // Продолжаем двигаться в этом направлении, пока не встретим край доски или другую фигуру
            while (true) {
                newX += direction[0];
                newY += direction[1];
                Position to = new Position(newX, newY);

                // Проверка на валидность позиции
                if (!board.isPositionValid(to)) {
                    break; // Выходим за пределы доски
                }

                // Если на позиции стоит фигура
                Piece targetPiece = board.getPiece(to);
                if (targetPiece != null) {
                    // Добавляем только если это фигура противника
                    if (!targetPiece.getColor().equals(this.color)) {
                        validMoves.add(to);
                    }
                    break; // Если встретили любую фигуру, дальнейшее движение невозможно
                }

                // Добавляем позицию, если она свободна
                validMoves.add(to);
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
