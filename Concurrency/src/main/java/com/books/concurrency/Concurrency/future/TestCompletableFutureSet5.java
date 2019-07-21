package com.books.concurrency.Concurrency.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TestCompletableFutureSet5 {
	// 0自定义线程池
	private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
			AVALIABLE_PROCESSORS * 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
			new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

	
		
		ForkJoinPool.commonPool().execute(new Runnable() {
			
			@Override
			public void run() {
				
				throw  new RuntimeException("ssssss");
				
			}
		});
		

		POOL_EXECUTOR.execute(new Runnable() {
			
			@Override
			public void run() {
				
				throw  new RuntimeException("ssssssT");
				
			}
		});
		
		Thread.sleep(300);
	}
}
