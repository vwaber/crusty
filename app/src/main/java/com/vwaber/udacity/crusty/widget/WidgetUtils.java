package com.vwaber.udacity.crusty.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class WidgetUtils {

    public static void updateWidget(Context context, Bundle bundle){

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        ComponentName component = new ComponentName(context, WidgetProvider.class);

        int[] ids = manager.getAppWidgetIds(component);

        Intent intent = new Intent(context, WidgetProvider.class);
        intent.putExtras(bundle);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        context.sendBroadcast(intent);

    }

}
