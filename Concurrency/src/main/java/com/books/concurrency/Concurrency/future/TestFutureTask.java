package com.books.concurrency.Concurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestFutureTask {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		System.out.println(System.currentTimeMillis()+10000000*1000);
		// 1.创建future任务
		FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				return "hello world";
			}
		});
		

		// 2.创建线程并启动，
		Thread thread = new Thread(futureTask,"thread-1");
		thread.start();

		// 3.创建线程并启动，
		Thread mainThread = Thread.currentThread();
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//3.1 休眠一秒
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//3.2中断main线程
				mainThread.interrupt();
				
			}
		},"thread-2");
		thread2.start();


		// 4.等待任务执行结果
		System.out.println("wait get result");
		//System.out.println(futureTask.get());
		
		//5.设置超时时间
		System.out.println(futureTask.get(1,TimeUnit.SECONDS));
	}
}
