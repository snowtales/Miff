package com.example.testingserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import at.blogc.android.views.ExpandableTextView;

public class NewsAdapter extends ArrayAdapter<NewsClass> {
    public NewsAdapter(Context context, ArrayList<NewsClass> mov) {
        super(context, 0, mov);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_view_custom, parent, false);
        }
        NewsClass currentItem = getItem(position);

        TextView title = listItemView.findViewById(R.id.titleNews);
        title.setText(currentItem.getTitle());
        ImageView poster = listItemView.findViewById(R.id.news_image);
        Picasso.get().load(currentItem.getPoster()).into(poster);
        ExpandableTextView content = listItemView.findViewById(R.id.contentNews);
        content.setInterpolator(new OvershootInterpolator());

        if (!Locale.getDefault().getDisplayLanguage().toLowerCase().contains("русский")) {
            content.setText(currentItem.getmContentE());
        } else
            content.setText(currentItem.getContent());

        listItemView.setOnClickListener(v -> content.toggle());
        TextView date = listItemView.findViewById(R.id.dateNews);
        date.setText(currentItem.getDate());
        return listItemView;
    }
}
