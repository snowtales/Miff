package com.example.testingserver;


import android.os.Parcel;
import android.os.Parcelable;

public class MovieClass implements Parcelable {
    private final String mTitle;
    private final String mTitleLoc;
    private final String mCountryYear;
    private final String mTitleRus;
    private final String mDirRus;
    private final String mDir;
    private final String mScriptRus;
    private final String mScript;
    private final String mCast;
    private final String mCastRus;
    private final String mCameraRus;
    private final String mCamera;
    private final String mProduRus;
    private final String mProdu;
    private final String mCtionRus;
    private final String mCtion;
    private final String mSynopsis;
    private final String mCountryRus;
    private final String mPoster;
    private final String mProgramm;
    private final String id;
    private final String programmEn;


    public MovieClass(String poster,
                      String title,
                      String titleLoc,
                      String countryYear,
                      String titleRus,
                      String programm,
                      String mirRus,
                      String mir,
                      String mcriptRus,
                      String mcript,
                      String mastRus,
                      String mast,
                      String mameraRus,
                      String mamera,
                      String mroduRus,
                      String mrodu,
                      String mtionRus,
                      String mtion,
                      String mynopsis,
                      String coRu,
                      String cId,
                      String cprogrammEn) {
        mTitle = title;
        mPoster = poster;
        mProgramm = programm;
        mCountryRus = coRu;
        mCamera = mamera;
        mCameraRus = mameraRus;
        mTitleLoc = titleLoc;
        mCountryYear = countryYear;
        mTitleRus = titleRus;
        mDirRus = mirRus;
        mDir = mir;
        mScriptRus = mcriptRus;
        mScript = mcript;
        mCastRus = mastRus;
        mCast = mast;
        mProduRus = mroduRus;
        mProdu = mrodu;
        mCtionRus = mtionRus;
        mCtion = mtion;
        mSynopsis = mynopsis;
        id = cId;
        programmEn = cprogrammEn;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getProgramm() {
        return mProgramm;
    }

    public String getCountryRus() {
        return mCountryRus;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public String getCtion() {
        return mCtion;
    }

    public String getCtionRus() {
        return mCtionRus;
    }

    public String getProdu() {
        return mProdu;
    }

    public String getProduRus() {
        return mProduRus;
    }

    public String getCamera() {
        return mCamera;
    }

    public String getCameraRus() {
        return mCameraRus;
    }

    public String getCast() {
        return mCast;
    }

    public String getCastRus() {
        return mCastRus;
    }

    public String getScript() {
        return mScript;
    }

    public String getScriptRus() {
        return mScriptRus;
    }

    public String getDir() {
        return mDir;
    }

    public String getDirRus() {
        return mDirRus;
    }

    public String getTitleRus() {
        return mTitleRus;
    }

    public String getCountryYear() {
        return mCountryYear;
    }

    public String getTitleLoc() {
        return mTitleLoc;
    }

    public String getId() {
        return id;
    }

    public String getProgrammEn() {
        return programmEn;
    }

    protected MovieClass(Parcel in) {
        mTitle = in.readString();
        mTitleLoc = in.readString();
        mCountryYear = in.readString();
        mTitleRus = in.readString();
        mDirRus = in.readString();
        mDir = in.readString();
        mScriptRus = in.readString();
        mScript = in.readString();
        mCast = in.readString();
        mCastRus = in.readString();
        mCameraRus = in.readString();
        mCamera = in.readString();
        mProduRus = in.readString();
        mProdu = in.readString();
        mCtionRus = in.readString();
        mCtion = in.readString();
        mSynopsis = in.readString();
        mCountryRus = in.readString();
        mPoster = in.readString();
        mProgramm = in.readString();
        id = in.readString();
        programmEn = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mTitleLoc);
        dest.writeString(mCountryYear);
        dest.writeString(mTitleRus);
        dest.writeString(mDirRus);
        dest.writeString(mDir);
        dest.writeString(mScriptRus);
        dest.writeString(mScript);
        dest.writeString(mCast);
        dest.writeString(mCastRus);
        dest.writeString(mCameraRus);
        dest.writeString(mCamera);
        dest.writeString(mProduRus);
        dest.writeString(mProdu);
        dest.writeString(mCtionRus);
        dest.writeString(mCtion);
        dest.writeString(mSynopsis);
        dest.writeString(mCountryRus);
        dest.writeString(mPoster);
        dest.writeString(mProgramm);
        dest.writeString(id);
        dest.writeString(programmEn);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieClass> CREATOR = new Parcelable.Creator<MovieClass>() {
        @Override
        public MovieClass createFromParcel(Parcel in) {
            return new MovieClass(in);
        }

        @Override
        public MovieClass[] newArray(int size) {
            return new MovieClass[size];
        }
    };
}
