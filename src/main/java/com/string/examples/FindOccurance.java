package com.string.examples;

public class FindOccurance {

    void findOccurance(String str, String substringToFind) {
        int start = 0, count=0;
        while (true) {
            int found = str.indexOf(substringToFind, start);
            if (found != -1) {
                count++;
            }
            if (found == -1) break;
            start = found + 2; // move start up for next iteration
        }
    }
}
