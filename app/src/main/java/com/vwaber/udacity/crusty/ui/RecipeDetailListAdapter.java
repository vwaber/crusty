package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Ingredient;
import com.vwaber.udacity.crusty.data.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailListAdapter extends RecyclerView.Adapter<RecipeDetailListAdapter.IngredientViewHolder>{

    private final Context mContext;
    //private final List<Ingredient> mIngredients;
    private Recipe mRecipe;

    RecipeDetailListAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(layoutId, parent, false);
        return new RecipeDetailListAdapter.IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(mRecipe == null) return 0;
        else return mRecipe.getIngredients().size();
    }

    void swap(Recipe data){
        mRecipe = data;
        notifyDataSetChanged();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ingredientName = (TextView) itemView.findViewById(R.id.tv_ingredient_name);
        }

        void bind(final int position){
            Ingredient ingredient = mRecipe.getIngredients().get(position);
            ingredientName.setText(ingredient.getIngredient());
        }
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        //TextView ingredientName;

        StepViewHolder(View itemView) {
            super(itemView);
            //ingredientName = (TextView) itemView.findViewById(R.id.tv_ingredient_name);
        }

        void bind(final int position){
            //Ingredient ingredient = mRecipe.ingredients.get(position);
            //ingredientName.setText(ingredient.getIngredient());
        }
    }
}
