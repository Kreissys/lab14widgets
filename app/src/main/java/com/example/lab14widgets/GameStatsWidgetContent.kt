package com.example.lab14widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class GameStatsWidgetContent : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                GameStatsContent()
            }
        }
    }

    @Composable
    private fun GameStatsContent() {
        // Datos simulados del juego (después los puedes obtener de SharedPreferences o base de datos)
        val playerLevel = 15
        val playerScore = 2450
        val victories = 12
        val coins = 1230
        val levelProgress = 65 // Porcentaje de progreso al siguiente nivel

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(ColorProvider(Color(0xFF1a1a2e)))
                .cornerRadius(16.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header con título del juego
                GameHeader()

                Spacer(modifier = GlanceModifier.height(12.dp))

                // Stats del jugador
                PlayerStatsSection(playerLevel, levelProgress, playerScore, victories, coins)

                Spacer(modifier = GlanceModifier.height(16.dp))

                // Botones de acción
                ActionButtonsSection()
            }
        }
    }

    @Composable
    private fun GameHeader() {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(ColorProvider(Color(0xFF16213e)))
                .cornerRadius(12.dp)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "⚔️",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = ColorProvider(Color(0xFFffa500))
                )
            )
            Spacer(modifier = GlanceModifier.width(8.dp))
            Text(
                text = "BLINDFORGE",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider(Color(0xFFffa500))
                )
            )
        }
    }

    @Composable
    private fun PlayerStatsSection(
        level: Int,
        levelProgress: Int,
        score: Int,
        victories: Int,
        coins: Int
    ) {
        Column(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nivel con indicador circular
            LevelIndicator(level, levelProgress)

            Spacer(modifier = GlanceModifier.height(12.dp))

            // Stats en dos columnas
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Columna izquierda
                Column(
                    modifier = GlanceModifier.defaultWeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StatItem("🎯", "Score", score.toString())
                    Spacer(modifier = GlanceModifier.height(8.dp))
                    StatItem("🏆", "Victorias", victories.toString())
                }

                Spacer(modifier = GlanceModifier.width(16.dp))

                // Columna derecha
                Column(
                    modifier = GlanceModifier.defaultWeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StatItem("💰", "Monedas", coins.toString())
                    Spacer(modifier = GlanceModifier.height(8.dp))
                    StatItem("⚡", "Energía", "100%")
                }
            }
        }
    }

    @Composable
    private fun LevelIndicator(level: Int, progress: Int) {
        Box(
            modifier = GlanceModifier
                .size(80.dp)
                .background(ColorProvider(Color(0xFF0f3460)))
                .cornerRadius(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nivel",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = ColorProvider(Color(0xFFe94560))
                    )
                )
                Text(
                    text = level.toString(),
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorProvider(Color(0xFFffa500))
                    )
                )
                Text(
                    text = "$progress%",
                    style = TextStyle(
                        fontSize = 9.sp,
                        color = ColorProvider(Color(0xFF00d4ff))
                    )
                )
            }
        }
    }

    @Composable
    private fun StatItem(emoji: String, label: String, value: String) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(ColorProvider(Color(0xFF0f3460)))
                .cornerRadius(8.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = emoji,
                style = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = GlanceModifier.width(6.dp))
            Column {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = ColorProvider(Color(0xFFb0b0b0))
                    )
                )
                Text(
                    text = value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ColorProvider(Color.White)
                    )
                )
            }
        }
    }

    @Composable
    private fun ActionButtonsSection() {
        Column(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón principal: JUGAR AHORA
            Button(
                text = "▶️ JUGAR AHORA",
                onClick = actionStartActivity<MainActivity>(),
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(ColorProvider(Color(0xFFe94560)))
                    .cornerRadius(12.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorProvider(Color.White)
                )
            )

            Spacer(modifier = GlanceModifier.height(8.dp))

            // Botones secundarios
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    text = "🏆 Ranking",
                    onClick = actionStartActivity<SecondActivity>(),
                    modifier = GlanceModifier
                        .defaultWeight()
                        .height(40.dp)
                        .background(ColorProvider(Color(0xFF16213e)))
                        .cornerRadius(10.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = ColorProvider(Color.White)
                    )
                )

                Spacer(modifier = GlanceModifier.width(8.dp))

                Button(
                    text = "🎁 Tienda",
                    onClick = actionStartActivity<MainActivity>(),
                    modifier = GlanceModifier
                        .defaultWeight()
                        .height(40.dp)
                        .background(ColorProvider(Color(0xFF16213e)))
                        .cornerRadius(10.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = ColorProvider(Color.White)
                    )
                )
            }
        }
    }
}