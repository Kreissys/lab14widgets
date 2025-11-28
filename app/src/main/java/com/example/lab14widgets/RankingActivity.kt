package com.example.lab14widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab14widgets.ui.theme.Lab14WidgetsTheme

data class Player(
    val name: String,
    val level: Int,
    val score: Int,
    val avatar: String
)

class RankingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab14WidgetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF1a1a2e)
                ) {
                    RankingScreen()
                }
            }
        }
    }
}

@Composable
fun RankingScreen() {
    val players = listOf(
        Player("DragonSlayer", 28, 15420, "🐉"),
        Player("ShadowNinja", 25, 12890, "🥷"),
        Player("IronKnight", 22, 11230, "⚔️"),
        Player("MysticMage", 20, 9870, "🧙"),
        Player("BlazeFury", 18, 8650, "🔥"),
        Player("StormRider", 17, 7420, "⚡"),
        Player("FrostGuard", 16, 6890, "❄️"),
        Player("Tú (BlindForge)", 15, 2450, "👤"),
        Player("NightHunter", 14, 2100, "🌙"),
        Player("CrystalMage", 12, 1850, "💎")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1a1a2e),
                        Color(0xFF16213e),
                        Color(0xFF0f3460)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF16213e)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🏆", fontSize = 32.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "RANKING GLOBAL",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFffa500)
                    )
                }
            }

            // Lista de jugadores
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(players) { index, player ->
                    PlayerRankCard(
                        position = index + 1,
                        player = player,
                        isCurrentPlayer = player.name.contains("Tú")
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerRankCard(position: Int, player: Player, isCurrentPlayer: Boolean) {
    val backgroundColor = when {
        isCurrentPlayer -> Color(0xFFe94560)
        position == 1 -> Color(0xFFffd700)
        position == 2 -> Color(0xFFC0C0C0)
        position == 3 -> Color(0xFFCD7F32)
        else -> Color(0xFF16213e)
    }

    val textColor = when {
        position <= 3 && !isCurrentPlayer -> Color.Black
        else -> Color.White
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Posición
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        if (isCurrentPlayer) Color(0xFF16213e) else Color(0xFF0f3460),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (position <= 3) {
                        when (position) {
                            1 -> "🥇"
                            2 -> "🥈"
                            3 -> "🥉"
                            else -> "$position"
                        }
                    } else "$position",
                    fontSize = if (position <= 3) 20.sp else 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (position <= 3) Color.White else Color(0xFFffa500)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Avatar
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF0f3460), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = player.avatar, fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Info del jugador
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Row {
                    Text(
                        text = "Nivel ${player.level}",
                        fontSize = 12.sp,
                        color = textColor.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "⭐",
                        fontSize = 12.sp
                    )
                }
            }

            // Score
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${player.score}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = "puntos",
                    fontSize = 10.sp,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}