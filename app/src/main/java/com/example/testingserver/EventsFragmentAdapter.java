package com.example.testingserver;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EventsFragmentAdapter extends FragmentStateAdapter {
    public EventsFragmentAdapter(EventsFragment fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Date1EventFragment(position);
            case 1:
                return new Date1EventFragment(position);
            case 2:
                return new Date1EventFragment(position);
            case 3:
                return new Date1EventFragment(position);
            case 4:
                return new Date1EventFragment(position);
            case 5:
                return new Date1EventFragment(position);
            case 6:
                return new Date1EventFragment(position);
            case 7:
                return new Date1EventFragment(position);
        }
        return new Date1EventFragment(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
