package com.example.gmchallenge.view.baseView;

import android.view.View;

public interface BaseView {
    View getRootView();

    <T extends View> T findViewById(int id);
}
