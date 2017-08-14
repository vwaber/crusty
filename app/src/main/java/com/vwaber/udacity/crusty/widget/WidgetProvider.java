package com.vwaber.udacity.crusty.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.vwaber.udacity.crusty.R;
import com.vwaber.udacity.crusty.data.Recipe;

public class WidgetProvider extends AppWidgetProvider {

    static Recipe mRecipe;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        remoteViews.setRemoteAdapter(R.id.widget_list_view, intent);
        remoteViews.setEmptyView(R.id.widget_list_view, R.id.widget_empty_view);

        remoteViews.setTextViewText(R.id.tv_recipe_name, mRecipe.getName());


        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.hasExtra(Recipe.PARCELABLE_EXTRA_KEY)){
            mRecipe = intent.getParcelableExtra(Recipe.PARCELABLE_EXTRA_KEY);
            super.onReceive(context, intent);
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {}

    @Override
    public void onDisabled(Context context) {}
}

