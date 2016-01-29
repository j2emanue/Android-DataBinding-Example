package com.databindingexample.mycompany.databindingexample;

import android.os.CountDownTimer;
import android.util.Log;

import com.databindingexample.mycompany.databindingexample.Interfaces.ICountDownListener;

public class MyCountDownTimer {
    private CountDownTimer countDownTimer;
    private boolean isExecuting =false;
    private ICountDownListener listener;
    private final String TAG = getClass().getSimpleName();

    public MyCountDownTimer(ICountDownListener listener){
        this.listener = listener;
    }

    public void startTimer(long timeLeftMillis) {
        if (!isExecuting) {
            isExecuting = true;
            countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    ;
                    if(isPrime(millisUntilFinished/1000)){
                        listener.doSomethingWithPrimeCountDown(millisUntilFinished / 1000);

                    }
                }

                @Override
                public void onFinish() {
                    isExecuting = false;
                    listener.doSomethingWithPrimeCountDown(0L);
                }
            };
            countDownTimer.start();
        } else {
            Log.i(TAG, "Timer already started");
        }
    }

    public void cancelTimer() {
        if (isExecuting) {
            countDownTimer.cancel();
            isExecuting = false;
        }
    }

    public void restartTimer(Long milli) {
        cancelTimer();
        startTimer(milli);
    }

    //checks whether an int is prime or not.
    boolean  isPrime(Long n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
