package com.haui.phamdai.fragementremove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
    }

    public void addA(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer, new AFragment(), "fragA");
        transaction.addToBackStack("aaa");
        transaction.commit();
    }

    public void addB(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer, new BFragment(), "fragB");
        transaction.addToBackStack("bbb");
        transaction.commit();
    }

    public void addC(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer, new CFragment(), "fragC");
        transaction.addToBackStack("ccc");
        transaction.commit();
    }

    public void removeA(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        AFragment aFragment = (AFragment) manager.findFragmentByTag("fragA");
        if (aFragment != null) {
            transaction.remove(aFragment);
            transaction.commit();
            Toast.makeText(this, "Đã xóa A!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Fragment không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeB(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        BFragment bFragment = (BFragment) manager.findFragmentByTag("fragB");
        if (bFragment != null) {
            transaction.remove(bFragment);
            transaction.commit();
            Toast.makeText(this, "Đã xóa B!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Fragment không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeC(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        CFragment cFragment = (CFragment) manager.findFragmentByTag("fragC");
        if (cFragment != null) {
            transaction.remove(cFragment);
            transaction.commit();
            Toast.makeText(this, "Đã xóa C!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Fragment không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        manager.popBackStack();
    }

    public void popA(View view) {
        manager.popBackStack("aaa", 0);
    }

    @Override
    public void onBackPressed() {
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}