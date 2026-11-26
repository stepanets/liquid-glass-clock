package dev.liquidglass.clock

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.SizeF
import android.widget.RemoteViews

open class ClockWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        val views = buildViews(context)
        appWidgetIds.forEach { id ->
            appWidgetManager.updateAppWidget(id, views)
        }
    }

    companion object {
        fun buildViews(context: Context): RemoteViews {
            val small = RemoteViews(context.packageName, R.layout.widget_clock_small)
            val wide = RemoteViews(context.packageName, R.layout.widget_clock_wide)
            val pending = openAppIntent(context)
            small.setOnClickPendingIntent(R.id.widget_root, pending)
            wide.setOnClickPendingIntent(R.id.widget_root, pending)
            return RemoteViews(
                mapOf(
                    SizeF(110f, 110f) to small,
                    SizeF(180f, 110f) to wide,
                ),
            )
        }

        private fun openAppIntent(context: Context): PendingIntent {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            return PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
        }
    }
}
