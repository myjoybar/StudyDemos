package com.example.joybar.myaskunagjia.demo.RXJava;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.base.BaseActivity;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.commom.T;
import com.example.joybar.myaskunagjia.utils.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


/**
 * Created by joybar on 15/11/4.
 */
public class MainActivityRx extends BaseActivity {

    // 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // public static final int CLICK_ADDRESS_ADD = 0;
    // 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
     private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
    // private TextView tv_register, tv_reget_pwd;
    // 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    // 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


    //生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);
        initView();
        initData();
        setLinstener();
        fillData();
    }

    @Override
    protected void onStart() {
        L.i(TAG, "onStart");
        super.onStart();
    }
    @Override
    protected void onRestart() {
        L.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        L.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        L.i(TAG, "onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        L.i(TAG, "onStop");
        super.onStop();

    }
    @Override
    protected void onDestroy() {
        L.i(TAG, "onDestroy");
        super.onDestroy();
    }

    //初始化部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    @Override
    protected void initView() {
         btn1 = fvById(R.id.btn1);
        btn2 = fvById(R.id.btn2);
        btn3 = fvById(R.id.btn3);
        btn4 = fvById(R.id.btn4);
        btn5 = fvById(R.id.btn5);
        btn6 = fvById(R.id.btn6);
        btn7 = fvById(R.id.btn7);
        btn8 = fvById(R.id.btn8);
        btn9 = fvById(R.id.btn9);
        btn10 = fvById(R.id.btn10);
        // tv_register = fvById(R.id.tv_register);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLinstener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
      btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

    }

    @Override
    protected void fillData() {
        // TODO Auto-generated method stub

    }

    //网络请求部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //自定义方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    //回调方法部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
              case R.id.btn1:
                  ob1();
             break;
            case R.id.btn2:
                ob2();
                break;
            case R.id.btn3:
                ob3();
                break;
            case R.id.btn4:
                ob4();
                break;
            case R.id.btn5:
                ob5();
                break;
            case R.id.btn6:
                ob6();
                break;
            case R.id.btn7:
                ob7();
                break;
            case R.id.btn8:
                ob8();
                break;

            case R.id.btn9:
                ob9();
                break;
            case R.id.btn10:
                ob10();
                break;
            default:
                break;
        }

    }


    public void ob1(){
        //被观察者
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("Hello, world1!");
                subscriber.onCompleted();
            }
        });


        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                T.showShort(mContext,s);
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };


        myObservable.subscribe(mySubscriber);
    }


    public void ob2(){
        Observable<String> myObservable = Observable.just("Hello, world2!");
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                T.showShort(mContext,s);
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };


        myObservable.subscribe(mySubscriber);
    }



    public void ob3(){
        Observable<String> myObservable = Observable.just("Hello, world3!");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                T.showShort(mContext, s);
            }
        };

        myObservable.subscribe(onNextAction);
    }

    //Func1 map
    public void ob4(){
        Observable<String> myObservable = Observable.just("Hello, world3!").map
                (new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -Dan";
                    }
                });



        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                T.showShort(mContext, s);
            }
        };

        myObservable.subscribe(onNextAction);
    }

    //Func1 map
    public void ob5(){
        Observable<Integer> myObservable = Observable.just("Hello, world3!").map
                (new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return 100;
                    }
                });

//        Observable.just("Hello, world!")
//                .map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        return s.hashCode();
//                    }
//                });


        Action1<Integer> onNextAction = new Action1<Integer>() {
            @Override
            public void call(Integer s) {
                T.showShort(mContext, s+"");
            }
        };

        myObservable.subscribe(onNextAction);
    }
  // from
    public void ob6(){
       // T.showShort(mContext, "aa");
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable<String> myObservable = Observable.from(words);

  //     Observable<String> myObservable = Observable.just("Hello, world3!");

//        Action1<String> onNextAction = new Action1<String>() {
//            @Override
//            public void call(String s) {
//            //    T.showShort(mContext, s);
//                Toast.makeText(mContext,s,Toast.LENGTH_SHORT);
//            }
//
//        };
//
//        myObservable.subscribe(onNextAction);



//
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(mContext, s+"onNext", Toast.LENGTH_SHORT);
                Log.d("AAAAA",s+"onNext");
            }

            @Override
            public void onCompleted() {
                Log.d("AAAAA","onCompleted");
                Toast.makeText(mContext, "onCompleted", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("AAAAA","onError");
                Toast.makeText(mContext, "onError", Toast.LENGTH_SHORT);
            }
        };


        myObservable.subscribe(subscriber);
    }

//Schedulers Logger
    public void ob7(){
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d("AAAAA", "number:" + number);
                        Logger.init("AAAAA");
                        Logger.d("AAAAA","number:" + number);

                       // Toast.makeText(mContext, "number:" + number, Toast.LENGTH_SHORT);
                        T.showShort(mContext, "number:" + number);
                    }
                });
    }

    //flatMap
    public void ob8() {
        //Observable<List<String>> query(String text);

        Student s1 = new Student("aa");
        Course c1a = new Course("course_aa_1");
        Course c1b = new Course("course_aa_2");

        List<Course> listCourse1= new ArrayList<>();
        listCourse1.add(c1a);
        listCourse1.add(c1b);

        s1.setListCourse(listCourse1);



        Student s2 = new Student("bb");
        Course c2a = new Course("course_bb_1");
        Course c2b = new Course("course_bb_2");

        List<Course> listCourse2= new ArrayList<>();
        listCourse2.add(c2a);
        listCourse2.add(c2b);

        s2.setListCourse(listCourse2);




        Student[] students = new Student[]{s1, s2
        };

        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onNext(Course course) {
                Log.d("AAAAA", course.getCourse_name());
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        };



        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getListCourse());
                    }
                })
                .subscribe(subscriber);

    }

    //reduce
    public void ob9() {

        final String[] mManyWords = {"Hello", "I", "am", "your", "friend", "Spike"};
        final List<String> mManyWordList = Arrays.asList(mManyWords);


        // 设置映射函数
         Func1<List<String>, Observable<String>> mOneLetterFunc = new Func1<List<String>, Observable<String>>() {
            @Override public Observable<String> call(List<String> strings) {
                return Observable.from(strings); // 映射字符串
            }
        };

        // 连接字符串
         Func2<String, String, String> mMergeStringFunc = new Func2<String, String, String>() {
            @Override public String call(String s, String s2) {
                return String.format("%s %s", s, s2); // 空格连接字符串
            }
        };


        // Action设置Toast
         Action1<String> mToastAction = new Action1<String>() {
            @Override public void call(String s) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            }
        };




        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mOneLetterFunc)
                .reduce(mMergeStringFunc)
                .subscribe(mToastAction);
    }


    public void ob10() {

        Observable observable = Observable.create(new Observable.OnSubscribe<Subscriber>() {
            @Override
            public void call(Subscriber subscriber) {
                subscriber.onNext(longRunningOperation());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                T.showShort(mContext, "结束");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("AAAAA", "number:" + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                T.showShort(mContext, "start");
            }
        });

    }

    // 长时间运行的任务
    private String longRunningOperation() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            Log.e("AAAA", e.toString());
        }

        return "Complete";
    }
}

