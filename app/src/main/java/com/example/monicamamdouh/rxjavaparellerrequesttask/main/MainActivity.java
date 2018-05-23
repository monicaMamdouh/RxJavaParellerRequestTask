package com.example.monicamamdouh.rxjavaparellerrequesttask.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.monicamamdouh.rxjavaparellerrequesttask.R;
import com.example.monicamamdouh.rxjavaparellerrequesttask.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();

    }
    private void loadFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.lnrFragmentContainer, MainFragment.newInstance()).commit();
    }

}
