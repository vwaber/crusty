package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        void onRecipeClick(Recipe data);
    }

    RecipeListAdapter(Context context) {
        mContext = context;
        mRecipes = new ArrayList<>();
        mClickListener = (RecipeClickListener) context;
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

        final TextView recipeName;

        RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeName = itemView.findViewById(R.id.tv_recipe_name);
        }

        void bind(final int position) {
            Recipe recipe = mRecipes.get(position);
            recipeName.setText(recipe.getName());
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = mRecipes.get(getAdapterPosition());
            mClickListener.onRecipeClick(recipe);
        }

    }

}
