package com.vwaber.udacity.crusty.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vwaber.udacity.crusty.R;

public class StepDetailActivity extends AppCompatActivity {

    final static String STEP_PARCELABLE_KEY = "step-intent-parcelable-extra-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
    }
}
