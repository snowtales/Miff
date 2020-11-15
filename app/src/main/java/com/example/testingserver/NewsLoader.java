package com.example.testingserver;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader {
    private final String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsClass> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsClass> newsList = NewsUtils.fetchData(mUrl);
        return newsList;
    }
}
