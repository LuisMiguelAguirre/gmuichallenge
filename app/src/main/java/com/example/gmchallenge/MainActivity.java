package com.example.gmchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PortraitView portraitView;
    private int elementPosition;
    private int itemPosition;
    private LandscapeView landscapeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setLandscapeView();
        } else {
            setPortraitView();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE || getSmallestScreenWidthDp() > 943) {
            setLandscapeView();
        } else {
            setPortraitView();
        }
    }

    private void setLandscapeView() {
        portraitView = null;
        landscapeView = new LandscapeView(LayoutInflater.from(this), null, this);
        setContentView(landscapeView.getRootView());
        landscapeView.setData(provideData(), elementPosition, itemPosition);
    }

    private void setPortraitView() {
        landscapeView = null;
        portraitView = new PortraitView(LayoutInflater.from(this), null, this);
        portraitView.setData(provideData(), elementPosition, itemPosition);
        setContentView(portraitView.getRootView());
    }

    private int getSmallestScreenWidthDp() {
        Configuration configuration = getResources().getConfiguration();
        return configuration.smallestScreenWidthDp;
    }

    public void onPositionSelected(int elementPosition, int itemPosition) {
        Log.i("Luis", "saving: " + elementPosition + " - " + itemPosition);

        this.elementPosition = elementPosition;
        this.itemPosition = itemPosition;
    }

    private static List<Element> provideData() {


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
        items1.add(item2);
        items1.add(item3);
        items1.add(item4);


        items2.add(item2);
        items3.add(item3);
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

        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        elements.add(element4);

        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        elements.add(element4);

        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        elements.add(element4);

        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        elements.add(element4);


        return elements;

    }
}
