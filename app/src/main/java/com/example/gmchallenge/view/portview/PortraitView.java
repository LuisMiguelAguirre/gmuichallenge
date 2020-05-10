package com.example.gmchallenge.view.portview;

import com.example.gmchallenge.model.Element;
import com.example.gmchallenge.view.baseView.BaseView;

import java.util.List;

public interface PortraitView extends BaseView {
    void setData(List<Element> data, int elementPosition, int itemPosition);
}
