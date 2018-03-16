package com.example.yangjingan.myapplication.RxJavaTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.yangjingan.myapplication.R;
import com.example.yangjingan.myapplication.tools.Views;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yangjingan on 18-3-9.
 */

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";
    TextView mInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        mInfo = Views.findViewById(this,R.id.info);
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }


    private void test(){

    /*    Observable<String> demo = Observable.just("1","2","3","4");
        demo.takeUntil(new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {

            }
        });
        demo.flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull String s) throws Exception {
                return null;
            }
        });*/
  /*      Long[]items ={};
        Long[]items0 ={11L,12L,13L,14L,15L,16L,17L,18L};
        Observable <Long> observable = Observable.interval(2,TimeUnit.SECONDS).take(5);
        //当此observale发射数据时，observable的数据才会被传递到onNext中
        Observable <Long> observable1 = Observable.interval(5,TimeUnit.SECONDS).take(5);
        Observer<Long> subscriber = new Observer<Long>() {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Long v) {
                Log.e(TAG, "onNext................." + v);

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete.....................");
            }


            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError.....................");
            }
        };
        Subscriber<Long> subscriber1 = new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable
                .skipUntil(observable1)
                .subscribe(subscriber);
*/

      /*  Observable <Long> observable = Observable.interval(2,TimeUnit.SECONDS).take(10);

        observable
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(@NonNull Long aLong) throws Exception {
                        if(aLong<4){
                            return  true ;
                        }
                        return false;
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "onNext................." + aLong);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "onError.....................");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.e(TAG, "onComplete.....................");
            }
        });*/

/*
        Observable <Long> observable = Observable.interval(1,TimeUnit.SECONDS).take(10);
        //当此observale发射数据时，observable的数据还未被发射的将会被丢失
        Observable <Long> observable1 = Observable.interval(5,TimeUnit.SECONDS).take(5);

        Observable<Long> demo = Observable.just(111L,112L).timer(3,TimeUnit.SECONDS);

        observable
                .takeUntil(demo)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "onNext................." + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "onError.....................");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "onComplete.....................");
                    }
                });*/


        Observable <Long> observable = Observable.interval(1,TimeUnit.SECONDS).take(10);
        //当此observale发射数据时，observable的数据还未被发射的将会被丢失
        Observable <Long> observable1 = Observable.interval(5,TimeUnit.SECONDS).take(5);

        Observable<Long> demo = Observable.just(111L,112L).timer(3,TimeUnit.SECONDS);

        observable
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "onNext................." + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "onError.....................");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "onComplete.....................");
                    }
                });




        //demo.filter()
      /*  demo.lift(new ObservableOperator<Object, String>() {
            @Override
            public Observer<? super String> apply(@NonNull Observer<? super Object> observer) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });*/
       /* demo.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });*/

       // demo.subscribeOn(Schedulers.io())

    }
}
