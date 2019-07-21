package com.books.concurrency.Concurrency.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class TestCompletableFuture {

	public static CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
		return CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 解密
				String id = encodedCompanyId;

				return id;
			}
		});
	}

	public static CompletableFuture<String> doSomethingTwo(String companyId) {
		return CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 查询公司信息，转换为JSON
				String str = companyId + ":alibaba";

				return str;
			}
		});
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		CompletableFuture oneFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return "one";
			}
		});

		CompletableFuture twoFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return "two";
			}
		});

		// a->b
		CompletableFuture result = doSomethingOne("123").thenCompose(id -> doSomethingTwo(id));
		System.out.println(result.get());

		// a para b
		result = oneFuture.thenCombine(twoFuture, (one, two) -> {
			return one + " " + two;
		});
		System.out.println(result.get());

	}

}
