package com.example.testingserver;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsClass>> {
    private static final String NEWS_REQUEST_URL = "https://42.arcomp.ru/api/news";
    private static final int NEWS_LOADER_ID = 1;
    private NewsAdapter newAdapter;

    public NewsFragment() {
    }

    @Override
    public Loader<List<NewsClass>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(getContext(), NEWS_REQUEST_URL); //если не работает, поменять контекст
    }

    @Override
    public void onLoadFinished(androidx.loader.content.Loader<List<NewsClass>> loader, List<NewsClass> eventMov) {
        newAdapter.clear();
        //mEmptyView.setText("change me at @string");
        //mProgressBar.setVisibility(View.GONE);
        if (eventMov != null && !eventMov.isEmpty()) {
            newAdapter.addAll(eventMov);
        }
    }

    @Override
    public void onLoaderReset(androidx.loader.content.Loader<List<NewsClass>> loader) {

        newAdapter.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, android.os.Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.core_layout, container, false);
        ListView list = rootView.findViewById(R.id.list);
        newAdapter = new NewsAdapter(getContext(), new ArrayList<>()); //если не работает поменять контекст
        list.setAdapter(newAdapter);
        LoaderManager.getInstance(this).initLoader(NEWS_LOADER_ID, null, this);
        return rootView;
    }
}
