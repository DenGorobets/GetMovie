package com.den.gorobets.getmovie.ui.views.details

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.viewmodel.SeriesSeasonDescriptionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SerialSeasonDescriptionScreenView(
    navigator: Navigator,
    seasonId: Int,
    viewModel: SeriesSeasonDescriptionViewModel = koinViewModel { parametersOf(seasonId) }
) {

}