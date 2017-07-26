package com.vwaber.udacity.crusty.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;


public class RecipeAsyncTaskLoader extends AsyncTaskLoader<List<Recipe>>{

    private List<Recipe> data;
    private String url;

    public RecipeAsyncTaskLoader(Context context, String urlString) {
        super(context);
        url = urlString;
    }

    @Override
    protected void onStartLoading() {
        if(data != null) deliverResult(data);
        else forceLoad();
    }

    @Override
    public List<Recipe> loadInBackground() {
        data = DataUtils.getRecipes(url);
        return data;
    }

}
