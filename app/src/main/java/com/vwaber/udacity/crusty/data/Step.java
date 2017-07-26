package com.vwaber.udacity.crusty.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.vwaber.udacity.crusty.R;

public class Step implements Parcelable {

    public static final int VIEW_TYPE = R.id.step_view_type;

    private int id;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnailURL;

    @SuppressWarnings("unused")
    public Step(){}

    private Step(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoUrl = in.readString();
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
        parcel.writeString(videoUrl);
        parcel.writeString(thumbnailURL);
    }
}
