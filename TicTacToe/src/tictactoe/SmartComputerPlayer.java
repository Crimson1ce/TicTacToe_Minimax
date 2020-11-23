package tictactoe;

/**
 *
 * @author Josué Fernández
 */
public class SmartComputerPlayer extends Player {

    int nextMove;
    
    public SmartComputerPlayer(char letter) {
        super(letter);
        this.nextMove = -1;
    }

    @Override
    public int move(String board) {
        
        if(numAvailableSpaces(board) == 9){
            return ((int)(Math.random() * 5) * 2);
        }
        minimax(board, true);
        int retValue = this.nextMove; // Next move was modified by minimax
        this.nextMove = -1; //We modify the variable to be safe
        return retValue;
    }

    public boolean checkWinner(String board, char letter) {

        for (int i = 0; i < 9; i += 3) {
            if (board.charAt(i) == letter
                    && board.charAt(i + 1) == letter
                    && board.charAt(i + 2) == letter) {
                return true;
            }
        }

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

    private int minimax(String board, boolean isMaximizing) {
        
        int spaces = numAvailableSpaces(board);

        //Base cases of recursion
        if(checkWinner(board, getPlayerLetter())){
            return spaces + 1;
        }
        if(checkWinner(board, getOpponentLetter())){
            return -1*(spaces + 1);
        }
        if (spaces == 0) {
            return 0;
        }
        
        //Casos recursivos
        if (isMaximizing) {
            
            int max = Integer.MIN_VALUE;
            int next = -1;
            
            for (int possibleMove : availableSpaces(board)) {
                String boardCopy = board.substring(0, possibleMove) 
                        + getPlayerLetter() 
                        + board.substring(possibleMove + 1);
//                System.out.println(boardCopy);
                int score = minimax(boardCopy, false);
                if(score > max){
                    max = score;
                    next = possibleMove;
                }
            }
            
            this.nextMove = next;
            return max;
            
        } else {
            
            int min = Integer.MAX_VALUE;
            int next = -1;
            
            for (int possibleMove : availableSpaces(board)) {
                String boardCopy = board.substring(0, possibleMove) 
                        + getOpponentLetter() 
                        + board.substring(possibleMove + 1);
//                System.out.println(boardCopy);
                int score = minimax(boardCopy, true);
                if(score < min){
                    min = score;
                    next = possibleMove;
                }
            }
            this.nextMove = next;
            return min;
            
        }

    }

    @Override
    public String toString() {
        return "SmartComputerPlayer";
    }

}
