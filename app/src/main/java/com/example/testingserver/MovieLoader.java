package com.example.testingserver;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class MovieLoader extends AsyncTaskLoader {
    private final String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<MovieClass> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<MovieClass> movieList = MovieUtils.fetchData(mUrl);
        return movieList;
    }
}
