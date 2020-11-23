package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Josué Fernández
 */
public class HumanPlayer extends Player {

    private final Scanner sc;
    private final String name;

    public HumanPlayer(char letter, String name, Scanner sc) {
        super(letter);
        this.name = name;
        this.sc = sc;
    }

    @Override
    public int move(String board) {

        int[] available = availableSpaces(board);
        int position = -1;

        do {
            try {
                position = sc.nextInt();
            } catch (InputMismatchException e) {
                position = -1;
                System.out.print("Entrada inválida. Ingrese una posición disponible: ");
            }
        } while (!contains(available, position - 1));

        return position-1;
    }

    private boolean contains(int[] array, int number) {
        for (int i : array) {
            if (number == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "HumanPlayer: " + name;
    }

}
