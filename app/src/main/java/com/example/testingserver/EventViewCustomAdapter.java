package com.example.testingserver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class EventViewCustomAdapter extends RecyclerView.Adapter<EventViewCustomAdapter.MyViewHolder> {
    private final List<EventClass> mData;

    public EventViewCustomAdapter(List<EventClass> mov) {
        this.mData = mov;
    }

    @Override
    public EventViewCustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view_custom, parent, false);
        EventViewCustomAdapter.MyViewHolder myViewHolder = new EventViewCustomAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (Locale.getDefault().getDisplayLanguage().toLowerCase().equals("русский")) {
            holder.title.setText(mData.get(position).getTitle());
            holder.describe.setText(mData.get(position).getHall());
        } else {
            holder.title.setText(mData.get(position).getmTitleEn());
            holder.describe.setText(mData.get(position).getmHallEn());
        }

        String finalTime = mData.get(position).getDate().substring(11, 16);
        holder.time.setText(finalTime);
        String typeOf;
        switch (mData.get(position).getType()) {
            case "0":
                typeOf = "Открытый";
                break;
            case "1":
                typeOf = "Закрытый";
                break;
            case "2":
                typeOf = "Пресс-показ";
                break;
            case "3":
                typeOf = "Свободно";
                break;
            default:
                typeOf = null;
        }
        holder.type.setText(typeOf);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView describe;
        TextView time;
        LinearLayout lineEvent;
        TextView type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.what_event);
            describe = itemView.findViewById(R.id.where_event);
            time = itemView.findViewById(R.id.when_event);
            lineEvent = itemView.findViewById(R.id.grouped_id);
            type = itemView.findViewById(R.id.type_of_events);
        }
    }
}
