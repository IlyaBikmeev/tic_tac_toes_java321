package game;

import board.Board;
import player.Player;
import player.PlayerType;

import java.util.Scanner;

//Обычная игра 3х3 для двоих живых игроков
//Они играют через 1 консоль
public class SimpleGamePlayerVsPlayer implements Game {
    private final Board board;
    private final Player[] players;
    private int currentMove;    //Какой игрок сейчас ходит (0 или 1).

    public SimpleGamePlayerVsPlayer(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        this.currentMove = 0;
    }

    private Player currentPlayer() {
        return players[currentMove];
    }

    //Метод будет передавать ход следующему игроку
    private void switchMove() {
        //currentMove = (currentMove == 0 ? 1 : 0);
        //currentMove = (currentMove + 1) % 2;
        currentMove ^= 1;
    }

    private void printState() {
        for(int i = 1; i <= 3; ++i) {
            for(int j = 1; j <= 3; ++j) {
                System.out.print(!board.isFree(i, j) ? board.getCell(i, j).value() + " " : ". ");
            }
            System.out.println();
        }
    }

    private boolean isPlayerWin(PlayerType type) {
        for(int i = 1; i <= 3; ++i) {
            boolean winByRow = true;
            boolean winByColumn = true;
            for(int j = 1; j <= 3; ++j) {
                PlayerType curRow = board.getCell(i, j);
                PlayerType curColumn = board.getCell(j, i);
                if(!type.equals(curRow)) {
                    winByRow = false;
                }
                if(!type.equals(curColumn)) {
                    winByColumn = false;
                }
            }
            if(winByRow || winByColumn) {
                return true;
            }
        }

        //Главная диагональ
        boolean winByPrimaryDiagonal = true;
        //Побочная диагональ
        boolean winBySecondaryDiagonal = true;

        for(int delta = 0; delta < 3; ++delta) {
            if(!type.equals(board.getCell(1 + delta, 1 + delta))) {
                winByPrimaryDiagonal = false;
            }
            if(!type.equals(board.getCell(1 + delta, 3 - delta))) {
                winBySecondaryDiagonal = false;
            }
        }
        return winByPrimaryDiagonal || winBySecondaryDiagonal;
    }

    //Ничья
    private boolean isDraw() {
        if(isPlayerWin(PlayerType.CROSSES) || isPlayerWin(PlayerType.NOUGHTS)) {
            return false;
        }
        for(int i = 1; i <= 3; ++i) {
            for(int j = 1; j <= 3; ++j) {
                if(board.isFree(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        while(!gameOver) {
            System.out.println("Текущее состояние игрового поля:");
            printState();

            System.out.println("Ходит игрок: " + currentPlayer().getName());
            System.out.println("Напишите номер строки и столбца, куда хотите поставить: ");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            if(board.isFree(row, column)) {
                board.putInCell(row, column, currentPlayer().getType());
                if(isPlayerWin(currentPlayer().getType()) || isDraw()) {
                    gameOver = true;
                    printGameOver();
                }
                else {
                    switchMove();
                }
            }
            else {
                System.out.println("Клетка уже занята:( Выберите другую...");
            }
        }
    }

    private void printGameOver() {
        System.out.println("Текущее состояние игрового поля:");
        printState();
        if(isDraw()) {
            System.out.println("Ничья! Победила дружба!!!");
        }
        else {
            System.out.println("Победил игрок: " + currentPlayer().getName());
        }
    }
}
