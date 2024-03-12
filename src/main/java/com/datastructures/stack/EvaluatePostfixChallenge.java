package com.datastructures.stack;


/*

Infix and Postfix Expressions #
In the infix expression (the usual convention followed in mathematics),
 operators like + and * appear between the operands involved in the calculation:
6 + 3 * 8 - 4
Another convention is the postfix expression,
where the operators appear after the operands involved in the expression.
In postfix, the expression written above will become:
6 3 8 * + 4 -
The two operands preceding an operator will be used with that operator
From the first block of digits 6 3 8, we pick the last two, which are 3 and 8.
Reading the operators from left to right, the first one is *. The expression now becomes 3 * 8
The next number is 6 while the next operator is +, so we have 6 + 8 * 3.
The value of this expression is followed by 4, which is right before -. Hence we have 6 + 8 * 3 - 4.

Problem Statement #
In this problem, you have to implement the evaluatePostFix() method
that will compute a postfix expression given to it in a string.

We check each character of the string from left to right.
If we find a digit, it is pushed into the stack.
If we find an operand, we pop two elements from the stack
 (there have to be at least two present or else this postfix expression is incorrect)
 and solve the expression. The resulting value is pushed back into the stack.
The process continues until we reach the end of the string.

Time Complexity #
Since we traverse the string of n characters once, the time complexity for this algorithm is O(n).

 */
class EvaluatePostfixChallenge {
    public static int evaluatePostFix(String expression) {
        Stack<Integer> stack = new Stack<>(expression.length());
        //1.Scan expression character by character,
		//2.If character is a number push it in stack
		//3.If character is operator then pop two elements from stack
		//perform the operation and put the result back in stack
		//At the end, Stack will contain result of whole expression.
        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (!Character.isDigit(character)) {
                Integer x = stack.pop();
                Integer y = stack.pop();

                switch (character) {
                    case '+':
                        stack.push(y + x);
                        break;
                    case '-':
                        stack.push(y - x);
                        break;
                    case '*':
                        stack.push(y * x);
                        break;
                    case '/':
                        stack.push(y / x);
                        break;
                }

            } else
                stack.push(Character.getNumericValue(character));
        }
        return stack.pop();
    }
	public static void main(String args[]) {
	
		System.out.println(evaluatePostFix("921*-8-4+"));
		//Try your own examples below
		
	}
  
}