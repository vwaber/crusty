package com.vwaber.udacity.crusty.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable{

    public final static String PARCELABLE_EXTRA_KEY = "recipe-intent-parcelable-extra-key";

    private final int id;
    private final String name;
    private final int servings;
    private final String image;

    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<Step> steps;

    public String getName() {
        return name;
    }
    public int getServings() {
        return servings;
    }

    public List<Ingredient> getIngredients(){return ingredients;}
    public List<Step> getSteps(){return steps;}

    private Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) { return new Recipe(in); }
        @Override
        public Recipe[] newArray(int size) { return new Recipe[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeString(image);
        parcel.writeTypedList(ingredients);
        parcel.writeTypedList(steps);
    }

}
