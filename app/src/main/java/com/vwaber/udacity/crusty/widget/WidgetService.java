package com.vwaber.udacity.crusty.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Ingredient;

import java.util.List;

public class WidgetService extends RemoteViewsService{

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }

}

class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private final Context mContext;
    private List<Ingredient> mIngredients;

    ListRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {}

    @Override
    public void onDataSetChanged() {
        if(WidgetProvider.mRecipe != null) mIngredients = WidgetProvider.mRecipe.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(mIngredients == null) return 0;
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

        if(mIngredients == null) return null;

        RemoteViews remoteViews= new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

        remoteViews.setTextViewText(R.id.tv_ingredient_quantity, String.valueOf(mIngredients.get(i).getQuantity()));
        remoteViews.setTextViewText(R.id.tv_ingredient_unit, mIngredients.get(i).getUnit());
        remoteViews.setTextViewText(R.id.tv_ingredient_name, mIngredients.get(i).getIngredient());

        return remoteViews;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
