package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Josué Fernández
 */
public class TicTacToe {

    private static Board board;
    private static Player player1;
    private static Player player2;

    private static final char CHAR_1 = 'X';
    private static final char CHAR_2 = '0';

    static Scanner sc = new Scanner(System.in);

    static int c = 0;
    static int d = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Player player = new SmartComputerPlayer('X');
//        String b = "XX_00____";
//        System.out.println(player.move(b));

        /**
         * X _ 0
         * _ 0 _
         * _ _ X
         */
        int option = 0;
        do {
            try {
                System.out.println("~~~ Tipos de jugadores ~~~\n"
                        + "1. Jugador humano\n"
                        + "2. Computadora aleatoria\n"
                        + "3. Computadora inteligente\n");
                System.out.print("Elija el tipo de jugador para Player 1: ");
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nIngreso erróneo.\n");
            }
        } while (option < 1 || option > 3);

        switch (option) {
            case 1:
                System.out.print("Ingrese el nombre de Player 1: ");
                String nombre = sc.nextLine();
                player1 = new HumanPlayer(CHAR_1, nombre, sc);
                break;
            case 2:
                player1 = new RandomComputerPlayer(CHAR_1);
                break;
            case 3:
                player1 = new SmartComputerPlayer(CHAR_1);
                break;
        }

        do {
            try {
                System.out.println("~~~ Tipos de jugadores ~~~\n"
                        + "1. Jugador humano\n"
                        + "2. Computadora aleatoria\n"
                        + "3. Computadora inteligente\n");
                System.out.print("Elija el tipo de jugador para Player 2: ");
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nIngreso erróneo.\n");
            }
        } while (option < 1 || option > 3);

        switch (option) {
            case 1:
                System.out.print("Ingrese el nombre de Player 2: ");
                String nombre = sc.nextLine();
                player2 = new HumanPlayer(CHAR_2, nombre, sc);
                break;
            case 2:
                player2 = new RandomComputerPlayer(CHAR_2);
                break;
            case 3:
                player2 = new SmartComputerPlayer(CHAR_2);
                break;
        }

        //for (int k = 0; k < 1000; k++) {
        board = new Board();

        System.out.println("Las posiciones de juego son: ");
        board.printPositions();
        System.out.println();

        boolean first = (Math.random() * 2) < 1;
        int position = -1;

        while (true) {
            //Player 1
            if (first) {
                do {
                    System.out.print("1. Es el turno de " + player1.toString() + ". Ingrese una posición: ");
                    position = player1.move(board.toString());
                    if (!(player1 instanceof HumanPlayer)) {
                        System.out.println(position + 1);
                    }

                } while (position < 0 || position > 8);

                board.setPosition(player1.getPlayerLetter(), position);
                board.printBoard();

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                if (checkEndOfGame(board.toString(), player1)) {
                    break;
                }
            }

            first = true;

            //Player 2
            do {
                System.out.print("2. Es el turno de " + player2.toString() + ". Ingrese una posición: ");
                position = player2.move(board.toString());
                if (!(player2 instanceof HumanPlayer)) {
                    System.out.println(position + 1);
                }
            } while (position < 0 || position > 8);

            board.setPosition(player2.getPlayerLetter(), position);
            board.printBoard();

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            if (checkEndOfGame(board.toString(), player2)) {
                break;
            }

        }
        //}

//        System.out.println("\n1. " + player1.toString() + " (X) ha ganado " + c + " de las 1000 veces jugadas contra " + player2.toString() + " y ha perdido " + d + " veces.");
//        System.out.println("Empataron " + (1000-c-d) + " veces.");
    }

    private static boolean checkEndOfGame(String board, Player player) {
        if (checkWinner(board, player.getPlayerLetter())) {
            System.out.println("¡Felicidades " + player.toString() + ", has ganado!");
            if (player.getPlayerLetter() == 'X') {
                c++;
            } else {
                d++;
            }
            return true;
        }
        if (!board.contains("_")) {
            System.out.println("¡Hay un empate!");
            return true;
        }
        return false;
    }

    private static boolean checkWinner(String board, char letter) {

        //Check each row
        for (int i = 0; i < 9; i += 3) {
            if (board.charAt(i) == letter
                    && board.charAt(i + 1) == letter
                    && board.charAt(i + 2) == letter) {
                return true;
            }
        }

        //Check each column
        for (int i = 0; i < 3; i++) {
            if (board.charAt(i) == letter
                    && board.charAt(i + 3) == letter
                    && board.charAt(i + 6) == letter) {
                return true;
            }
        }

        if (board.charAt(0) == letter
                && board.charAt(4) == letter
                && board.charAt(8) == letter) {
            return true;
        }

        return board.charAt(2) == letter
                && board.charAt(4) == letter
                && board.charAt(6) == letter;
    }

}
