package com.example.testingserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Switch;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    ViewPager2 viewpager;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String[] titles;
    Switch language;
    public static final String DEFAULT = "rus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //устанавливаем язык
        SharedPreferences sharedPreferences = getSharedPreferences("selectedLanguage", Context.MODE_PRIVATE);
        Locale locale = new Locale(sharedPreferences.getString("language", DEFAULT));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        invalidateOptionsMenu();
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);

        //устанавливаем навигационные вкладки
        titles = new String[]{getString(R.string.news), getString(R.string.films), getString(R.string.events)};
        viewpager = findViewById(R.id.pager);
        TabLayout tablayout = findViewById(R.id.tab_layout);
        NavigationFragmentAdapter fr = new NavigationFragmentAdapter(this);
        viewpager.setAdapter(fr);
        viewpager.setUserInputEnabled(false); //отключение свайпа главных вкладок
        new TabLayoutMediator(tablayout, viewpager, (tab, position) -> tab.setText(titles[position])).attach();

        //настраиваем боковое меню
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        setupNavigationView();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//сохраняем позицию кнопки
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.switch_button);
        language = menuItem.getActionView().findViewById(R.id.drawer_switch);
        SharedPreferences sharedPrefs = getSharedPreferences("com.example.xyz", MODE_PRIVATE);
        language.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", false));

        navigationView.setCheckedItem(R.id.switch_button);
        navigationView.getMenu().performIdentifierAction(R.id.switch_button, 0);
        Log.v("Lang is:", Locale.getDefault().getDisplayLanguage());
    }

    public boolean onOptionsItemSelected(MenuItem menu) {
        if (actionBarDrawerToggle.onOptionsItemSelected(menu)) {
            return true;
        }
        return super.onOptionsItemSelected(menu);
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(item -> {
            switchScreen(item.getItemId());
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_main, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewCollapsed();
                Intent intent = new Intent(getBaseContext(), wereWeGo.class);
                startActivity(intent);
            }
        });
       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newRecAdapt.getFilter().filter(newText);
                return false;
            }
        }); */
        return true;
    }

    private void switchScreen(int id) {
        Intent goat = new Intent(Intent.ACTION_VIEW);
        switch (id) {
            case R.id.fa:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mmkf42/"));
                startActivity(intent);
                break;
            case R.id.ins:
                goat.setData(Uri.parse("https://www.instagram.com/mmkf_official/"));
                startActivity(goat);
                break;
            case R.id.vk:
                goat.setData(Uri.parse("https://vk.com/mmkf"));
                startActivity(goat);
                break;
            case R.id.about:
                goat.setData(Uri.parse("http://moscowfilmfestival.ru/miff41/page/?page=history"));
                startActivity(goat);
                break;
            case R.id.switch_button:
                language.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        SharedPreferences.Editor editor = getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
                        editor.putBoolean("NameOfThingToSave", true);
                        editor.commit();

                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        language.setChecked(true);

                        SharedPreferences.Editor ensharedPreferences = getSharedPreferences("selectedLanguage", MODE_PRIVATE).edit();
                        ensharedPreferences.putString("language", "en");
                        ensharedPreferences.commit();

                        Intent refresh = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(refresh);

                    } else {
                        //положение кнопки сохраняем
                        SharedPreferences.Editor editor = getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
                        editor.putBoolean("NameOfThingToSave", false);
                        editor.commit();

                        //устанавливаем язык
                        Locale locale = new Locale("rus");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                        language.setChecked(false);

                        // язык по умолчанию сохраняем
                        SharedPreferences.Editor ruSharedPreferences = getSharedPreferences("selectedLanguage", MODE_PRIVATE).edit();
                        ruSharedPreferences.putString("language", "rus");
                        ruSharedPreferences.commit();

                        //обновляем приложение
                        Intent refresh = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(refresh);
                    }
                });
                break;
        }
    }

}