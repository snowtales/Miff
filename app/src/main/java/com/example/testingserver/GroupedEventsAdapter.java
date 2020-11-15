package com.example.testingserver;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GroupedEventsAdapter extends RecyclerView.Adapter<GroupedEventsAdapter.ViewHolder> {
    private final Activity activity;
    public List<EventsPerPlaceClass> arrayListGroup;

    public GroupedEventsAdapter(Activity activity, ArrayList<EventsPerPlaceClass> arrayListGroup) {
        this.activity = activity;
        this.arrayListGroup = arrayListGroup;
    }

    @NonNull
    @Override
    public GroupedEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.maddest_ones, parent, false);

        return new GroupedEventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupedEventsAdapter.ViewHolder holder, int position) {
        holder.groupedTitle.setText(arrayListGroup.get(position).getPlace());
        EventViewCustomAdapter members = new EventViewCustomAdapter(arrayListGroup.get(position).getAllEventsThere());
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        holder.memberList.setLayoutManager(layoutManager);
        holder.memberList.removeAllViews();
        holder.memberList.setAdapter(members);
    }

    @Override
    public int getItemCount() {
        return arrayListGroup == null ? 0 : arrayListGroup.size();
    }

    public void setData(List<EventsPerPlaceClass> data) {
        this.arrayListGroup = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupedTitle;
        RecyclerView memberList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupedTitle = itemView.findViewById(R.id.group_title_event);
            memberList = itemView.findViewById(R.id.child_recycler_events);
        }
    }
}