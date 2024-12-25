// Класс для представления шахматной фигуры
abstract class Piece {
    protected String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean isValidMove(Board board, Position from, Position to);
}

// Класс для представления позиции на доске
class Position {
    private int x; // Столбец
    private int y; // Ряд

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

// Класс для представления шахматной доски
class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        // Инициализация доски с фигурами
    }

    public Piece getPiece(Position position) {
        return board[position.getX()][position.getY()];
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece != null && piece.isValidMove(this, from, to)) {
            board[to.getX()][to.getY()] = piece;
            board[from.getX()][from.getY()] = null;
        }
    }
}

// Класс для представления игры
class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private String currentPlayer;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1.getColor();
    }

    public void makeMove(Position from, Position to) {
        board.movePiece(from, to);
        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals(player1.getColor()) ? player2.getColor() : player1.getColor();
    }
}

// Класс для представления игрока
class Player {
    private String color;

    public Player(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

// Пример использования
public class ChessGame {
    public static void main(String[] args) {
        Player player1 = new Player("White");
        Player player2 = new Player("Black");
        Game game = new Game(player1, player2);

        // Пример хода
        game.makeMove(new Position(1, 0), new Position(3, 0)); // Пример хода
    }
}
