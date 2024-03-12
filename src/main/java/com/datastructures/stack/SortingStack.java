package com.datastructures.stack;

import java.util.Stack;

public class SortingStack {
    public static Stack<Integer> sort(Stack<Integer> stk) {
        Stack<Integer> rstk = new Stack<Integer>();
        while(!stk.isEmpty()){
            int tmp = stk.pop();
            while(!rstk.isEmpty() && rstk.peek() > tmp){
                stk.push(rstk.pop());
            }
            rstk.push(tmp);
        }
        return rstk;
    }
}
