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

public class RecipeDetailListFragment extends Fragment{

    private RecipeDetailListAdapter mAdapter;
    private Context mContext;

    public RecipeDetailListFragment(){}

    interface OnFragmentCreatedListener{
        void onFragmentCreated(RecipeDetailListFragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredient_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_ingredient_list);
        mAdapter = new RecipeDetailListAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((OnFragmentCreatedListener) mContext).onFragmentCreated(this);
    }

    void setRecipe(Recipe recipe){
        mAdapter.swap(recipe);
    }
}
