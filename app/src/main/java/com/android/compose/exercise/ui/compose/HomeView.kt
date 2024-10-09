@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.android.compose.exercise.ui.compose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.compose.exercise.R
import com.android.compose.exercise.network.GameRepo

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeView(
    back: () -> Unit
) {
    var openGameView by remember { mutableStateOf(false) }
    var selectedGame: Game? by remember { mutableStateOf(null) }

    BackHandler { if (!openGameView) back() }

    SharedTransitionLayout {
        AnimatedContent(
            openGameView,
            label = stringResource(R.string.transition_to_game_view)
        ) { targetState ->
            if (!targetState) {
                HomeContent(
                    onGameSelected = { game ->
                        openGameView = true
                        selectedGame = game
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@AnimatedContent
                )
            } else {
                selectedGame?.let { game ->
                    GameView(
                        game = game,
                        onBack = { openGameView = false },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                } ?: EmptyView()
            }
        }
    }
}

@Composable
fun HomeContent(
    onGameSelected: (Game) -> Unit = { },
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var newGames by remember { mutableStateOf(listOf<Game>()) }
    var favoriteGames by remember { mutableStateOf(listOf<Game>()) }

    LaunchedEffect(Unit) {
        newGames = GameRepo.instance.fetchNewGames()
    }

    LaunchedEffect(Unit) {
        favoriteGames = GameRepo.instance.fetchFavoriteGames()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp)
    ) {
        HorizontalGameList(
            stringResource(R.string.new_games),
            newGames,
            onGameSelected,
            sharedTransitionScope,
            animatedVisibilityScope
        )
        HorizontalGameList(
            stringResource(R.string.favorite_games),
            favoriteGames,
            onGameSelected,
            sharedTransitionScope,
            animatedVisibilityScope
        )
    }
}

@Composable
fun EmptyView() {
    Box(contentAlignment = Alignment.Center) {
        Text(
            style = MaterialTheme.typography.headlineSmall,
            text = stringResource(R.string.game_not_found)
        )
    }
}
