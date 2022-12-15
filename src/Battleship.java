import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Battleship {
    int gameBoardLength = 10;
    char water = '~';
    char ship = 'O';
    char score = 'X';
    char missed = 'M';
    char[][] board = new char[gameBoardLength][gameBoardLength];
    char[][] gameBoard = new char[gameBoardLength][gameBoardLength];
    List<String> forbidenAreas = new ArrayList<String>();
    List<String> alreadyHited = new ArrayList<String>();
    HashMap<Integer, String> intigerToString = new HashMap<Integer, String>();

    HashMap<Character, Integer> stringToInteger = new HashMap<Character, Integer>();

    Integer[] shipsLength = {5, 4, 3, 3, 2};

    String[] shipsName = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};

    ArrayList<Ship> ships = new ArrayList<>();


    Battleship(String name) {

        for (int i = 0; i < gameBoardLength; i++) {

            for (int j = 0; j < gameBoardLength; j++) {

                gameBoard[i][j] = water;

                board[i][j] = water;

            }

        }

        intigerToString.put(0, "A");

        intigerToString.put(1, "B");

        intigerToString.put(2, "C");

        intigerToString.put(3, "D");

        intigerToString.put(4, "E");

        intigerToString.put(5, "F");

        intigerToString.put(6, "G");

        intigerToString.put(7, "H");

        intigerToString.put(8, "I");

        intigerToString.put(9, "J");

        stringToInteger.put('A', 0);

        stringToInteger.put('B', 1);

        stringToInteger.put('C', 2);

        stringToInteger.put('D', 3);

        stringToInteger.put('E', 4);

        stringToInteger.put('F', 5);

        stringToInteger.put('G', 6);

        stringToInteger.put('H', 7);

        stringToInteger.put('I', 8);

        stringToInteger.put('J', 9);

        displayBoard(board);

        System.out.println("Player " + name + ", place your ships on the game field");

        setShipsOnBoard();

    }


    public void updateGameBoardAccordingMarkAndPosition(char mark, String shotPosition) {

        int firstLetter = stringToInteger.get(shotPosition.charAt(0));

        int firstNumber = Integer.parseInt(shotPosition.substring(1, shotPosition.length()));

        gameBoard[firstLetter][firstNumber - 1] = mark;

    }


    public void updateBoardAccordingMarkAndPosition(char mark, String shotPosition) {

        int firstLetter = stringToInteger.get(shotPosition.charAt(0));

        int firstNumber = Integer.parseInt(shotPosition.substring(1, shotPosition.length()));

        board[firstLetter][firstNumber - 1] = mark;

    }


    public char checkIfScoreOrMissedFunction(int endOfGame, String shot) {

        int firstLetter = stringToInteger.get(shot.charAt(0));

        int firstNumber = Integer.parseInt(shot.substring(1, shot.length()));

        boolean czyZatopiony = false;

        alreadyHited.add(shot.charAt(0) + String.valueOf(firstNumber));


        if (board[firstLetter][firstNumber - 1] == ship || board[firstLetter][firstNumber - 1] == score) {

            for (Ship ship : ships) {

                if (alreadyHited.containsAll(ship.pozycjeStatku)) {

                    czyZatopiony = true;

                    if (endOfGame == 16) {

                        System.out.println("\nYou sank the last ship. You won. Congratulations!");

                        return score;

                    }

                    System.out.println("\nYou sank a ship\n");

                    alreadyHited.remove(ship.pozycjeStatku);

                    return score;

                }

            }

            System.out.println("\nYou hit a ship!");

            return score;

        } else {

            System.out.println("\nYou missed!");

            return missed;

        }

    }


    void displayBoard(char[][] defaultBoard) {

        for (int i = 1; i <= 10; i++)

            System.out.print(" " + i);

        System.out.println();

        for (int i = 0; i < gameBoardLength; i++) {

            System.out.print(intigerToString.get(i) + " ");

            for (int j = 0; j < gameBoardLength; j++) {

                System.out.print(defaultBoard[i][j] + " ");


            }

            System.out.println();

        }


    }


    public boolean checkIfPossitionShotCorrect(String shotPosition) {
        char firstLetter = shotPosition.charAt(0);

        int firstNumber = Integer.parseInt(shotPosition.substring(1, shotPosition.length()));

        String position = firstLetter + String.valueOf(firstNumber);

        if (firstLetter > 'J' || firstNumber > 10 || alreadyHited.contains(position)) {

            System.out.println("Error! You entered the wrong coordinates! Try again:");

            return false;

        }

        return true;

    }


    void setShipsOnBoard() {

        String position = "";

        int miejsceSpacji;

        char firstLetter;

        int firstNumber;

        char secondLetter;

        int secondNumberLetter;

        Scanner scaner = new Scanner(System.in);

        for (int k = 0; k < 5; k++) {


            System.out.println("\nEnter the coordinates of the " + shipsName[k] + " (" + shipsLength[k] + " cells):\n");

            boolean correctPossition = false;

            boolean checkIfLengthCorrect = false;


            while (correctPossition == false || checkIfLengthCorrect == false) {
                Pattern pattern = Pattern.compile("\\w\\d\\d?\\s\\w\\d\\d?");
                position = scaner.nextLine();
                position = position.toUpperCase();
                Matcher matcher = pattern.matcher(position);
                System.out.println("> " + position);

                if (!matcher.matches()) {
                    System.out.println("Schemat [Literka][liczaba] [Literka][liczaba]\n liczba ->od 1 do 10 \n literka -> od A - J" );
                } else {
                    miejsceSpacji = position.indexOf(" ");
                    firstLetter = position.charAt(0);
                    firstNumber = Integer.parseInt(position.substring(1, miejsceSpacji));
                    secondLetter = position.charAt(miejsceSpacji + 1);
                    secondNumberLetter = Integer.parseInt(position.substring(miejsceSpacji + 2, position.length()));
                    correctPossition = checkIfPossitionCorrect(firstLetter, firstNumber, secondLetter, secondNumberLetter);
                    checkIfLengthCorrect = checkLengthOfShip(shipsLength[k], firstLetter, firstNumber, secondLetter, secondNumberLetter);

                }
            }
            miejsceSpacji = position.indexOf(" ");
            firstLetter = position.charAt(0);
            secondLetter = position.charAt(miejsceSpacji + 1);
            miejsceSpacji = position.indexOf(" ");
            int firstLetterNumber = stringToInteger.get(position.charAt(0));
            firstNumber = Integer.parseInt(position.substring(1, miejsceSpacji));
            int secondLetterNumber = stringToInteger.get(position.charAt(miejsceSpacji + 1));
            secondNumberLetter = Integer.parseInt(position.substring(miejsceSpacji + 2, position.length()));
            placeShipInBoard(firstLetterNumber, firstNumber, secondLetterNumber, secondNumberLetter, intigerToString);
            System.out.println();
            addForbidenAreas(firstLetter, firstNumber, secondLetter, secondNumberLetter);
            displayBoard(board);


        }

//        scaner.close();

    }


    public boolean checkIfPossitionCorrect(char firstLetter, int firstNumber, char secondLetter, int secondNumberLetter) {

        String pole1 = firstLetter + String.valueOf(firstNumber);

        String pole2 = secondLetter + String.valueOf(secondNumberLetter);

        if (firstLetter > 'J' || secondLetter > 'J' || firstNumber > 10 || secondNumberLetter > 10 || forbidenAreas.contains(pole1) || forbidenAreas.contains(pole2)) {

            System.out.println("Error! Wrong ship location! Try again:");

            return false;

        }

        return true;

    }


    public boolean checkLengthOfShip(int length, char firstLetter, int firstNumber, char secondLetter, int secondNumberLetter) {


        if (firstLetter == secondLetter && ((Math.abs(secondNumberLetter - firstNumber) + 1) == length)) {

            return true;

        } else if (firstNumber == secondNumberLetter && ((Math.abs(firstLetter - secondLetter) + 1) == length)) {

            return true;

        } else {

            System.out.println("Error! Wrong length of the Submarine! Try again:");

            return false;

        }


    }


    public void addForbidenAreas(char firstLetterNumber, int firstNumber, char secondLetterNumber,

                                 int secondNumberLetter) {


        if (firstLetterNumber > secondLetterNumber) {

            char tempNUmber = secondLetterNumber;

            secondLetterNumber = firstLetterNumber;

            firstLetterNumber = tempNUmber;


        }


        if (firstNumber > secondNumberLetter) {

            int tempNUmber = secondNumberLetter;

            secondNumberLetter = firstNumber;

            firstNumber = tempNUmber;


        }


        if (firstLetterNumber == secondLetterNumber) {

            for (int i = firstLetterNumber - 1; i <= firstLetterNumber + 1; i++) {

                for (int j = firstNumber - 1; j <= secondNumberLetter + 1; j++) {


                    String pole = (char) i + String.valueOf(j);

                    forbidenAreas.add(pole);

                }

            }


        } else if (firstNumber == secondNumberLetter) {

            for (int i = firstNumber - 1; i <= firstNumber + 1; i++) {

                for (int j = firstLetterNumber - 1; j <= secondLetterNumber + 1; j++) {


                    String pole = (char) j + String.valueOf(i);

                    forbidenAreas.add(pole);

                }

            }

        }


    }


    public void placeShipInBoard(int firstLetterNumber, int firstNumber, int secondLetterNumber,

                                 int secondNumberLetter, HashMap<Integer, String> intigerToString) {

        List<String> pozycjeStatku = new ArrayList<String>();

        String letter = "";

        if (firstLetterNumber > secondLetterNumber) {

            int tempNUmber = secondLetterNumber;

            secondLetterNumber = firstLetterNumber;

            firstLetterNumber = tempNUmber;


        }


        if (firstNumber > secondNumberLetter) {

            int tempNUmber = secondNumberLetter;

            secondNumberLetter = firstNumber;

            firstNumber = tempNUmber;


        }


        if (firstLetterNumber == secondLetterNumber) {

            for (int j = firstNumber; j <= secondNumberLetter; j++) {

                letter = intigerToString.get(firstLetterNumber);

                pozycjeStatku.add(letter + String.valueOf(j));

                board[firstLetterNumber][j - 1] = ship;

            }

        } else if (firstNumber == secondNumberLetter) {

            for (int j = firstLetterNumber; j <= secondLetterNumber; j++) {

                letter = intigerToString.get(j);

                pozycjeStatku.add(letter + String.valueOf(firstNumber));

                board[j][firstNumber - 1] = ship;

            }

        }


        ships.add(new Ship(pozycjeStatku));

    }

}
