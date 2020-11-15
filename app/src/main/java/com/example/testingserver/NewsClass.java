package com.example.testingserver;

public class NewsClass {
    private final String mTitle;
    private final String mContent;
    private final String mDate;
    private final String mPoster;
    private final String mContentE;


    public NewsClass(String title, String content, String date, String poster, String contentEng) {
        mTitle = title;
        mContent = content;
        mDate = date;
        mPoster = poster;
        mContentE = contentEng;
    }

    public String getContent() {
        return mContent;
    }

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getmContentE() {
        return mContentE;
    }
}
