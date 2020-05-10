package com.example.gmchallenge.view.baseView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gmchallenge.R;

public class BaseViewImp implements BaseView {

    private final View rootView;

    public BaseViewImp(LayoutInflater inflater, ViewGroup parent) {
        rootView = inflater.inflate(R.layout.activity_main, parent, false);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public  <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

}
