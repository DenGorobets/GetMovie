package com.den.gorobets.getmovie.ui.views.list

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.dto.description.ScrollerItemData
import com.den.gorobets.getmovie.viewmodel.SeriesSeasonsListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SeriesSeasonsListScreenView(
    navigator: Navigator,
    seasonList: List<ScrollerItemData>,
    viewModel: SeriesSeasonsListViewModel = koinViewModel { parametersOf(seasonList) }
) {

}