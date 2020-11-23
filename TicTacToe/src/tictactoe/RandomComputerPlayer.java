package tictactoe;

/**
 *
 * @author Josué Fernández
 */
public class RandomComputerPlayer extends Player {
    
    public RandomComputerPlayer(char letter) {
        super(letter);
    }

    @Override
    public int move(String board) {
        int index = (int)(Math.random() * numAvailableSpaces(board));
        return availableSpaces(board)[index];
    }

    @Override
    public String toString() {
        return "RandomComputerPlayer";
    }
    
}
