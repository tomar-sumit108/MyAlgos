package myalgos;

import InputReader.InputReader;
import net.egork.io.OutputWriter;

public class NextPermutation {
    int[] nextPermutation(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return null;
        }

        int j = array.length - 1;

        while (array[j] <= array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;

        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return array;
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {



    }
}
