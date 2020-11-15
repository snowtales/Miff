package com.example.testingserver;


public class EventClass {

    private final String mTitle;
    private final String mPlace;
    private final String mDate;
    private final String mHall;
    private final String mIdEvent;
    private final String midFilm;
    private final String mType;
    private final String mTitleEn;
    private final String mHallEn;
    private final String mPlaceEn;
    private final String mAddress;


    public EventClass(String title, String place, String date, String hall, String idFilm, String idEvent, String type, String titleEn, String hallEn, String placeEn, String adress) {
        mTitle = title;
        mPlace = place;
        midFilm = idFilm;
        mDate = date;
        mHall = hall;
        mIdEvent = idEvent;
        mType = type;
        mTitleEn = titleEn;
        mHallEn = hallEn;
        mPlaceEn = placeEn;
        mAddress = adress;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getMidFilm() {
        return midFilm;
    }

    public String getDate() {
        return mDate;
    }

    public String getHall() {
        return mHall;
    }

    public String getIdEvent() {
        return mIdEvent;
    }

    public String getType() {
        return mType;
    }

    public String getmTitleEn() {
        return mTitleEn;
    }

    public String getmHallEn() {
        return mHallEn;
    }

    public String getmPlaceEn() {
        return mPlaceEn;
    }

    public String getmAddress() {
        return mAddress;
    }
}
