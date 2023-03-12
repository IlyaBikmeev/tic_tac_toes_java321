package board;

import player.PlayerType;

//Обычная доска в крестики нолики размерности 3х3
public class SimpleBoard implements Board {
    private final PlayerType[][] board;

    public SimpleBoard() {
        this.board = new PlayerType[3][3];
    }

    //Проверяет на корректность номер строки и столбца
    private boolean isValid(int row, int column) {
        return row >= 1 && row <= 3
                && column >= 1 && column <= 3;
    }

    @Override
    public PlayerType getCell(int row, int column) {
        if(!isValid(row, column)) {
            throw new ArrayIndexOutOfBoundsException("Numbers of row and column must be in range(1, 3)");
        }
        return this.board[row - 1][column - 1];
    }

    @Override
    public void putInCell(int row, int column, PlayerType type) {
        if(!isValid(row, column)) {
            throw new ArrayIndexOutOfBoundsException("Numbers of row and column must be in range(1, 3)");
        }
        this.board[row - 1][column - 1] = type;
    }

    @Override
    public boolean isFree(int row, int column) {
        return getCell(row, column) == null;
    }
}
