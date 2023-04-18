package tests;

import java.util.*;

public class Solution {

    public static int[] result = {-1, -1, -1, -1};
    public static int[] digits = {-1, -1, -1, -1};
    public static int nextDigitIndex;
    public static int previousDigitChecked;
    public static ArrayList<Integer> digitsLeft = new ArrayList<>();
    public static int attempts;
    public static int attempts2;

    public static void main(String[] args) {
        // test part, you can choose any 4 different digits
        int playersAttempts = 0;
        int matches = -1;
        int[] code = new int[]{9, 7, 4, 5};
        int[] answer = new int[4];
        answer = tryToGuess(matches);
        while (!Arrays.equals(answer, code) && playersAttempts < 17) {
            matches = 0;
            for (int l = 0; l < 4; l++)
                if (answer[l] == code[l])
                    matches++;
            if (matches < 4) {
                playersAttempts++;
                answer = tryToGuess(matches);
            }
        }
        System.out.println("Count of attempts: " + playersAttempts);
        playersAttempts = 0;
        matches = -1;
        code = new int[]{0, 1, 2, 8};
        answer = new int[4];
        answer = tryToGuess(matches);
        while (!Arrays.equals(answer, code) && playersAttempts < 17) {
            matches = 0;
            for (int l = 0; l < 4; l++)
                if (answer[l] == code[l])
                    matches++;
            if (matches < 4) {
                playersAttempts++;
                answer = tryToGuess(matches);
            }
        }
        System.out.println("Count of attempts: " + playersAttempts);
    }

    static int[] tryToGuess(int matches) {
        // reset globals which should be reset at first attempt
        if (matches == -1) {
            result = new int[]{-1, -1, -1, -1};
            digits = new int[]{-1, -1, -1, -1};
            nextDigitIndex = 0;
            digitsLeft = new ArrayList<>();
            attempts = 0;
            attempts2 = 0;
        }
        // look for four digits used in code
        if (digits[3] == -1) {
            if (matches == 1 && nextDigitIndex < 4) {
                digits[nextDigitIndex] = previousDigitChecked;
                nextDigitIndex++;
            }
            for (int i = 0; i < 4; i++) {
                result[i]++;
            }
            if (nextDigitIndex == 3 && result[0] == 9) {
                digits[nextDigitIndex] = 9;
            } else if (nextDigitIndex == 2 && result[0] == 8) {
                digits[nextDigitIndex] = 8;
                digits[nextDigitIndex + 1] = 9;
            } else if (nextDigitIndex == 1 && result[0] == 7) {
                digits[nextDigitIndex] = 7;
                digits[nextDigitIndex + 1] = 8;
                digits[nextDigitIndex + 2] = 9;
            } else {
                previousDigitChecked = result[0];
                return result;
            }
        }
        // from here looking places for our 4 digits
        if (attempts == 0) {
            System.out.println("This digits are in code: " + Arrays.toString(digits));
            nextDigitIndex = 0;
            result = new int[]{digits[attempts], -1, -1, -1};
            System.out.println("First try: " + attempts + " " + Arrays.toString(result));
            attempts++;
            return result;
        }
        if (matches == 0) {
            if (attempts == 3) {
                result = new int[]{digits[attempts], digits[0], -1, -1};
            } else {
                result = new int[]{digits[attempts], -1, -1, -1};
            }
            System.out.println("No matches yet: " + attempts + " " + Arrays.toString(result));
            attempts++;
            return result;
        } else if (matches == 1) {
            if (attempts2 == 0) {
                for (int d :
                        digits) {
                    if (d != result[0]) {
                        digitsLeft.add(d);
                    }
                }
            }
            if (attempts2 == digitsLeft.size() - 1) {
                result = new int[]{result[0], digitsLeft.get(attempts2), digitsLeft.get(0), -1};
            } else {
                if (result[1] == digitsLeft.get(0) && attempts2 == 0) {
                    attempts2++;
                }
                result = new int[]{result[0], digitsLeft.get(attempts2), -1, -1};
            }
            System.out.println("1 match: " + Arrays.toString(result));
            attempts2++;
            return result;
        } else if (matches == 2) {
            digitsLeft = new ArrayList<>();
            for (int d :
                    digits) {
                if (d != result[0] && d != result[1]) {
                    digitsLeft.add(d);
                }
            }
            if (result[2] == -1) {
                result = new int[]{result[0], result[1], digitsLeft.get(0), digitsLeft.get(1)};
            } else {
                result = new int[]{result[0], result[1], digitsLeft.get(1), digitsLeft.get(0)};
            }
            System.out.println("2+ matches: " + Arrays.toString(result));
            return result;
        } else if (matches == 3) {
            for (int d :
                    digits) {
                if (d != result[0] && d != result[1] && d != result[2]) {
                    result = new int[]{result[0], result[1], result[2], d};
                    System.out.println("last try: " + Arrays.toString(result));
                    return result;
                }
            }
        }
        System.out.println("default out");
        return result;
    }
}