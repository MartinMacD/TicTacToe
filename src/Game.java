import java.util.*;

public class Game {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setPieceType("x");
        player2.setPieceType("o");
        System.out.println("Player 1, what is your name?");
        player1.setPlayerName(kb.next());

        System.out.println("Player 2 what is your name?");
        player2.setPlayerName(kb.next());

        while (true) {
            GameBoard gameBoard = new GameBoard();
            while (true) {
                gameBoard.displayBoard();

                int column = gameBoard.playerMakeMoveColumn(player1, player2);

                int row = gameBoard.playerMakeMoveRow(player1, player2);



                if (gameBoard.checkValidSpace(row, column)) {
                    if (gameBoard.whoseTurn().equals("Player 1")) {
                        gameBoard.setGameBoard(row, column, player1.getPieceType());
                    } else {
                        gameBoard.setGameBoard(row, column, player2.getPieceType());
                    }
                } else {
                    continue;
                }

                if (gameBoard.whoseTurn().equals("Player 1")) {
                    if (gameBoard.checkWin(gameBoard.getGameBoard(), player1.getPieceType())) {
                        System.out.println("Good job " + player1.getPlayerName() + ", you have WON the game!");
                        break;
                    }
                } else {
                    if (gameBoard.checkWin(gameBoard.getGameBoard(), player2.getPieceType())) {
                        System.out.println("Good job " + player2.getPlayerName() + ", you have WON the game!");
                        break;
                    }
                }

                if (gameBoard.checkIfDraw(gameBoard.getGameBoard())) {
                    System.out.println("The game is a DRAW");
                    break;
                }

                gameBoard.increaseTurnTracker();
            }
            gameBoard.displayBoard();
            System.out.println("Do you want to play another game?");
            String restart = kb.next();

            if (!(restart.equalsIgnoreCase("yes") || restart.equalsIgnoreCase("y"))) {
                break;
            }
        }

    }

}
