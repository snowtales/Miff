package com.example.testingserver;

import android.os.Bundle;
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

public class Date1EventFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<EventClass>> {
    private static final String MOVIE_REQUEST_URL = "https://42.arcomp.ru/api/events";
    private static final int NEWS_LOADER_ID = 1;
    private final int positionOfTab;
    private HashSet<String> states;
    private EventsPerPlaceClass collectionOfEvents;
    private final List<EventsPerPlaceClass> setOfCollection = new ArrayList<>();
    private GroupedEventsAdapter newRecAdapt;
    private LinearLayoutManager layoutManager;

    public Date1EventFragment(int positionOfTabcon) {
        positionOfTab = positionOfTabcon;
    }

    @Override
    public Loader<List<EventClass>> onCreateLoader(int i, Bundle bundle) {
        return new EventLoader(getContext(), MOVIE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(androidx.loader.content.Loader<List<EventClass>> loader, List<EventClass> eventMov) {
        layoutManager.removeAllViews();
        //mEmptyView.setText("change me at @string");
        //mProgressBar.setVisibility(View.GONE);
        states = new HashSet<>();
        List<EventClass> datedEvents = new ArrayList<>();
        if (eventMov != null && !eventMov.isEmpty()) { //проверяю, не пустой ли список
            for (int i = 0; i < eventMov.size(); i++) {
                switch (positionOfTab) {
                    case 0:
                        if (eventMov.get(i).getDate().contains("2020-10-01")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 1:
                        if (eventMov.get(i).getDate().contains("2020-10-02")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 2:
                        if (eventMov.get(i).getDate().contains("2020-10-03")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 3:
                        if (eventMov.get(i).getDate().contains("2020-10-04")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 4:
                        if (eventMov.get(i).getDate().contains("2020-10-05")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 5:
                        if (eventMov.get(i).getDate().contains("2020-10-06")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 6:
                        if (eventMov.get(i).getDate().contains("2020-10-07")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                    case 7:
                        if (eventMov.get(i).getDate().contains("2020-10-08")) {

                            datedEvents.add(eventMov.get(i));
                        }
                        break;
                }
                /*for(int i = 0; i<eventMov.size(); i++) //строки сортировки
                    if(eventMov.get(i).getTitle().toLowerCase().contains("searchWord".toLowerCase())) //боже как охуенно
                        newAdapter.add(eventMov.get(i));
                    else newAdapter.addAll(eventMov); */
            }
            for (int i = 0; i < datedEvents.size(); i++) { //формирую список существующих площадок
                states.add(datedEvents.get(i).getPlace());
            }

            for (String s : states) {
                ArrayList<EventClass> corrected = new ArrayList<>();
                Log.v("Working well", s);
                for (int i = 0; i < datedEvents.size(); i++) {
                    if (datedEvents.get(i).getPlace().contains(s)) {
                        corrected.add(datedEvents.get(i));
                    }
                }
                collectionOfEvents = new EventsPerPlaceClass(s, corrected);
                setOfCollection.add(collectionOfEvents);
                //newRecAdapt.setData(corrected);
            }
            newRecAdapt.setData(setOfCollection);

        }
    }

    @Override
    public void onLoaderReset(androidx.loader.content.Loader<List<EventClass>> loader) {
        layoutManager.removeAllViews();
        //newRecAdapt.setData(new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.core_recycle_layout, container, false);
        RecyclerView list = rootView.findViewById(R.id.recycler);
        newRecAdapt = new GroupedEventsAdapter(getActivity(), new ArrayList<>()); //если не работает поменять контекст
        layoutManager = new LinearLayoutManager(getContext()); //or getActivity()
        list.setLayoutManager(layoutManager);
        list.setAdapter(newRecAdapt);

        LoaderManager.getInstance(this).initLoader(NEWS_LOADER_ID, null, this).forceLoad();
        return rootView;
    }
}

