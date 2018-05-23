package com.example.monicamamdouh.rxjavaparellerrequesttask.base;


import android.support.v4.app.Fragment;
import android.view.View;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract void initializeViews(View v);

    protected abstract void setListeners();
}
