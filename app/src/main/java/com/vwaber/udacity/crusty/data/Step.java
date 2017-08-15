package com.vwaber.udacity.crusty.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    public final static String PARCELABLE_EXTRA_KEY = "step-intent-parcelable-extra-key";

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public String getText(){return description;}
    public String getVideoUrl(){return videoURL;}
    public String getImageUrl(){return thumbnailURL;}

    @SuppressWarnings("unused")
    public Step(){}

    private Step(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnailURL);
    }

    public boolean equals(Object o){
        if(o instanceof Step){
            Step toCompare = (Step) o;
            return this.id == toCompare.id;
        }
        return false;
    }


}
