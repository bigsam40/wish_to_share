package tests;

import java.util.Arrays;
import java.util.Random;
/**
 * you can use it shuffle array in "random" order
 */
public class Shuffler {

    public static void shuffle(int[] arr) {
        Random rnd = new Random(System.nanoTime());
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, rnd.nextInt(arr.length));
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static class Test {
        public static void main(String[] args) {
            int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
            for (int i = 0; i < a.length; i++) {
                Shuffler.shuffle(a);
                System.out.println(Arrays.toString(a));
            }

        }
    }
}