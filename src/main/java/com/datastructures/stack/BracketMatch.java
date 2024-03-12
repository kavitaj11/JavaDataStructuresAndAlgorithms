package com.datastructures.stack;

import java.util.Stack;


public class BracketMatch {

  public static boolean doParenthesisMatch(String str) {
    Stack stack = new Stack<Character>();
    char c;
    for (int i=0; i < str.length(); i++) {
      c = str.charAt(i);

      if (c == '(' || c == '{') {
        stack.push(c);
      }
      else if (c == '}') {
        if (stack.empty()) {
          return false;
        } else if (stack.peek().equals('{')) {
          stack.pop();
        }
      }
      else if (c == ')') {
        if (stack.empty()) {
          return false;
        } else if (stack.peek().equals('(')) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.empty();
  }

  public static void main(String[] args) {
    System.out.println(doParenthesisMatch(""));
    System.out.println(doParenthesisMatch("()"));
    System.out.println(doParenthesisMatch("{}"));
    System.out.println(doParenthesisMatch("{()}"));
    System.out.println(doParenthesisMatch("{123}"));
    System.out.println(doParenthesisMatch("{)")); // failure
    System.out.println(doParenthesisMatch("{((((((((()")); // failure
  }
}