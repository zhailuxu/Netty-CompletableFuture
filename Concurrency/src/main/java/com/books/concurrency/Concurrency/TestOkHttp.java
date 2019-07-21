package com.books.concurrency.Concurrency;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkHttp {

	public static void main(String[] args) throws InterruptedException {

		// 创建okHttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();// .newBuilder().protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE)).build();
		// 创建一个Request
		// final Request request = new
		// Request.Builder().url("https://api.push.apple.com/3/device/")
		final Request request = new Request.Builder().url("https://cloud.tencent.com/developer/article/1199053")
				.build();
		Call call = mOkHttpClient.newCall(request);
		// 请求加入调度
		call.enqueue(new Callback() {

			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				arg1.body().byteStream();
				System.out.println(arg0 + " " + arg1.protocol() + arg1.body().string());
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				System.out.println(arg1.getLocalizedMessage());

			}
		});

		Thread.sleep(3000);
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}

	}

}
