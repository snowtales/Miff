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

public class GroupedMovieAdapter extends RecyclerView.Adapter<GroupedMovieAdapter.ViewHolder> {
    private final Activity activity;
    public List<ProgAndList> arrayListGroup;

    public GroupedMovieAdapter(Activity activity, ArrayList<ProgAndList> arrayListGroup) {
        this.activity = activity;
        this.arrayListGroup = arrayListGroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mad_big_matreshka, parent, false);

        return new GroupedMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.groupedTitle.setText(arrayListGroup.get(position).getProgramm());
        MovieRecyclerAdapter members = new MovieRecyclerAdapter(arrayListGroup.get(position).getPerProgramm());
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        holder.memberList.setLayoutManager(layoutManager);
        holder.memberList.removeAllViews();
        holder.memberList.setAdapter(members);
    }

    @Override
    public int getItemCount() {
        return arrayListGroup == null ? 0 : arrayListGroup.size();
    }

    public void setData(List<ProgAndList> data) {
        this.arrayListGroup = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupedTitle;
        RecyclerView memberList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupedTitle = itemView.findViewById(R.id.group_title);
            memberList = itemView.findViewById(R.id.child_recycler);
        }
    }
}
