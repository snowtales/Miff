package com.example.testingserver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class EventsFragment extends Fragment {
    ViewPager2 viewpager;
    String[] titles;


    public EventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, android.os.Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.date_core_layout, container, false);
        viewpager = rootView.findViewById(R.id.pager_at_fragments);
        TabLayout tabLayout = rootView.findViewById(R.id.tabs_at_fragments);
        EventsFragmentAdapter fr2 = new EventsFragmentAdapter(this);
        viewpager.setAdapter(fr2);
        titles = new String[]{"1\n" + getString(R.string.october), "2\n" + getString(R.string.october), "3\n" + getString(R.string.october),
                "4\n" + getString(R.string.october), "5\n" + getString(R.string.october), "6\n" + getString(R.string.october),
                "7\n" + getString(R.string.october), "8\n" + getString(R.string.october)};
        new TabLayoutMediator(tabLayout, viewpager, (tab, position) -> tab.setText(titles[position])).attach();
        return rootView;
    }
}
