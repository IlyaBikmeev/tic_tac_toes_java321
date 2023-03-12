import board.SimpleBoard;
import game.Game;
import game.SimpleGamePlayerVsPlayer;
import player.Player;
import player.PlayerType;

import java.util.Scanner;

public class TicTacToesGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first;
        String second;

        System.out.println("Введите имя первого игрока: ");
        first = scanner.nextLine();

        System.out.println("Введите имя второго игрока: ");
        second = scanner.nextLine();


        Game game = new SimpleGamePlayerVsPlayer(
            new SimpleBoard(),
            new Player[] {
                new Player(first, PlayerType.CROSSES),
                new Player(second, PlayerType.NOUGHTS)
            }
        );
        game.play();
    }
}
