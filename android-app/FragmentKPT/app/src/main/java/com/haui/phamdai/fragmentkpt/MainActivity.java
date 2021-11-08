package com.haui.phamdai.fragmentkpt;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        FragmentA fragmentA = new FragmentA();
//
//        transaction.add(R.id.fragmentContainer_Main, fragmentA);
//
//        transaction.commit();
    }

    public void addFragment(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.buttonAddA:
                fragment = new FragmentA();
                break;
            case R.id.buttonAddB:
                fragment = new FragmentB();
                break;
        }
        transaction.replace(R.id.fragmentContainer_Main, fragment);
        transaction.commit();
    }
}