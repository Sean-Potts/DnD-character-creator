package com.example.a3.ui.appWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.a3.CharacterInfo;
import com.example.a3.R;

public class AppWidget extends AppWidgetProvider {
    static public void updateAll(Context context) {
        int Ids[] = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, AppWidget.class));
        for (int appWidgetId : Ids) {
            updateAppWidget(context, AppWidgetManager.getInstance(context), appWidgetId);
        }
    }

    static public void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        CharSequence widgetText = context.getString(R.string.appwidget_text) + " " + CharacterInfo.CurrentCharacter.getCharacterName() + CharacterInfo.CurrentCharacter.savedStatus();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}