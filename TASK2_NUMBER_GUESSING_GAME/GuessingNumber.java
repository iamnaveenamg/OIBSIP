package TASK2_NUMBER_GUESSING_GAME;

import java.util.ArrayList;

class GuessingNumber{
    static ArrayList<Integer> scoreBoard = new ArrayList<Integer>();
    public static void main(String[] args) {
        NumberGuessingGame methodChange = new NumberGuessingGame();
        methodChange.menu(scoreBoard);
    }
}