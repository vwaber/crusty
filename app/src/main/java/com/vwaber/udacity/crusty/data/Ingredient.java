package com.vwaber.udacity.crusty.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.vwaber.udacity.crusty.R;

public class Ingredient implements Parcelable {

    private float quantity;
    private String measure;
    private String ingredient;

    public String getIngredient(){return ingredient;}

    @SuppressWarnings("unused")
    public Ingredient(){}

    private Ingredient(Parcel in) {
        quantity = in.readFloat();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }
}
