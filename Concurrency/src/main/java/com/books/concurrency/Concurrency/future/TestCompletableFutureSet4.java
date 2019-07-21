package com.books.concurrency.Concurrency.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TestCompletableFutureSet4 {
	// 0自定义线程池
	private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		// 1.创建一个CompletableFuture对象
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("hello");

			}
		},POOL_EXECUTOR);

		future.whenComplete(new BiConsumer<Void, Throwable>() {

			@Override
			public void accept(Void t, Throwable u) {

				if (null == u) {
					System.out.println(t);
				} else {
					System.out.println(u.getLocalizedMessage());

				}
			}
		});

		
		Thread.sleep(300);
	}
}
