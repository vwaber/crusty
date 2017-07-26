package com.vwaber.udacity.crusty.ui;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;

public class RecipeDetailActivity extends AppCompatActivity
        implements
        RecipeListFragment.OnRecipeClickListener,
        RecipeDetailListFragment.OnFragmentCreatedListener{

    final static String INTENT_PARCELABLE_EXTRA_KEY = "intent-parcelable-extra-key";

    private Resources mResources;

    private boolean mIsDualPaneLayout;

    private RecipeDetailListFragment mIngredientListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mResources = getResources();

        mIsDualPaneLayout = findViewById(R.id.dual_pane_layout) != null;

        mIngredientListFragment = new RecipeDetailListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(mIsDualPaneLayout){

            RecipeListFragment recipeListFragment = new RecipeListFragment();
            recipeListFragment.setGridSpanCount(mResources.getInteger(R.integer.recipe_grid_spans_narrow));

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_1, recipeListFragment)
                    .add(R.id.fragment_container_2, mIngredientListFragment)
                    .commit();

        }else{

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mIngredientListFragment)
                    .commit();
        }

    }

    @Override
    public void onRecipeClick(Recipe data) {
        mIngredientListFragment.setRecipe(data);
    }

    @Override
    public void onFragmentCreated(RecipeDetailListFragment fragment) {
        if(getIntent().hasExtra(INTENT_PARCELABLE_EXTRA_KEY)){
            Recipe data = getIntent().getParcelableExtra(INTENT_PARCELABLE_EXTRA_KEY);
            fragment.setRecipe(data);
        }
    }
}
