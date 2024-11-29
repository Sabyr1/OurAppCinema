package com.example.ourAppCinema.presentation.Navigation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ourAppCinema.R
import com.example.ourAppCinema.ui.NavItem

@Composable
fun BottomNavigation( navController: NavController){
    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        tonalElevation = 0.dp
    ) {
        val navItemList = listOf(
            NavItem(painterResource(R.drawable.home)),
            NavItem(painterResource(R.drawable.search)),
            NavItem(painterResource(R.drawable.profile)),
        )

        var selectedIndex by remember { mutableIntStateOf(0) }

        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("searching")
                        2 -> navController.navigate("profile")
                    }
                },
                icon = {
                    val isSelected = selectedIndex == index
                    Icon(
                        navItem.icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.Green else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Black
                )
            )
        }
    }
}