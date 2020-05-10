package com.example.gmchallenge;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PortraitView implements NavigationView.OnNavigationItemSelectedListener {

    private final View rootView;
    private final ActivityCallback activityCallback;
    private List<Element> data;
    private RecyclerView itemList;
    private ListAdapterItems mAdapterItems;
    private List<MenuItem> items = new ArrayList<>();
    private int elementPosition;
    private int itemPosition;
    private NavigationView navigationView;


    public PortraitView(final LayoutInflater inflater, final ViewGroup parent, final ActivityCallback activityCallback) {

        rootView = inflater.inflate(R.layout.activity_main, parent, false);
        this.activityCallback = activityCallback;
    }

    public View getRootView() {
        return rootView;
    }

    public void setData(List<Element> data, int elementPosition, int itemPosition) {
        this.data = data;

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Menu menu = navigationView.getMenu();

        int counterMenuItems = 0;
        for (Element elem : data) {
            menu.add(elem.name);
            items.add(menu.getItem(counterMenuItems));
            counterMenuItems++;
        }

        navigationView.getMenu().getItem(this.elementPosition).setChecked(false);
        navigationView.getMenu().getItem(elementPosition).setChecked(true);
        this.elementPosition = elementPosition;


        itemList = findViewById(R.id.list_items);
        mAdapterItems = new ListAdapterItems(getRootView().getContext(), this.data.get(elementPosition).items, null, this, itemPosition);
        itemList.setAdapter(mAdapterItems);
        itemList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));



    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        navigationView.getMenu().getItem(elementPosition).setChecked(false);
        elementPosition = items.indexOf(menuItem);
        navigationView.getMenu().getItem(elementPosition).setChecked(true);
        mAdapterItems.setData(this.data.get(elementPosition).items);
        activityCallback.onPositionSelected(elementPosition, 0);

        return false;
    }

    public void onClickItemCallBack(int position) {
        Toast.makeText(getRootView().getContext(), "Item position: " + position, Toast.LENGTH_SHORT).show();
        itemPosition = position;
        activityCallback.onPositionSelected(elementPosition, itemPosition);
    }
}
