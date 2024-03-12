package com.datastructures.queue;

import java.util.LinkedList;
import java.util.Queue;

public class StackwithTwoQueues<T> {
    private Queue<T> Q1 = new LinkedList<T>();
    private Queue<T> Q2 = new LinkedList<T>();
    public void push(T data){
    	if(Q1.isEmpty())
    		Q2.offer(data);
		else	Q1.offer(data);
    }
    public T pop(){
		int i=0, size;
		if(Q2.isEmpty()) {
			size = Q1.size();
			while(i < size-1) {
				Q2.offer(Q1.poll());
				i++;
			}
			return Q1.poll();
		}
		else {	
			size = Q2.size();
			while(i < size-1) {
				Q1.offer(Q2.poll());
				i++;
			}
			return Q2.poll();
		}
	}
  }
