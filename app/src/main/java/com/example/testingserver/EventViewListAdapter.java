package com.example.testingserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

public class EventViewListAdapter extends ArrayAdapter<EventClass> {
    public EventViewListAdapter(Context context, ArrayList<EventClass> mov) {
        super(context, 0, mov);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.mov_events_custom_view, parent, false);
        }

        EventClass currentItem = getItem(position);

        TextView title = listItemView.findViewById(R.id.what_event_mov);
        TextView time = listItemView.findViewById(R.id.when_event_mov);
        TextView place = listItemView.findViewById(R.id.where_event_mov);
        TextView hall = listItemView.findViewById(R.id.where_event_mov_hall);
        TextView date = listItemView.findViewById(R.id.date_event_mov);
        TextView type = listItemView.findViewById(R.id.type_of);

        if (Locale.getDefault().getLanguage().toLowerCase().contains("русский")) {
            title.setText(currentItem.getTitle());
            place.setText(currentItem.getPlace());
        } else {
            title.setText(currentItem.getmTitleEn());
            place.setText(currentItem.getmPlaceEn());
        }

        String finalTime = currentItem.getDate().substring(11, 16);
        time.setText(finalTime);
        String finalDate2 = currentItem.getDate().substring(8, 10);
        String finalDate = finalDate2 + " Окт";
        date.setText(finalDate);
        hall.setText(currentItem.getHall());
        String typeOf;
        switch (currentItem.getType()) {
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
        type.setText(typeOf);

        return listItemView;
    }
}
