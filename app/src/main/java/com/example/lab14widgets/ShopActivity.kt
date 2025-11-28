package com.example.lab14widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab14widgets.ui.theme.Lab14WidgetsTheme

data class ShopItem(
    val name: String,
    val price: Int,
    val emoji: String,
    val type: String
)

class ShopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab14WidgetsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF1a1a2e)
                ) {
                    ShopScreen()
                }
            }
        }
    }
}

@Composable
fun ShopScreen() {
    var coins by remember { mutableStateOf(1230) }

    val shopItems = listOf(
        ShopItem("Espada Legendaria", 500, "⚔️", "Arma"),
        ShopItem("Escudo Divino", 450, "🛡️", "Defensa"),
        ShopItem("Poción de Vida", 50, "❤️", "Consumible"),
        ShopItem("Armadura Épica", 600, "🎽", "Armadura"),
        ShopItem("Casco de Guerra", 350, "⛑️", "Armadura"),
        ShopItem("Botas Veloces", 200, "👢", "Equipamiento"),
        ShopItem("Anillo Mágico", 400, "💍", "Accesorio"),
        ShopItem("Gema de Poder", 800, "💎", "Especial"),
        ShopItem("Elixir Místico", 150, "🧪", "Consumible"),
        ShopItem("Cofre del Tesoro", 1000, "📦", "Especial")
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
            // Header con monedas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF16213e)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🎁", fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "TIENDA",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFffa500)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Contador de monedas
                    Row(
                        modifier = Modifier
                            .background(Color(0xFF0f3460), RoundedCornerShape(16.dp))
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "💰", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tus monedas:",
                            fontSize = 14.sp,
                            color = Color(0xFFb0b0b0)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$coins",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFffa500)
                        )
                    }
                }
            }

            // Grid de items
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(shopItems) { item ->
                    ShopItemCard(
                        item = item,
                        currentCoins = coins,
                        onPurchase = { price ->
                            if (coins >= price) {
                                coins -= price
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ShopItemCard(item: ShopItem, currentCoins: Int, onPurchase: (Int) -> Unit) {
    val canAfford = currentCoins >= item.price
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF16213e)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Tipo de item
            Box(
                modifier = Modifier
                    .background(Color(0xFF0f3460), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = item.type,
                    fontSize = 10.sp,
                    color = Color(0xFF00d4ff)
                )
            }

            // Icono del item
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFF0f3460), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.emoji, fontSize = 36.sp)
            }

            // Nombre del item
            Text(
                text = item.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 2
            )

            // Precio y botón
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "💰", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${item.price}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFffa500)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (canAfford) {
                            showDialog = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (canAfford) Color(0xFFe94560) else Color(0xFF4a4a4a)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = canAfford
                ) {
                    Text(
                        text = if (canAfford) "COMPRAR" else "Sin fondos",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    // Dialog de confirmación
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "✅ ¡Compra exitosa!",
                    color = Color(0xFF00d4ff)
                )
            },
            text = {
                Text(
                    text = "Has comprado: ${item.emoji} ${item.name}",
                    color = Color.White
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onPurchase(item.price)
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFe94560)
                    )
                ) {
                    Text("OK")
                }
            },
            containerColor = Color(0xFF16213e)
        )
    }
}