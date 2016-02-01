package com.databindingexample.mycompany.databindingexample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.databindingexample.mycompany.databindingexample.ViewModels.ViewModel;
import com.databindingexample.mycompany.databindingexample.databinding.CountdownBinder;

public class MainActivity extends FragmentActivity {
    CountdownBinder mCountdownBinder;
private static final Long TIME= 200000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

       mCountdownBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Lets reference our textview just for fun
        mCountdownBinder.tvGreen.setText("initial text");
        final ViewModel viewModel = ViewModel.instance();
        //now tell databinding about your viewModel below
        mCountdownBinder.setViewModel(viewModel);
        viewModel.startCounting(TIME);

        mCountdownBinder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.startCounting(TIME);//RESTART THE TIMER
                Toast.makeText(MainActivity.this,"timer restarted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
