package com.books.concurrency.Concurrency;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Executor executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; ++i) {
			executor.execute(new Runnable() {

				@Override
				public void run() {

					try {
						for (;;) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(1);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						System.out.println("finanlly");
					}

				}
			});
		}

		System.out.println("over");
	}
}
