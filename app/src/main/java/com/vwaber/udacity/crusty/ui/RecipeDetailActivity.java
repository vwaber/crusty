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

    private RecipeDetailFragment mRecipeDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Resources resources = getResources();

        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean isDualPaneLayout = findViewById(R.id.dual_pane_layout) != null;

        RecipeListFragment recipeListFragment;
        RecipeDetailFragment recipeDetailFragment;

        if(isDualPaneLayout){

            recipeListFragment = (RecipeListFragment) fragmentManager.findFragmentById(R.id.fragment_container_1);
            recipeDetailFragment = (RecipeDetailFragment) fragmentManager.findFragmentById(R.id.fragment_container_2);

            if(recipeListFragment == null){
                recipeListFragment = new RecipeListFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_1, recipeListFragment)
                        .commit();
            }

            if(recipeDetailFragment == null){
                recipeDetailFragment = new RecipeDetailFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_2, recipeDetailFragment)
                        .commit();
            }

            recipeListFragment.setGridSpanCount(resources.getInteger(R.integer.recipe_grid_spans_narrow));

        }else{

            recipeDetailFragment = (RecipeDetailFragment) fragmentManager.findFragmentById(R.id.fragment_container);

            if(recipeDetailFragment == null){
                recipeDetailFragment = new RecipeDetailFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container, recipeDetailFragment)
                        .commit();
            }

        }

        mRecipeDetailFragment = recipeDetailFragment;

    }

    @Override
    public void onRecipeClick(Recipe data) {
        mRecipeDetailFragment.setRecipe(data);
    }

    @Override
    public void onFragmentCreated(RecipeDetailFragment fragment) {
        if(getIntent().hasExtra(Recipe.PARCELABLE_EXTRA_KEY)){
            Recipe data = getIntent().getParcelableExtra(Recipe.PARCELABLE_EXTRA_KEY);
            fragment.setRecipe(data);
        }
    }

    @Override
    public void onStepClick(Recipe recipe, Step step) {
        Intent intent = new Intent(this, StepDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Recipe.PARCELABLE_EXTRA_KEY, recipe);
        bundle.putParcelable(Step.PARCELABLE_EXTRA_KEY, step);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
