package com.vwaber.udacity.crusty.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.RecipeAsyncTaskLoader;

import java.util.List;

public class RecipeListFragment extends Fragment
        implements
        LoaderManager.LoaderCallbacks<List<Recipe>>{

    private static final int RECIPE_LOADER_ID = 0;
    private static final String LOADER_BUNDLE_KEY_URL = "loader-bundle-url-key";

    private RecipeListAdapter mAdapter;

    private int mGridSpanCount;

    public RecipeListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_recipe_list);
        mAdapter = new RecipeListAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), mGridSpanCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        Bundle bundle = new Bundle();
        bundle.putString(LOADER_BUNDLE_KEY_URL, getString(R.string.data_url));
        getLoaderManager().initLoader(RECIPE_LOADER_ID, bundle, this);

        return rootView;
    }

    public void setGridSpanCount(int count){
        mGridSpanCount = count;
    }

    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, final Bundle args) {
        String urlString = args.getString(LOADER_BUNDLE_KEY_URL);
        return new RecipeAsyncTaskLoader(getContext(), urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        mAdapter.swap(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {}

}
