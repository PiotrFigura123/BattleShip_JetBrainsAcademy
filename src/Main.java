import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Battleship player1 = new Battleship("1");
        promptEnterKey();
        Battleship player2 = new Battleship("2");
        promptEnterKey();
        int player = 0;
        String shotPosition = "";
        int endOfGame = 0;
        int endOfGame2 = 0;

        while (endOfGame < 17 && endOfGame2 < 17) {
            if (player == 0) {
                player1.displayBoard(player1.gameBoard);
                System.out.println("---------------------");
                player1.displayBoard(player1.board);
                System.out.println("\nPlayer 1, it's your turn!\n");
                shotPosition = sc.nextLine();
                if (player2.checkIfPossitionShotCorrect(shotPosition)) {
                    char mark = player2.checkIfScoreOrMissedFunction(endOfGame, shotPosition);
                    player1.updateGameBoardAccordingMarkAndPosition(mark, shotPosition);
                    player2.updateBoardAccordingMarkAndPosition(mark, shotPosition);
                    if (mark == 'X')
                        endOfGame++;
                }
            } else {
                player2.displayBoard(player2.gameBoard);
                System.out.println("---------------------");
                player2.displayBoard(player2.board);
                System.out.println("\nPlayer 2, it's your turn!\n");
                shotPosition = sc.nextLine().toUpperCase();
                if (player1.checkIfPossitionShotCorrect(shotPosition)) {
                    char mark = player1.checkIfScoreOrMissedFunction(endOfGame2, shotPosition);
                    player2.updateGameBoardAccordingMarkAndPosition(mark, shotPosition);
                    player1.updateBoardAccordingMarkAndPosition(mark, shotPosition);
                    if (mark == 'X')
                        endOfGame2++;
                }
            }
            promptEnterKey();
            player++;
            player = player % 2;

        }
        sc.close();
    }
    public static void promptEnterKey() {

        System.out.println("Press Enter and pass the move to another player\n ...");
        Scanner sc = new Scanner(System.in);
            sc.nextLine();

    }
}