package ib.vine.ethan;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SortedArraysWithoutMe {

    int[] inputs1 = new int[]{3, 4, 5, 4};
    int[] inputs2 = new int[]{4, 5, 2, 3, 4};

    int[] inputs3 = new int[]{4, 5, 2, 4, 5};
    int[] inputs4 = new int[]{3, 5, 3, 4, 5};

    int[] inputs5 = new int[]{3, 5, 3, 5, 5};
    int[] inputs6 = new int[]{3, 5, 4, 3, 5};

    int[] inputsAll = new int[]{1,2,3,4,5,6};


    @Test
    public void test() {
        assertThat(cutDowns(inputs1)).isEqualTo(2);
        assertThat(cutDowns(inputs2)).isEqualTo(0);
        assertThat(cutDowns(inputs3)).isEqualTo(0);
        assertThat(cutDowns(inputs4)).isEqualTo(1);
        assertThat(cutDowns(inputs5)).isEqualTo(2);
        assertThat(cutDowns(inputs6)).isEqualTo(0);
        assertThat(cutDowns(inputsAll)).isEqualTo(6);
    }


    private int cutDowns(int[] arrays) {
        if(isSorted(arrays))
            return arrays.length;

        int currentValue = Integer.MIN_VALUE;
        int previousValue = Integer.MIN_VALUE;
        int nextValue = Integer.MAX_VALUE;

        int failCount = 0;
        int cutDowns = 0;

        for(int i = 0;i< arrays.length;i++) {
            if(failCount > 1) {
                break;
            }
            currentValue = arrays[i];
            if (i < arrays.length - 1) {
                nextValue = arrays[i + 1];
            } else {
                nextValue = Integer.MAX_VALUE;
            }

            if (i > 0) {
                previousValue = arrays[i - 1];
            }

            if(currentValue < previousValue || currentValue > nextValue) {
                if(isSortedWithoutCurrentValue(arrays, i)) {
                    cutDowns++;
                } else {
                    failCount++;
                }
            }
        }

        if(failCount > 1) {
            return 0;
        }
        return cutDowns;
    }

    private boolean isSortedWithoutCurrentValue(int[] arrays, int index) {

        int[] copied = new int[arrays.length -1];

        if(index == 0) {
            System.arraycopy(arrays, 1, copied, 0, arrays.length -1);
        } else if(index == arrays.length -1) {
            System.arraycopy(arrays, 0, copied, 0, arrays.length -1);
        } else {
            System.arraycopy(arrays, 0, copied, 0, index + 1);
            System.arraycopy(arrays, index +1, copied, index, arrays.length -index -1);
        }

        return isSorted(copied);
    }


    private boolean isSorted(int[] arrays) {
        boolean sorted = true;
        int[] sortedArrays = new int[arrays.length];
        System.arraycopy(arrays, 0, sortedArrays, 0, arrays.length);
        Arrays.sort(sortedArrays);

        for(int i=0; i< arrays.length;i++) {
            if(arrays[i] != sortedArrays[i]) {
                sorted = false;
                break;
            }
        }

        return sorted;
    }
}
