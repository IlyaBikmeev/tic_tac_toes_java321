package board;

import player.PlayerType;

public interface Board {
    PlayerType getCell(int row, int column);    //Получить состояние клетки по номеру строки и столбца
    void putInCell(int row, int column, PlayerType type);   //Нарисовать конкретную фигуру в конкретной клетке
    boolean isFree(int row, int column);    //Свободна ли клетка
}
