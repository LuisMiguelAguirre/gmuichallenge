package com.example.gmchallenge.view.landview;

import com.example.gmchallenge.model.Element;
import com.example.gmchallenge.view.baseView.BaseView;

import java.util.List;

public interface LandscapeView extends BaseView {
    void setData(List<Element> data, int initialElementPosition, int initialItemPosition);
}
