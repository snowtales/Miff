package com.example.testingserver;


import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class MoviesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<MovieClass>> {
    private static final String MOVIE_REQUEST_URL = "https://42.arcomp.ru/api/movies";
    private static final int NEWS_LOADER_ID = 1;
    private HashSet<String> states;
    private ProgAndList collectionOfMovie;
    private final List<ProgAndList> setOfCollection = new ArrayList<>();
    private GroupedMovieAdapter newRecAdapt;
    private LinearLayoutManager layoutManager;
    RecyclerView list;
    Parcelable savedRecyclerLayoutState;


    public MoviesFragment() {
    }

    @Override
    public Loader<List<MovieClass>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(getContext(), MOVIE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(androidx.loader.content.Loader<List<MovieClass>> loader, List<MovieClass> eventMov) {
        layoutManager.removeAllViews();
        //newAdapter.clear();
        //mEmptyView.setText("change me at @string");
        //mProgressBar.setVisibility(View.GONE);

        if (eventMov != null && !eventMov.isEmpty()) {
            states = new HashSet<>();
            for (int i = 0; i < eventMov.size(); i++) {
                if (Locale.getDefault().getDisplayLanguage().toLowerCase().equals("русский"))
                    states.add(eventMov.get(i).getProgramm());
                else states.add(eventMov.get(i).getProgrammEn());
            }

            for (String s : states) {
                List<MovieClass> corrected = new ArrayList<>();
                Log.v("Working well", s);
                for (int i = 0; i < eventMov.size(); i++) {
                    if (Locale.getDefault().getDisplayLanguage().toLowerCase().equals("русский")) {
                        if (eventMov.get(i).getProgramm().contains(s)) {
                            corrected.add(eventMov.get(i));
                        }
                    } else {
                        if (eventMov.get(i).getProgrammEn().contains(s)) {
                            corrected.add(eventMov.get(i));
                        }
                    }
                }
                collectionOfMovie = new ProgAndList(s, corrected);
                setOfCollection.add(collectionOfMovie);
                //newRecAdapt.setData(corrected);
            }

            newRecAdapt.setData(setOfCollection);
            list.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

            /*for(int i = 0;i < eventMov.size(); i++) {
                if (eventMov.get(i).getProgramm().toLowerCase().contains("Конкурс документального кино".toLowerCase())) {
                    Log.v("This Movie", eventMov.get(i).getTitle() + "");
                    corrected.add(eventMov.get(i));
                }
            } */
        //newRecAdapt.setData(eventMov);
    }

    @Override
    public void onLoaderReset(androidx.loader.content.Loader<List<MovieClass>> loader) {
        // Loader reset, so we can clear out our existing data.
        layoutManager.removeAllViews();
        //newAdapter.clear();
        //newRecAdapt.setData(new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, android.os.Bundle
            savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.core_layout, container, false);
        View rootView = inflater.inflate(R.layout.core_recycle_layout, container, false);
        list = rootView.findViewById(R.id.recycler);
        //ListView list = rootView.findViewById(R.id.list);
        newRecAdapt = new GroupedMovieAdapter(getActivity(), new ArrayList<>());

        layoutManager = new LinearLayoutManager(getContext());//getActivity()
        list.setLayoutManager(layoutManager);
        list.setAdapter(newRecAdapt);
        //list.setAdapter(newAdapter);
            /*list.setOnItemClickListener((parent, view, position, id) -> {
                newAdapter.getItem(position);
                Intent open = new Intent(getContext(), wereWeGo.class); //поменять контекст
                //добавим сюда данные из каждого блока и передадим в интент.
                //возможно есть смысл создать контент провайдер.
                startActivity(open);
            });*/
        LoaderManager.getInstance(this).initLoader(NEWS_LOADER_ID, null, this);
        return rootView;
    }
}
