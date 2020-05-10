package com.example.gmchallenge.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gmchallenge.R;

public class BaseView {

    private final View rootView;

    public BaseView(LayoutInflater inflater, ViewGroup parent) {
        rootView = inflater.inflate(R.layout.activity_main, parent, false);
    }

    public View getRootView() {
        return rootView;
    }

    public  <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

}
