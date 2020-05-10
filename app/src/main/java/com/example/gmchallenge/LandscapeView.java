package com.example.gmchallenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LandscapeView {

    private final View rootView;
    private final MainActivity activity;
    private List<Element> data;
    private RecyclerView elementsList;
    private RecyclerView itemList;
    private ListAdapterItems mAdapterItems;
    int elementPosition = 0;
    int itemPosition = 0;

    public LandscapeView(LayoutInflater inflater, ViewGroup parent, MainActivity mainActivity) {

        rootView = inflater.inflate(R.layout.activity_main, parent, false);
        this.activity = mainActivity;

    }

    public View getRootView() {
        return rootView;
    }

    public void setData(List<Element> data, int elementPosition, int itemPosition) {
        this.data = data;
        elementsList = findViewById(R.id.list_elements);
        ListAdapterElements mAdapterElements = new ListAdapterElements(getRootView().getContext(), data, this, elementPosition);
        elementsList.setAdapter(mAdapterElements);
        elementsList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));


        itemList = findViewById(R.id.list_items);
        mAdapterItems = new ListAdapterItems(getRootView().getContext(), this.data.get(elementPosition).items, this, null, itemPosition);
        itemList.setAdapter(mAdapterItems);
        itemList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));

    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    public void onClickElementCallBack(int position) {
        mAdapterItems.setData(this.data.get(position).items);
        elementPosition = position;
        itemPosition = 0;
        activity.onPositionSelected(elementPosition, itemPosition);

    }

    public void onClickItemCallBack(int position) {
        Toast.makeText(getRootView().getContext(), "Item position: " + position, Toast.LENGTH_SHORT).show();
        itemPosition = position;
        activity.onPositionSelected(elementPosition, itemPosition);

    }
}
