package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;

import java.util.ArrayList;
import java.util.List;

class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>{

    private final Context mContext;
    private final List<Recipe> mRecipes;
    private final RecipeClickListener mClickListener;

    interface RecipeClickListener {
        void onRecipeClick(Recipe recipe, Bundle bundle);
    }

    RecipeListAdapter(Context context, Fragment fragment) {
        mContext = context;
        mRecipes = new ArrayList<>();
        mClickListener = (RecipeClickListener) fragment;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(layoutId, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    void swap(List<Recipe> data){
        if(data == null) return;
        mRecipes.clear();
        mRecipes.addAll(data);
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Recipe recipe;
        final TextView recipeName;
        final ImageView recipeImage;

        RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeName = itemView.findViewById(R.id.tv_recipe_name);
            recipeImage = itemView.findViewById(R.id.iv_recipe_image);
        }

        void bind(final int position) {
            recipe = mRecipes.get(position);
            recipeName.setText(recipe.getName());
            if(!TextUtils.isEmpty(recipe.getImageUrl())){
                UiUtils.loadImageInto(mContext, recipeImage, recipe.getImageUrl());
            }
        }

        @Override
        public void onClick(View view) {
            final Bundle bundle = new Bundle();
            bundle.putParcelable(Recipe.PARCELABLE_EXTRA_KEY, recipe);
            mClickListener.onRecipeClick(recipe, bundle);
        }

    }

}
