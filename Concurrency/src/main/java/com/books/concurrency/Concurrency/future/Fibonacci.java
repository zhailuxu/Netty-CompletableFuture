package com.books.concurrency.Concurrency.future;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	final int n;

	Fibonacci(int n) {
		this.n = n;
	}

	 protected Integer compute() {
		if (n <= 1)
			return n;
		Fibonacci f1 = new Fibonacci(n - 1);
		f1.fork();
		Fibonacci f2 = new Fibonacci(n - 2);
		f2.fork();

        Integer left = f2.join();
        Integer right = f1.join();
        System.out.println(left + " " + right);
		return left + right;
	}
}
