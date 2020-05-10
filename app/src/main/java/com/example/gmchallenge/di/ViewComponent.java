package com.example.gmchallenge.di;

import com.example.gmchallenge.view.landview.LandscapeViewImp;
import com.example.gmchallenge.view.portview.PortraitViewImp;

import dagger.Component;

@Component(modules = ViewModule.class)
public interface ViewComponent {

    LandscapeViewImp getLandscapeView();
    PortraitViewImp getPortraitView();

}
