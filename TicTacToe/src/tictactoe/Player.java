package tictactoe;

/**
 *
 * @author Josué Fernández
 */
public abstract class Player {

    private final char playerLetter;
    private final char opponentLetter;

    public Player(char letter) {
        playerLetter = letter;
        if(letter == 'X'){
            opponentLetter = '0';
        } else {
            opponentLetter = 'X';
        }
    }

    public char getPlayerLetter() {
        return playerLetter;
    }

    public char getOpponentLetter() {
        return opponentLetter;
    }
    
    public int numAvailableSpaces(String board) {
        int availableSpaces = 0;
        for (int i = 0; i < board.length(); ++i) {
            if (board.charAt(i) == '_') {
                availableSpaces++;
            }
        }
        return availableSpaces;
    }
    
    public int[] availableSpaces(String board) {
        int num = numAvailableSpaces(board);
        if (num > 0) {
            int[] spaces = new int[num];
            int position=0;
            for (int i = 0; i < board.length(); ++i) {
                if (board.charAt(i) == '_') {
                    spaces[position++] = i;
                }
            }
            return spaces;
        }
        return null;
    }
    
    public abstract int move(String board);
}
