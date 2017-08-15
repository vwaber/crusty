package com.vwaber.udacity.crusty.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Ingredient;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>{

    private final Context mContext;
    private final Resources mResources;
    private final List<Ingredient> mIngredients;

    IngredientListAdapter(Context context){
        mContext = context;
        mResources = context.getResources();
        mIngredients = new ArrayList<>();
    }


    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.ingredient_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(layoutId, parent, false);
        return new IngredientListAdapter.IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    void swap(List<Ingredient> data){
        if(data == null) return;
        mIngredients.clear();
        mIngredients.addAll(data);
        notifyDataSetChanged();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        final TextView ingredientName;
        final TextView ingredientUnit;
        final TextView ingredientQuantity;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.tv_ingredient_name);
            ingredientUnit = itemView.findViewById(R.id.tv_ingredient_unit);
            ingredientQuantity = itemView.findViewById(R.id.tv_ingredient_quantity);
        }

        void bind(final int position){

            Ingredient ingredient = mIngredients.get(position);

            String name = ingredient.getIngredient().toLowerCase();
            String quantity = UiUtils.formatQuantity(mContext, ingredient.getQuantity());
            String unit = UiUtils.formatUnit(mContext, ingredient.getUnit());

            if(!unit.equals("") && !unit.equals(" ")) unit = " " + unit + " ";

            ingredientName.setText(name);
            ingredientUnit.setText(unit);
            ingredientQuantity.setText(quantity);
        }
    }

}
