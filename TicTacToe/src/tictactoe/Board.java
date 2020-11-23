package tictactoe;

/**
 *
 * @author Josué Fernández
 */
public class Board {

    char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    public void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + "\n");
        }
    }

    public void printPositions() {
        for (int i = 1; i <= 9; i += 3) {
            System.out.println(" " + i + " | " + (i + 1) + " | " + (i + 2) + "\n");
        }
    }
    
    public void setPosition(char letter, int position) {
        if (position >= 0 && position < 9) {
            board[position] = letter;
        }
    }

    @Override
    public String toString() {

        String retValue = "";

        for (int i = 0; i < 9; i++) {
            retValue += (board[i] == ' ') ? '_' : board[i];
        }

        return retValue;
    }
}
