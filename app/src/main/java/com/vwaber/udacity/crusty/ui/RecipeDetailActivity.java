package com.vwaber.udacity.crusty.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;

public class RecipeDetailActivity extends AppCompatActivity
        implements
        RecipeListAdapter.RecipeClickListener,
        RecipeDetailFragment.FragmentCreationListener,
        RecipeDetailFragment.StepClickListener{

    private Resources mResources;

    private boolean mIsDualPaneLayout;

    private RecipeDetailFragment mIngredientListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mResources = getResources();

        mIsDualPaneLayout = findViewById(R.id.dual_pane_layout) != null;

        mIngredientListFragment = new RecipeDetailFragment();

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
    public void onFragmentCreated(RecipeDetailFragment fragment) {
        if(getIntent().hasExtra(Recipe.PARCELABLE_EXTRA_KEY)){
            Recipe data = getIntent().getParcelableExtra(Recipe.PARCELABLE_EXTRA_KEY);
            fragment.setRecipe(data);
        }
    }

    @Override
    public void onStepClick(Recipe data) {
        Intent intent = new Intent(this, StepDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Recipe.PARCELABLE_EXTRA_KEY, data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
