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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.compose.exercise.R
import com.android.compose.exercise.network.GameRepo
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchView(
    back: () -> Unit
) {
    var openGameView by remember { mutableStateOf(false) }
    var selectedGame: Game? by remember { mutableStateOf(null) }
    var games by remember { mutableStateOf(listOf<Game>()) }
    var text by remember { mutableStateOf("") }

    BackHandler { if (!openGameView) back() }

    LaunchedEffect(text) {
        delay(300)
        games = GameRepo.instance.searchGames(text)
    }

    SharedTransitionLayout {
        AnimatedContent(
            openGameView,
            label = stringResource(R.string.transition_to_game_view)
        ) { targetState ->
            if (!targetState) {
                SearchContent(
                    onTextChange = { text = it },
                    games = games,
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
fun SearchContent(
    onTextChange: (String) -> Unit,
    games: List<Game>,
    onGameSelected: (Game) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column {
        SearchField { onTextChange(it) }
        if (games.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            GameList(
                games = games,
                onGameSelected = onGameSelected,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope
            )
        }
    }
}