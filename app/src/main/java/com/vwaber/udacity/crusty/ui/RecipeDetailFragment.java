package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;

public class RecipeDetailFragment extends Fragment{

    private IngredientListAdapter mIngredientsAdapter;
    private StepListAdapter mStepsAdapter;
    private Recipe mRecipe;
    private Context mContext;

    private TextView mRecipeName;
    private TextView mRecipeServings;

    public RecipeDetailFragment(){}

    interface FragmentCreationListener{
        void onFragmentCreated(RecipeDetailFragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        mRecipeName = rootView.findViewById(R.id.tv_recipe_name);
        mRecipeServings = rootView.findViewById(R.id.tv_recipe_servings);

        createIngredientList(rootView);
        createStepList(rootView);

        return rootView;

    }

    private void createIngredientList(View parent){
        RecyclerView recyclerView = parent.findViewById(R.id.rv_ingredient_list);
        mIngredientsAdapter = new IngredientListAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mIngredientsAdapter);
    }

    private void createStepList(View parent){
        RecyclerView recyclerView = parent.findViewById(R.id.rv_step_list);
        mStepsAdapter = new StepListAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mStepsAdapter);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FragmentCreationListener) mContext).onFragmentCreated(this);
    }

    void setRecipe(Recipe data){
        mRecipe = data;
        mIngredientsAdapter.swap(mRecipe.getIngredients());
        mStepsAdapter.swap(mRecipe.getSteps());
        mRecipeName.setText(mRecipe.getName());
        mRecipeServings.setText(String.valueOf(mRecipe.getServings()));
    }
}
