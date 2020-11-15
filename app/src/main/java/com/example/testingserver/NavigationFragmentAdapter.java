package com.example.testingserver;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NavigationFragmentAdapter extends FragmentStateAdapter {

    public NavigationFragmentAdapter(FragmentActivity fm) {
        super(fm);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NewsFragment();
            case 1:
                return new MoviesFragment();
            case 2:
                return new EventsFragment();
        }
        return new EventsFragment();
    }

    @Override
    public int getItemCount() {
        return 3; //кол-во вкладок
    }
}
