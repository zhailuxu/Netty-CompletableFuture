package com.books.concurrency.Concurrency.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestCompletableFutureException {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		//1.创建异步任务
		CompletableFuture<String> oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				//2.休眠2s，模拟计算
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//3.抛出异常
				throw new RuntimeException("npe");
				//4. 正常结果
				//return "hello";
			}
		});

		//5.异步任务正常完成后做thenApply里面的函数，如果异常执行exceptionally内函数
		oneFuture = oneFuture.thenApply(r -> {
			return r + " world";
		}).exceptionally(ex -> {

			System.out.println(ex.getLocalizedMessage());

			return "error";
		}).handle(new BiFunction<String, Throwable, String>() {

			@Override
			public String apply(String t, Throwable u) {
				if(null != u) {
					u.printStackTrace();
					return "error h";
				}
				return t;
			}
		});
		

		System.out.println(oneFuture.get());

	}

}
