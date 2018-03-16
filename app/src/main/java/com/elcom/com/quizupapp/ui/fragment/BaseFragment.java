package com.elcom.com.quizupapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by hoanv on 13/09/2015.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAppComponent();
    }

    protected abstract void setupViews();

    protected abstract void setupAppComponent();
}
