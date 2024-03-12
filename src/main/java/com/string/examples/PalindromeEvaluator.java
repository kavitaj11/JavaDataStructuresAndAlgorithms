package com.string.examples;


public class PalindromeEvaluator {

    //Checking for palindromes in Java 7 or earlier
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }


    // Checking for palindromes using Java 8 streams
    public boolean isPalindromeUsingJava8Stream(String s) {
        String forward = s.toLowerCase().codePoints()
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }

   /* The codePoints method returns an IntStream, which can then be filtered using the condition 'isLetterOrDigit'.
    The arguments are:
            •	A Supplier, which produces the resulting reduced object,
             in this case a StringBuilder.
            •	A BiConsumer used to accumulate each element of the stream into the resulting data structure;
             this example uses the appendCodePoint method.
	        •   A BiConsumer representing a combiner, which is a “non-interfering, stateless function”
	        for combining two values that must be compatible with the accumulator;
	        in this case, the append method.
	        Note that the combiner is only used if the operation is done in parallel.
    That sounds like a lot, but the advantage in this case is that the code doesn’t have to make a distinction between characters and integers, which is often an issue when working with elements of strings.

*/


}


