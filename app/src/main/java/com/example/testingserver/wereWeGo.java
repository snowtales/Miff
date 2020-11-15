package com.example.testingserver;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class wereWeGo extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieClass>> {

    private MovieRecyclerAdapter newRecAdapt;
    private LinearLayoutManager layoutManager;
    private static final String MOVIE_REQUEST_URL = "https://42.arcomp.ru/api/movies";
    private static final int NEWS_LOADER_ID = 1;


    @Override
    public Loader<List<MovieClass>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(this, MOVIE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(androidx.loader.content.Loader<List<MovieClass>> loader, List<MovieClass> eventMov) {
        layoutManager.removeAllViews();
        newRecAdapt.setData(eventMov);
    }

    @Override
    public void onLoaderReset(androidx.loader.content.Loader<List<MovieClass>> loader) {
        layoutManager.removeAllViews();
        newRecAdapt.setData(new ArrayList<>());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem refresh = menu.findItem(R.id.refresh);


        refresh.setOnMenuItemClickListener(item -> {
            recreate();
            return false;
        });
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newRecAdapt.getFilter().filter(query);
                searchView.onActionViewCollapsed();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newRecAdapt.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_searching_layout);
        RecyclerView list = findViewById(R.id.recycler_searching);
        TextView empty = findViewById(R.id.emptyTe);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        newRecAdapt = new MovieRecyclerAdapter(new ArrayList<>());
        layoutManager = new LinearLayoutManager(this);//getActivity()

        list.setLayoutManager(layoutManager);
        list.setAdapter(newRecAdapt);
        LoaderManager.getInstance(this).initLoader(NEWS_LOADER_ID, null, this);
    }

    @Override
    public void onBackPressed() {
    }

}
