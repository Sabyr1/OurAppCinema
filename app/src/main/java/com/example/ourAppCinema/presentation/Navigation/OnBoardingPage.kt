package com.example.ourAppCinema.presentation.Navigation

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ourAppCinema.ui.MainScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import com.example.ourAppCinema.R
import com.example.ourAppCinema.ui.theme.graphik_bold
import com.example.ourAppCinema.ui.theme.graphik_medium

@Composable
fun WelcomePage() {
    MyApp()
}


data class OnBoardingPage(
    val title: String,
    val image: Int
)

val onBoardingPages = listOf(
    OnBoardingPage("Узнавай\nо премьерах", R.drawable.layer1),
    OnBoardingPage("Создавай\nколлекции",  R.drawable.layer2),
    OnBoardingPage("Делись\nс друзьями",  R.drawable.layer3)
)

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "OnBoardingScreen"
    ) {
        composable("OnBoardingScreen") { OnBoardingScreen(navController) }
        composable("MainScreen") { MainScreen() }
    }
}

@Composable
fun OnBoardingScreen(navController: NavHostController, pagerState: PagerState = rememberPagerState { onBoardingPages.size }){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Spacer(
            modifier = Modifier
                .size(70.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 35.dp)
        ){
            Text(
                text = "SkillCinema",
                fontSize = 25.sp,
                fontFamily = graphik_medium,
                color = Color.Black,
                fontWeight = FontWeight(401),
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.size(80.dp))
            Button(onClick = {navController.navigate("MainScreen")},
                modifier = Modifier
                    .background(Color.Transparent),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "Пропустить",
                    fontFamily = graphik_bold,
                    color = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(top = 80.dp)
        ) { page -> OnBoardingPageContent(page = onBoardingPages[page]) }
        Spacer(
            modifier = Modifier.size(70.dp)
        )
        Row(
            modifier = Modifier
                .padding(start = 50.dp),
        ) {
            onBoardingPages.forEachIndexed { index, _ ->val color = if (pagerState.currentPage == index) Color.Black else Color.Gray
                Box(modifier = Modifier
                    .size(18.dp)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(color))
            }
        }
    }
}

@Composable
fun OnBoardingPageContent(page: OnBoardingPage){
    Column (
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            modifier = Modifier
                .padding(50.dp,20.dp)
                .size(350.dp)
        )
        Spacer(
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = page.title,
            fontSize = 35.sp,
            fontWeight = FontWeight(401),
            lineHeight = 35.sp,
            fontFamily = graphik_bold,
            modifier = Modifier
                .padding(start = 50.dp)
        )
    }
}