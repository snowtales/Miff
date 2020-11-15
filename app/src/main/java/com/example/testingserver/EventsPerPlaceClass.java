package com.example.testingserver;

import java.util.ArrayList;

public class EventsPerPlaceClass {
    private final String place;
    private final ArrayList<EventClass> allEventsThere;

    public EventsPerPlaceClass(String pl, ArrayList<EventClass> ev) {
        place = pl;
        allEventsThere = ev;
    }

    public String getPlace() {
        return place;
    }

    public ArrayList<EventClass> getAllEventsThere() {
        return allEventsThere;
    }
}
