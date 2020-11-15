package com.example.testingserver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieSingle extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EventClass>> {
    private static final String MOVIE_REQUEST_URL = "https://42.arcomp.ru/api/events";
    private static final int NEWS_LOADER_ID = 1;
    private EventViewListAdapter newAdapter;


    MovieClass getter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_movie_look);
        Bundle bungle = getIntent().getExtras();
        if (bungle != null) {
            getter = getIntent().getParcelableExtra("title");
        }
        TextView title = findViewById(R.id.title_movie);
        TextView titleRus = findViewById(R.id.title_movie_rus);
        TextView titleEng = findViewById(R.id.title_movie_eng);
        TextView countryRus = findViewById(R.id.Country_rus);
        TextView countryE = findViewById(R.id.Country_eng);
        TextView synopsis = findViewById(R.id.synopsis_movie);
        TextView synopsisHead = findViewById(R.id.synopsis_header);
        TextView bio = findViewById(R.id.bio_movie);
        TextView bioheader = findViewById(R.id.bio_header);
        TextView dir = findViewById(R.id.Rus_dir_movie);
        TextView dirE = findViewById(R.id.dir_movie);
        TextView scr = findViewById(R.id.Rus_script_movie);
        TextView scrE = findViewById(R.id.script_movie);
        TextView cast = findViewById(R.id.Rus_cast_movie);
        TextView castE = findViewById(R.id.cast_movie);
        TextView cam = findViewById(R.id.rus_camera_movie);
        TextView camE = findViewById(R.id.camera_movie);
        TextView pod = findViewById(R.id.rus_prod_movie);
        TextView podE = findViewById(R.id.prod_movie);
        TextView ction = findViewById(R.id.rus_ction_movie);
        TextView ctionE = findViewById(R.id.ction_movie);
        ImageView howItLooks = findViewById(R.id.poster_at_activity);
        ListView eventsPerMovie = findViewById(R.id.list_single_movie);
        TableRow dirHeader = findViewById(R.id.dirHeader);


        title.setText(getter.getTitleLoc());
        titleRus.setText(getter.getTitleRus());
        titleEng.setText(getter.getTitle());
        countryE.setText(getter.getCountryYear());
        countryRus.setText(getter.getCountryRus());
        dir.setText(getter.getDirRus());
        dirE.setText(getter.getDir());
        scr.setText(getter.getScriptRus());
        scrE.setText(getter.getScript());
        cast.setText(getter.getCastRus());
        castE.setText(getter.getCast());
        cam.setText(getter.getCameraRus());
        camE.setText(getter.getCamera());
        pod.setText(getter.getProduRus());
        podE.setText(getter.getProdu());
        ction.setText(getter.getCtionRus());
        ctionE.setText(getter.getCtion());
        Picasso.get().load(getter.getPoster()).into(howItLooks);

        if (getter.getSynopsis().contains("null") || getter.getSynopsis().contains("-")) {
            synopsis.setVisibility(View.GONE);
            synopsisHead.setVisibility(View.GONE);
        } else {
            synopsis.setText(getter.getSynopsis());
        }

        if (getter.getDirRus().contains("-") && getter.getDir().contains("-")) {
            dir.setVisibility(View.GONE);
            dirE.setVisibility(View.GONE);
            dirHeader.setVisibility(View.GONE);
        }


        newAdapter = new EventViewListAdapter(getBaseContext(), new ArrayList<>()); //если не работает поменять контекст
        eventsPerMovie.setAdapter(newAdapter);
        eventsPerMovie.setOnItemClickListener((parent, view, position, id) -> {
            String searching = newAdapter.getItem(position).getmAddress().replace(" ", "+");
            Log.v("searching", searching);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + searching));
            startActivity(intent);
        });
        LoaderManager.getInstance(this).initLoader(NEWS_LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<EventClass>> onCreateLoader(int i, Bundle bundle) {
        return new EventLoader(getBaseContext(), MOVIE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(androidx.loader.content.Loader<List<EventClass>> loader, List<EventClass> eventMov) {
        newAdapter.clear();
        //mEmptyView.setText("change me at @string");
        //mProgressBar.setVisibility(View.GONE);
        if (eventMov != null && !eventMov.isEmpty()) {
            for (int i = 0; i < eventMov.size(); i++) {
                if (eventMov.get(i).getMidFilm().toLowerCase().equals(getter.getId().toLowerCase())) {
                    Log.v("пресс показ", eventMov.get(i).getMidFilm());

                    newAdapter.add(eventMov.get(i));
                }
            }
        }
    }

    @Override
    public void onLoaderReset(androidx.loader.content.Loader<List<EventClass>> loader) {
        newAdapter.clear();
    }

}
