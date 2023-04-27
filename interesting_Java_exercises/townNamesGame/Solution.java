import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
    public static void main(String[] args) throws IOException {
        // just some code for method correctness check
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(filename));
        StringBuilder allFile = new StringBuilder();
        while (reader.ready()) {
            allFile.append(reader.readLine()).append(" ");
        }
        reader.close();
        String result = getLine(allFile.toString().split(" "));
        System.out.println(result.toString());
    }

    public static String getLine(String... words) {
        //no need to continue if "words" is empty
        if (words.length == 0) {
            return "";
        }
        //it needs 2 lists
        List<String> wordsList = new ArrayList<>(Arrays.asList(words)); //this one is for unsorted words, it will become smaller during method work
        List<String> result = new ArrayList<>(); //this one is for sorted words. Method return String contains from its values.
        result.add(wordsList.get(0)); // just taking first from "wordsList" directly to first "result"
        wordsList.remove(0); // and removing it from "wordsList"
        int i = 0; //this counter serves to look through "wordsList"
        while (wordsList.size() > 0) { // doing this loop while there is any word left in wordsList
            String currentWord = wordsList.get(i);
            String firstResultWord = result.get(0);
            String lastResultWord = result.get(result.size() - 1);
            // compare the first letter of the current word in wordsList with the last letter of the last word in result
            if (currentWord.toLowerCase().charAt(0)
                    == lastResultWord.toLowerCase().charAt(lastResultWord.length() - 1)) {
                result.add(currentWord);
                wordsList.remove(i);
                i = 0;
            } else if (currentWord.toLowerCase().charAt(currentWord.length() - 1) // compare the last letter of the current word in wordsList with the first letter of the first word in result
                    == firstResultWord.toLowerCase().charAt(0)) {
                result.add(0, currentWord);
                wordsList.remove(i);
                i = 0;
            } else if (i == wordsList.size() - 1) { // if the loop has already gone through all the values of wordsList and has not added anything, then we move the last element of result to the first place, shifting all other elements and repeating the search
                result.add(0, result.get(result.size() - 1));
                result.remove(result.size() - 1);
                i = 0;
            } else i++;
        }
        /* at this moment wordsList surely empty, but it's very impotent to check if all values are on the right place
        for this purpose there is another loop which loops through the list, comparing the first letter of the first word
        and the last letter of the last word. If it encounters an inequality, then the loop breaks.
        This means that the list is directed and strictly has a first and last word.
        If not, then just at the end it returns the original state.
         */
        for (int j = 0; j < result.size() - 1; j++) {
            String first = result.get(0);
            String last = result.get(result.size() - 1);
            if (first.toLowerCase().charAt(0) != last.toLowerCase().charAt(last.length() - 1)) {
                break;
            }
            result.add(0, last);
            result.remove(result.size() - 1);
        }
        return result.toString().replaceAll("[,\\]\\[]", "");
    }
}
