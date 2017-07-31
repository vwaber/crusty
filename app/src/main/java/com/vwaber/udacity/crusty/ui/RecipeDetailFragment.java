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

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;
import com.vwaber.udacity.crusty.data.Step;

public class RecipeDetailFragment extends Fragment implements StepListAdapter.StepClickListener{

    private IngredientListAdapter mIngredientsAdapter;
    private StepListAdapter mStepsAdapter;
    private Context mContext;

    public RecipeDetailFragment(){}

    @Override
    public void onStepClick(Step data) {
        OnStepClickListener listener = (OnStepClickListener) mContext;
        listener.onStepClick(data);
//        OnRecipeClickListener listener = (OnRecipeClickListener) getContext();
//        listener.onRecipeClick(data);
    }

    interface OnFragmentCreatedListener{
        void onFragmentCreated(RecipeDetailFragment fragment);
    }

    interface OnStepClickListener {
        void onStepClick(Step data);
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
        mStepsAdapter = new StepListAdapter(mContext, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mStepsAdapter);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((OnFragmentCreatedListener) mContext).onFragmentCreated(this);
    }

    void setRecipe(Recipe recipe){
        mIngredientsAdapter.swap(recipe.getIngredients());
        mStepsAdapter.swap(recipe.getSteps());
    }
}
