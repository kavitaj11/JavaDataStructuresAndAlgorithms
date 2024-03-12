package com.string.examples;

//Time complexity #
//We traverse over the string in O(n) time, and
// In the worst case, the whole string of size n can be the palindrome.
// Therefore, the time complexity will be O(n^2)

//Space complexity #
//The space complexity will be O(1) because no additional space is utilized.

class LargestPalindromeLength {

    public static String locateProtein(String s) {
        if (s == null || s.length() < 1) 
            return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {

            int len1 = returnPalindromeLength(s, i, i); // for odd length
            int len2 = returnPalindromeLength(s, i, i + 1); // for even length
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // returns size of the current/latest palindrome
    private static int returnPalindromeLength(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        return right - left - 1;
    }

    public static void main( String args[] ) {
        // Driver code
        String sequence = "aaccbababcbc";
        
        System.out.println(locateProtein(sequence));
    }
}