package com.example.lab14widgets

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class GameStatsWidget : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = GameStatsWidgetContent()
}