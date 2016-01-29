package com.databindingexample.mycompany.databindingexample.ViewModels;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.databindingexample.mycompany.databindingexample.BR;
import com.databindingexample.mycompany.databindingexample.Interfaces.ICountDownListener;
import com.databindingexample.mycompany.databindingexample.MyCountDownTimer;

//notice we are subclassing BaseObservable
public class ViewModel extends BaseObservable implements ICountDownListener{

    private static ViewModel instance;
    private long countDownTime;
    private MyCountDownTimer mCountDownTimer;
    private final String TAG = getClass().getSimpleName();

    //lock the constructor as this is a singleton
    private ViewModel(){
        mCountDownTimer=new MyCountDownTimer(this);

    }

    public static ViewModel instance() {
        if (instance == null) {
            instance = new ViewModel();
        }
        return instance;
    }

    /* this is an important annotation. It tells the data binding framework that
    * we are interested in updates to the countDownTime variable. Here we are saying
    * everytime the countDownTime variable changes, check the xml file for anyone
    * calling getCountDownTime() or countDownTime directly, and update it*/
    @Bindable
    public long getCountDownTime() {
        return countDownTime;
    }


    public void setCountDownTime(long countDownTime) {
        this.countDownTime = countDownTime;

        /*BR is very similar to the R file your use to in Android.
        * data binding generates BR files with our variables*/

        notifyPropertyChanged((int) BR.countDownTime);
        Log.d(TAG,"prime tick:"+countDownTime);
    }

    @BindingAdapter({"app:primeColor"})
    public static void setTextColor(TextView view, String color) {

        if("green".equals(color))
            view.setTextColor(Color.parseColor("#63f421"));

        else  if("pink".equals(color))
            view.setTextColor(Color.parseColor("#ffc0cb"));
    }

    public void startCounting(Long milli){
        mCountDownTimer.restartTimer(milli);
    }

    @Override
    public void doSomethingWithPrimeCountDown(Long count) {
        setCountDownTime(count);
    }
}
