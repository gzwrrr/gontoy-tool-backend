package com.gontoy.common;


import java.util.Arrays;

public class StringUtils {

    /**
     * 移除字符串中的多余空格
     */
    public char[] removeEmpty(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < len; fast++) {
            if (arr[fast] != ' ') {
                if (slow != 0) {
                    arr[slow++] = ' ';
                }
                while (fast < len && arr[fast] != ' ') {
                    arr[slow++] = arr[fast++];
                }
            }
        }
        return Arrays.copyOf(arr, slow);
    }
}
