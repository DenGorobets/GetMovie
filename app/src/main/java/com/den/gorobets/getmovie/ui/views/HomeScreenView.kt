package com.den.gorobets.getmovie.ui.views

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenView(navigator: Navigator, viewModel: HomeViewModel = koinViewModel()) {


}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    GetMovieTheme {
//        HomeScreenView()
//    }
//}