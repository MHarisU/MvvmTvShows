package com.softvrbox.mvvmtvshows.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.softvrbox.mvvmtvshows.R;
import com.softvrbox.mvvmtvshows.databinding.ActivityPatientBinding;

public class PatientActivity extends AppCompatActivity {

    ActivityPatientBinding activityPatientBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPatientBinding = DataBindingUtil.setContentView(this, R.layout.activity_patient);
    }
}