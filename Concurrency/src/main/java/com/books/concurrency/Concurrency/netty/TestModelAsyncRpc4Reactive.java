package com.books.concurrency.Concurrency.netty;

import java.util.concurrent.ExecutionException;

import io.reactivex.Flowable;

public class TestModelAsyncRpc4Reactive {

	private static final RpcClient rpcClient = new RpcClient();

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 2.发起远程调用异步，并注册回调，马上返回
		Flowable<String> result = rpcClient.rpcAsyncCallFlowable("who are you");

		result.subscribe(/* onNext */r -> {

			System.out.println(Thread.currentThread().getName() + ":" + r);
		}, /* onError */error -> {
			System.out.println(Thread.currentThread().getName() + "error:" + error.getLocalizedMessage());
		});

		System.out.println("---async rpc call over");
	}
}
