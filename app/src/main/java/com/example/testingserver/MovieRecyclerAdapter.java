package com.example.testingserver;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MyViewHolder> implements Filterable {
    private final List<MovieClass> mData;
    private List<MovieClass> mDataFull;

    public MovieRecyclerAdapter(List<MovieClass> mov) {
        this.mData = mov;
        this.mDataFull = new ArrayList<>(mData);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cards_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        myViewHolder.lineMovie.setOnClickListener(v ->
                {
                    Intent open = new Intent(v.getContext(), MovieSingle.class);

                    open.putExtra("title", mDataFull.get(myViewHolder.getAdapterPosition()));
                    parent.getContext().startActivity(open);
                }
        );
        return myViewHolder;
    }

    public void setData(List<MovieClass> data) {
        this.mDataFull = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(mDataFull.get(position).getPoster()).into(holder.img);
        holder.title.setText(mDataFull.get(position).getTitleRus());
        holder.titleEn.setText(mDataFull.get(position).getTitle());
        /*if(Locale.getDefault().getLanguage().toLowerCase().equals("русский"))
            holder.title.setText(mData.get(position).getTitleRus());
        else
            holder.title.setText(mData.get(position).getTitle()); */

    }

    @Override
    public int getItemCount() {
        return mDataFull == null ? 0 : mDataFull.size(); //если нет никаких данных чтобы отобразить - возвращает 0 и никчего не отображает, иначе - ошибка
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView titleEn;
        ImageView img;
        LinearLayout lineMovie;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_recycler);
            titleEn = itemView.findViewById(R.id.titleEn_recycler);
            img = itemView.findViewById(R.id.poster_recycler);
            lineMovie = itemView.findViewById(R.id.card);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private final Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MovieClass> filtered = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filtered.addAll(mDataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MovieClass moving : mDataFull) {
                    if (moving.getTitle().toLowerCase().contains(filterPattern) ||
                            moving.getTitleRus().toLowerCase().contains(filterPattern))
                        filtered.add(moving);
                    Log.v("I found it!", filtered.size() + "");
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mDataFull = (ArrayList) results.values;
            Log.v("I found it!222222222", mDataFull.size() + "");
            notifyDataSetChanged();
        }
    };
}
