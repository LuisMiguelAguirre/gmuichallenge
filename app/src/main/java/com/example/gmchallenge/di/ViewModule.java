package com.example.gmchallenge.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.gmchallenge.ActivityCallback;
import com.example.gmchallenge.view.landview.LandscapeViewImp;
import com.example.gmchallenge.view.portview.PortraitViewImp;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class ViewModule {

    @Provides
    public LandscapeViewImp landscapeView(Context context) {
        return new LandscapeViewImp(LayoutInflater.from(context), null, (ActivityCallback) context);
    }

    @Provides
    public PortraitViewImp portraitView(Context context) {
        return new PortraitViewImp(LayoutInflater.from(context), null, (ActivityCallback)context);
    }
}
