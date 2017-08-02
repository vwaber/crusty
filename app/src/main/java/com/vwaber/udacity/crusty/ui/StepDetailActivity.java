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
        RecipeDetailFragment.StepClickListener
{

    RecipeDetailFragment mRecipeDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();

        mRecipeDetailFragment = new RecipeDetailFragment();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container_1, mRecipeDetailFragment)
                .commit();

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

    }
}
