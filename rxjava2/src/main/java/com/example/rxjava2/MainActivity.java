package com.example.rxjava2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.rxjava2.data.PictureCategory;
import com.example.rxjava2.retrofit.RetrofitProvider;
import com.example.rxjava2.service.Api;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
//http://www.jianshu.com/p/464fa025229e

	private static String TAG = "MainActivity";

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		//test1();
		//test2();
		test22();

	}


	//1. =======================================================
	private void test11(){
		setContentView(R.layout.activity_main);
		//创建一个上游 Observable：
		Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

				Log.d(TAG, "emit: 1");
				emitter.onNext(1);

				Log.d(TAG, "emit: 2");
				emitter.onNext(2);

				Log.d(TAG, "emit: 3");
				emitter.onNext(3);

				Log.d(TAG, "emit: onComplete");
				emitter.onComplete();
			}
		});
		//创建一个下游 Observer

		Observer<Integer> observer = new Observer<Integer>() {

			private Disposable mDisposable;

			@Override
			public void onSubscribe(Disposable disposable) {
				Log.d(TAG, "subscribe");
				mDisposable = disposable;
			}

			@Override
			public void onNext(Integer value) {
				Log.d(TAG, "onNext: " + value);
				if (value == 2) {
					mDisposable.dispose();
					Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
				}
			}

			@Override
			public void onError(Throwable e) {
				Log.d(TAG, "error");
			}

			@Override
			public void onComplete() {
				Log.d(TAG, "complete");
			}
		};

		//建立连接
		observable.subscribe(observer);
	}

	//2. =======================================================

	private void test12(){


		Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
				Log.d(TAG, "emit 1");
				emitter.onNext(1);
			}
		});

		Consumer<Integer> consumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) throws Exception {
				Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
				Log.d(TAG, "onNext: " + integer);
			}
		};

		observable.subscribe(consumer);

	}


	//3. =======================================================

	private void test21(){


		Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
				Log.d(TAG, "emit 1");
				emitter.onNext(1);
			}
		});

		Consumer<Integer> consumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) throws Exception {
				Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
				Log.d(TAG, "onNext: " + integer);
			}
		};

//		observable.subscribeOn(Schedulers.newThread())
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.observeOn(Schedulers.io())
//				.subscribe(consumer);

		observable.subscribeOn(Schedulers.newThread())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
					}
				})
				.observeOn(Schedulers.io())
				.doOnNext(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						Log.d(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
					}
				})
				.subscribe(consumer);

	}


	private void test22(){
		Api api = RetrofitProvider.get().create(Api.class);
		api.getPictureCategoryInfo()
				.subscribeOn(Schedulers.io())               //在IO线程进行网络请求
				.observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
				.subscribe(new Observer<PictureCategory>() {
					@Override
					public void onSubscribe(Disposable d) {

					}

					@Override
					public void onNext(PictureCategory pictureCategory) {
						Log.d(TAG, "onNext : " + pictureCategory.toString());
					}

					@Override
					public void onError(Throwable e) {
						Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete() {
						Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
					}
				});
	}


}
