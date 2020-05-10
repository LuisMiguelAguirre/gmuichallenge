package com.example.gmchallenge.view.portview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmchallenge.ActivityCallback;
import com.example.gmchallenge.view.ItemCallback;
import com.example.gmchallenge.adapter.ListAdapterItems;
import com.example.gmchallenge.R;
import com.example.gmchallenge.model.Element;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class PortraitView implements NavigationView.OnNavigationItemSelectedListener, ItemCallback {

    private final View rootView;
    private final ActivityCallback activityCallback;
    private List<Element> data;
    private RecyclerView itemList;
    private ListAdapterItems mAdapterItems;
    private List<MenuItem> items = new ArrayList<>();
    private int elementPosition;
    private int itemPosition;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;


    public PortraitView(final LayoutInflater inflater, final ViewGroup parent, final ActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
        rootView = inflater.inflate(R.layout.activity_main, parent, false);
        setupToolbarMenu();
        setupNavigationDrawerMenu();
    }

    private void setupNavigationDrawerMenu() {
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle((Activity) getRootView().getContext(),
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setupToolbarMenu() {
        toolbar = findViewById(R.id.toolbar);
    }

    public View getRootView() {
        return rootView;
    }

    public void setData(List<Element> data, int elementPosition, int itemPosition) {
        this.data = data;

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
        mAdapterItems = new ListAdapterItems(getRootView().getContext(), this.data.get(elementPosition).items, itemPosition, this);
        itemList.setAdapter(mAdapterItems);
        itemList.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        closeDrawer();

        navigationView.getMenu().getItem(elementPosition).setChecked(false);
        elementPosition = items.indexOf(menuItem);
        navigationView.getMenu().getItem(elementPosition).setChecked(true);
        mAdapterItems.setData(this.data.get(elementPosition).items);
        activityCallback.onPositionSelected(elementPosition, 0);

        Toast.makeText(getRootView().getContext(), "Element " + (elementPosition + 1), Toast.LENGTH_SHORT).show();
        return false;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onClickItemCallBack(int position) {
        itemPosition = position;
        activityCallback.onPositionSelected(elementPosition, itemPosition);
    }
}
