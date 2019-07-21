package com.books.concurrency.Concurrency.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestForkJoin {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(0,Integer.MAX_VALUE, 0	, TimeUnit.MINUTES, new SynchronousQueue<>());
		
		
		for(int i=0;i<10000;++i) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			System.out.println(pool.getActiveCount());
		}
		
		
		
		Thread.currentThread().join();
		
		
//		
//		ForkJoinPool pool = ForkJoinPool.commonPool();
//		
//		ForkJoinTask<Integer> task = new Fibonacci(100);
//		
//		System.out.println(pool.submit(task).get());

	}  

}   
