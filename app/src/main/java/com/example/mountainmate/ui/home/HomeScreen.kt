package com.example.mountainmate.ui.home

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mountainmate.Screen
import java.net.URLEncoder

enum class HomePage(val title: String, val url: String) {
    YUSHAN("玉山國家公園", "https://www.ysnp.gov.tw/"),
    HSUEH_PA("雪霸國家公園", "https://www.spnp.gov.tw/"),
    TAROKO("太魯閣國家公園", "https://www.taroko.gov.tw/")
}
@Composable
fun HomeScreen(navController: NavHostController) {
        val homePages by remember {
            mutableStateOf(listOf(HomePage.YUSHAN, HomePage.HSUEH_PA, HomePage.TAROKO))
        }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(homePages.size) { index ->
            HomePageItem(homePages[index]){
                val url = URLEncoder.encode(homePages[index].url, "UTF-8")
                navController.navigate("${Screen.HomeWebView.route}/$url")
            }
        }
    }
}

@Composable
fun HomePageItem(homePage: HomePage, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.secondary,
        shadowElevation = 6.dp,
        tonalElevation = 6.dp,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondary)
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    onClick()
                }

        ) {
            Text(
                text = homePage.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun HomeWebViewScreen(url: String) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
    ) {
        val (webView) = createRefs()
        AndroidView(
            modifier = Modifier
                .constrainAs(webView) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            factory = {
                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                }
            },
            update = {
                it.loadUrl(url)
            },
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

