package com.vwaber.udacity.crusty.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;

public class MainActivity extends AppCompatActivity
        implements RecipeListFragment.OnRecipeClickListener {

    Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResources = getResources();

        RecipeListFragment recipeListFragment = new RecipeListFragment();

        recipeListFragment.setGridSpanCount(mResources.getInteger(R.integer.recipe_grid_spans_wide));

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, recipeListFragment)
            .commit();

    }

    @Override
    public void onRecipeClick(Recipe data) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RecipeDetailActivity.INTENT_PARCELABLE_EXTRA_KEY, data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
