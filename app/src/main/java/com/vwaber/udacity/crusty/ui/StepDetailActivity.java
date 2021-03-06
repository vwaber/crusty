package com.vwaber.udacity.crusty.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;

public class StepDetailActivity extends AppCompatActivity
        implements
        RecipeDetailFragment.FragmentCreationListener,
        RecipeDetailFragment.StepClickListener,
        StepDetailFragment.FragmentCreationListener{

    private StepDetailFragment mStepDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if(getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean isDualPaneLayout = findViewById(R.id.dual_pane_layout) != null;

        mStepDetailFragment = (StepDetailFragment) fragmentManager.findFragmentById(R.id.fragment_container_main);

        if(mStepDetailFragment == null){
            mStepDetailFragment = new StepDetailFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_main, mStepDetailFragment)
                    .commit();
        }

        if(isDualPaneLayout){

            RecipeDetailFragment recipeDetailFragment = (RecipeDetailFragment) fragmentManager.findFragmentById(R.id.fragment_container_secondary);

            if(recipeDetailFragment == null){
                recipeDetailFragment = new RecipeDetailFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_secondary, recipeDetailFragment)
                        .commit();
            }

        }

    }

    @Override
    public void onFragmentCreated(RecipeDetailFragment fragment) {
        if(getIntent().hasExtra(Recipe.PARCELABLE_EXTRA_KEY)){
            Recipe recipe = getIntent().getParcelableExtra(Recipe.PARCELABLE_EXTRA_KEY);
            fragment.setRecipe(recipe);
        }
    }

    @Override
    public void onFragmentCreated(StepDetailFragment fragment) {
        if(getIntent().hasExtra(Step.PARCELABLE_EXTRA_KEY)){
            Step step = getIntent().getParcelableExtra(Step.PARCELABLE_EXTRA_KEY);
            Recipe recipe = getIntent().getParcelableExtra(Recipe.PARCELABLE_EXTRA_KEY);
            fragment.setRecipe(recipe);
            fragment.setStep(step);
        }
    }

    @Override
    public void onStepClick(Recipe recipe, Step step) {
        mStepDetailFragment.setStep(step);
    }
}
