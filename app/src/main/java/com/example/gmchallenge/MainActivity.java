package com.example.gmchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.gmchallenge.model.Element;
import com.example.gmchallenge.model.Item;
import com.example.gmchallenge.view.landview.LandscapeView;
import com.example.gmchallenge.view.portview.PortraitView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private int initialElementPosition;
    private int initialItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(this.getResources().getConfiguration());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setView(newConfig);
    }

    private void setView(Configuration newConfig) {
        if (isLandscapeView(newConfig)) {
            setLandscapeView();
        } else {
            setPortraitView();
        }
    }

    private boolean isLandscapeView(Configuration configuration) {
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE || getSmallestScreenWidthDp() > 943;
    }

    private void setLandscapeView() {
        LandscapeView landscapeView = new LandscapeView(LayoutInflater.from(this), null, this);
        setContentView(landscapeView.getRootView());
        landscapeView.setData(getData(), initialElementPosition, initialItemPosition);
    }

    private void setPortraitView() {
        PortraitView portraitView = new PortraitView(LayoutInflater.from(this), null, this);
        setContentView(portraitView.getRootView());
        portraitView.setData(getData(), initialElementPosition, initialItemPosition);
    }

    private int getSmallestScreenWidthDp() {
        Configuration configuration = getResources().getConfiguration();
        return configuration.smallestScreenWidthDp;
    }

    @Override
    public void onPositionSelected(int elementPosition, int itemPosition) {
        this.initialElementPosition = elementPosition;
        this.initialItemPosition = itemPosition;
    }

    //TODO get data from API, use adapter pattern to convert API data into Element and Item objects, so that the code does not have to be modify
    private static List<Element> getData() {


        List<Element> elements = new ArrayList<>();
        List<Item> items1 = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        List<Item> items3 = new ArrayList<>();
        List<Item> items4 = new ArrayList<>();

        Item item1 = new Item();
        item1.name = "item 1";


        Item item2 = new Item();
        item2.name = "item 2";


        Item item3 = new Item();
        item3.name = "item 3";


        Item item4 = new Item();
        item4.name = "item 4";


        items1.add(item1);

        items2.add(item1);
        items2.add(item2);

        items3.add(item1);
        items3.add(item2);
        items3.add(item3);

        items4.add(item1);
        items4.add(item2);
        items4.add(item3);
        items4.add(item4);

        Element element1 = new Element();
        element1.name = "Element 1";
        element1.items = items1;


        Element element2 = new Element();
        element2.name = "Element 2";
        element2.items = items2;



        Element element3 = new Element();
        element3.name = "Element 3";
        element3.items = items3;



        Element element4 = new Element();
        element4.name = "Element 4";
        element4.items = items4;


        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        elements.add(element4);

        return elements;

    }
}