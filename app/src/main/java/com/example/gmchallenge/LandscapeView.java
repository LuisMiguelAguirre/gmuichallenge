package com.example.gmchallenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LandscapeView implements ItemCallback, ElementCallback{

    private final View rootView;
    private final ActivityCallback activityCallback;
    private List<Element> data;
    private ListAdapterItems mAdapterItems;
    private int elementPosition = 0;
    private int itemPosition = 0;

    public LandscapeView(final LayoutInflater inflater, final ViewGroup parent, final ActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        rootView = inflater.inflate(R.layout.activity_main, parent, false);
    }

    public View getRootView() {
        return rootView;
    }

    public void setData(List<Element> data, int initialElementPosition, int initialItemPosition) {
        this.data = data;
        RecyclerView elementsList = findViewById(R.id.list_elements);
        ListAdapterElements mAdapterElements = new ListAdapterElements(getRootView().getContext(), data, initialElementPosition, this);
        elementsList.setAdapter(mAdapterElements);
        elementsList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));

        RecyclerView itemList = findViewById(R.id.list_items);
        mAdapterItems = new ListAdapterItems(getRootView().getContext(), this.data.get(initialElementPosition).items, initialItemPosition, this);
        itemList.setAdapter(mAdapterItems);
        itemList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));

    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }


    @Override
    public void onClickItemCallBack(int position) {
        itemPosition = position;
        activityCallback.onPositionSelected(elementPosition, itemPosition);
    }

    @Override
    public void onClickElementCallBack(int position) {
        mAdapterItems.setData(this.data.get(position).items);
        elementPosition = position;
        activityCallback.onPositionSelected(elementPosition, 0);
    }
}

interface ItemCallback {
    void onClickItemCallBack(int position);
}

interface ElementCallback {
    void onClickElementCallBack(int position);
}
