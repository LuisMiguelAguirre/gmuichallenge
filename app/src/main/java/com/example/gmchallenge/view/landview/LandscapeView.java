package com.example.gmchallenge.view.landview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmchallenge.ActivityCallback;
import com.example.gmchallenge.view.BaseView;
import com.example.gmchallenge.view.ElementCallback;
import com.example.gmchallenge.view.ItemCallback;
import com.example.gmchallenge.adapter.ListElementsAdapter;
import com.example.gmchallenge.adapter.ListItemsAdapter;
import com.example.gmchallenge.R;
import com.example.gmchallenge.model.Element;

import java.util.List;

public class LandscapeView extends BaseView implements ItemCallback, ElementCallback {

    private final ActivityCallback activityCallback;
    private List<Element> data;
    private ListItemsAdapter itemsAdapter;
    private int elementPosition = 0;

    public LandscapeView(final LayoutInflater inflater, final ViewGroup parent, final ActivityCallback activityCallback) {
        super(inflater, parent);
        this.activityCallback = activityCallback;
    }

    public void setData(List<Element> data, int initialElementPosition, int initialItemPosition) {
        this.data = data;
        setupElementsList(initialElementPosition);
        setupItemsList(initialElementPosition, initialItemPosition);

    }

    private void setupItemsList(int initialElementPosition, int initialItemPosition) {
        RecyclerView itemList = findViewById(R.id.list_items);
        itemsAdapter = new ListItemsAdapter(getRootView().getContext(), this.data.get(initialElementPosition).items, initialItemPosition, this);
        itemList.setAdapter(itemsAdapter);
        itemList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
    }

    private void setupElementsList(int initialElementPosition) {
        RecyclerView elementsList = findViewById(R.id.list_elements);
        ListElementsAdapter elementsAdapter = new ListElementsAdapter(getRootView().getContext(), this.data, initialElementPosition, this);
        elementsList.setAdapter(elementsAdapter);
        elementsList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
    }


    @Override
    public void onClickItemCallBack(int position) {
        activityCallback.onPositionSelected(elementPosition, position);
    }

    @Override
    public void onClickElementCallBack(int position) {
        itemsAdapter.setData(this.data.get(position).items);
        elementPosition = position;
        activityCallback.onPositionSelected(elementPosition, 0);
    }
}

