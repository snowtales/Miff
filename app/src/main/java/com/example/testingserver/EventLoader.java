package com.example.testingserver;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class EventLoader extends AsyncTaskLoader {
    private final String mUrl;
    //private int mPositionOfTab;

    public EventLoader(Context context, String url) {
        super(context);
        mUrl = url;
        //mPositionOfTab = positionOfTab;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EventClass> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<EventClass> newsList = EventUtils.fetchData(mUrl);
        return newsList;
    }
}
