import java.util.Scanner;
public class GameBoard {
    private String[][] gameBoard;
    private int turnTracker = 1;
    Scanner kb = new Scanner(System.in);
    public GameBoard() {
        gameBoard = new String[3][3];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = " ";
            }
        }
    }

    /**
     return game board
     @return the current board state
     **/
    public String[][] getGameBoard(){
        return gameBoard;
    }

    /**
     Sets game board to inputted parameters
     @param i First position entered
     @param j Second position entered
     @param str Value entered
     **/
    public void setGameBoard(int i, int j, String str) {
        gameBoard[i][j] = str;
    }

    /**
     return Turn tracker
     @return the current turn
     **/
    public int getTurnTracker() {
        return turnTracker;
    }

    /**
     Increase turn tracker by 1
     **/
    public void increaseTurnTracker(){
        turnTracker++;
    }

    /**
     Displays current board state with formatting
     **/
    public void displayBoard(){
        System.out.print(" -------------");
        System.out.println();
        for (int i = 0; i < gameBoard.length; ++i) {
            for(int j = 0; j < gameBoard[i].length; ++j) {
                System.out.print(" │ ");
                System.out.print(gameBoard[i][j]);
            }
            System.out.print(" │");
            System.out.println();
            System.out.print(" -------------");
            System.out.println();
        }
    }

    /**
     Returns players row input depending on which players turn it is, also checks if input is a number.
     @param player1
     @param player2
     @returns Players input minus 1
     **/
    public int playerMakeMoveRow(Player player1, Player player2){
        if(whoseTurn().equals("Player 1")){
            System.out.println(player1.getPlayerName() + " It is your turn, choose which ROW you want");
        }else{
            System.out.println(player2.getPlayerName() + " It is your turn, choose which ROW you want");
        }

        while(true){
            if(kb.hasNextInt()){
                return kb.nextInt() - 1;
            }else{
                String word = kb.next();
                System.out.println(word + " is not a valid input, please re-enter the NUMBER you want for the ROW");
                continue;
            }
        }
    }
    /**
     Returns players column input depending on which players turn it is, also checks if input is a number.
     @param player1
     @param player2
     @returns Players input minus 1
     **/
    public int playerMakeMoveColumn(Player player1, Player player2){
        if(whoseTurn().equals("Player 1")){
            System.out.println(player1.getPlayerName() + ", choose which COLUMN you want");
        }else{
            System.out.println(player2.getPlayerName() + ", choose which COLUMN you want");
        }

        while(true){
            if(kb.hasNextInt()){
                return kb.nextInt() - 1;
            }else{
                String word = kb.next();
                System.out.println(word + " is not a valid input, please re-enter the NUMBER you want for the COLUMN");
                continue;
            }
        }
    }

    /**
     Returns which players turn it is
     @returns The player whose turn it is
     **/
    public String whoseTurn(){
        if(getTurnTracker() % 2 == 0){
            return "Player 2";
        }else{
            return "Player 1";
        }
    }
    /**
     Checks if space selected by user is valid
     @param column user selected
     @param row user selected
     @returns True if space is valid, false if space is not valid
     **/
    public boolean checkValidSpace(int row, int column){
        if(!(column >= 0 && column <= 2 && row >= 0 && row<=2)) {
            System.out.println("The number you enter must be 1, 2 or 3");
            return false;
        }

        if(gameBoard[row][column].equals(" ")){
            return true;
        }else{
            System.out.println("Sorry, that's not a valid space, try again.");
        }

        return false;
    }

    /**
     Checks multiple methods to see if there is a winner
     @param gameBoard that game is being played on
     @param pieceType of current player
     @returns true if player has won and false if player has not won
     **/
    public boolean checkWin(String[][] gameBoard, String pieceType){
        if(checkIfRowWin(gameBoard, pieceType) || checkIfColumnWin(gameBoard,pieceType) || checkDiagonalWin(gameBoard, pieceType)){
            return true;
        }
        return false;
    }

    /**
     Returns if the game is a draw or not
     @param gameBoard that game is being played on
     @returns True if the game is a draw and false if the game is not a draw
     **/
    public boolean checkIfDraw(String[][] gameBoard){
        int counter = 9;
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                if(gameBoard[i][j] != " "){
                    counter--;
                }
            }
        }
        if(counter == 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     Returns true if there is a row win or not
     @param gameBoard that game is being played on
     @param pieceType of current player
     @returns True if there is a row win and false if there is not a row win
     **/
    public boolean checkIfRowWin(String[][] gameBoard, String pieceType){

        for(int i = 0; i < gameBoard.length; i++){
            int counter = 3;
            for(int j = 0; j < gameBoard[i].length; j++){
                if(gameBoard[i][j].equals(pieceType)){
                    counter--;
                }else{
                    break;
                }
            }
            if(counter == 0){
                return true;
            }
        }
        return false;
    }

    /**
     Returns true if there is a column win or not
     @param gameBoard that game is being played on
     @param pieceType of current player
     @returns True if there is a column win and false if there is not a column win
     **/
    public boolean checkIfColumnWin(String[][] gameBoard, String pieceType){

        for(int i = 0; i < gameBoard.length; i++){
            int counter = 3;
            for(int j = 0; j < gameBoard[i].length; j++){
                if(gameBoard[j][i].equals(pieceType)){
                    counter--;
                }else{
                    break;
                }
            }
            if(counter == 0){
                return true;
            }
        }
        return false;
    }
    /**
     Returns true if there is a diagonal win or not
     @param gameBoard that game is being played on
     @param pieceType of current player
     @returns True if there is a diagonal win and false if there is not a diagonal        win
     **/
    public boolean checkDiagonalWin(String[][] gameBoard, String pieceType){
        if(gameBoard[0][0].equals(pieceType) && gameBoard[1][1].equals(pieceType) && gameBoard[2][2].equals(pieceType)){
            return true;
        }
        if(gameBoard[0][2].equals(pieceType) && gameBoard[1][1].equals(pieceType) && gameBoard[2][0].equals(pieceType)){
            return true;
        }
        return false;
    }

}
