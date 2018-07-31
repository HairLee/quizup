package com.elcom.com.quizupapp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elcom.com.quizupapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroductionFragment extends Fragment {


    public IntroductionFragment() {
        // Required empty public constructor
    }

    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static IntroductionFragment newInstance(int page, String title) {
        IntroductionFragment fragmentFirst = new IntroductionFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,


                             Bundle savedInstanceState) {

        switch (page){

            case 0:{
                view = inflater.inflate(R.layout.fragment_introduction, container, false);
                break;
            }

            case 1:{
                view = inflater.inflate(R.layout.fragment_introduction2, container, false);
                break;
            }

            case 2:{
                view = inflater.inflate(R.layout.fragment_introduction3, container, false);
                break;
            }


        }



        return view;
    }



}
