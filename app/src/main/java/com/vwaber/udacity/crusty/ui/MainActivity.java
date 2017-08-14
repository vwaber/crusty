package com.vwaber.udacity.crusty.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.widget.WidgetUtils;

public class MainActivity extends AppCompatActivity
        implements
        RecipeListAdapter.RecipeClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();

        FragmentManager fragmentManager = getSupportFragmentManager();

        RecipeListFragment recipeListFragment = (RecipeListFragment) fragmentManager.findFragmentById(R.id.fragment_container);

        if (recipeListFragment == null) {

            recipeListFragment = new RecipeListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, recipeListFragment)
                    .commit();

        }

        recipeListFragment.setGridSpanCount(resources.getInteger(R.integer.recipe_grid_spans_wide));

    }

    @Override
    public void onRecipeClick(Recipe recipe, Bundle bundle) {

        WidgetUtils.updateWidget(this, bundle);

        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}