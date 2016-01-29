package com.databindingexample.mycompany.databindingexample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.databindingexample.mycompany.databindingexample.ViewModels.ViewModel;
import com.databindingexample.mycompany.databindingexample.databinding.CountdownBinder;

public class MainActivity extends FragmentActivity {
    CountdownBinder mCountdownBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

       mCountdownBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Lets reference our textview just for fun
        mCountdownBinder.tvGreen.setText("initial text");
        ViewModel viewModel = ViewModel.instance();
        //now tell databinding about your viewModel below
        mCountdownBinder.setViewModel(viewModel);
        viewModel.startCounting(200000L);

    }

}
