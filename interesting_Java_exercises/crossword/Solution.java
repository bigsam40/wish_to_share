import java.util.ArrayList;
import java.util.List;

/* 
crossword
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'x', 'a', 'f', 'o', 'o', 'e'},
                {'h', 'a', 'a', 'o', 'e', 'e'},
                {'n', 'i', 'i', 'h', 'u', 'u'},
                {'b', 'g', 's', 'w', 'r', 'e'},
                {'s', 's', 'l', 'w', 'b', 'r'}
        };
        List<Word> wordList = detectAllWords(crossword, "haa", "hoo", "hee", "huu", "hrr", "hww", "hss", "hii");
        for (Word word :
                wordList) {
            System.out.println(word);
        }
        /*
the expecting result
haa - (0, 1) - (2, 1)
hoo - (3, 2) - (3, 0)
hee - (3, 2) - (5, 0)
huu - (3, 2) - (5, 2)
hrr - (3, 2) - (5, 4)
hww - (3, 2) - (3, 4)
hss - (3, 2) - (1, 4)
hii - (3, 2) - (1, 2)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        // searching for the FIRST occurrence of each word
        for (String word :
                words) {
            wordList.add(detectSingleWord(crossword, word));
        }
        return wordList;
    }

    public static Word detectSingleWord(int[][] crossword, String word) {
        Word resultWord = new Word(word);
        // look through array for first letter of String word
        for (int y = 0; y < crossword.length; y++) {
            for (int x = 0; x < crossword[y].length; x++) {
                if ((char) crossword[y][x] == word.charAt(0)) {
                    resultWord.setStartPoint(x, y);
                    // if first letter has been found - need to check all directions, this is the best i got xD
                    if (checkForWholeWord(crossword, resultWord, 1, 0)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, 0, 1)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, 1, 1)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, -1, 0)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, 0, -1)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, -1, -1)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, -1, 1)) {
                        return resultWord;
                    }
                    if (checkForWholeWord(crossword, resultWord, 1, -1)) {
                        return resultWord;
                    }
                }
            }
        }
        return resultWord;
    }

    public static boolean checkForWholeWord(int[][] crossword, Word word, int vectorX, int vectorY) {
        String wordString = word.getText();
        int currentX = word.getStartX();
        int currentY = word.getStartY();
        StringBuilder sb = new StringBuilder();
        // collect whole word with given vector
        for (int i = 0; i < wordString.length(); i++) {
            try {
                sb.append((char) crossword[currentY][currentX]);
                word.setEndPoint(currentX, currentY);
                currentX += vectorX;
                currentY += vectorY;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        // if collected word is equal to the word we are looking for return true
        if (wordString.equals(sb.toString())) {
            return true;
        } else {
            // if not, we reset endPoint to the default
            word.setEndPoint(0, 0);
            return false;
        }
    }

    public static class Word {
        private final String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        public String getText() {
            return text;
        }

        public int getStartX() {
            return startX;
        }

        public int getStartY() {
            return startY;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
