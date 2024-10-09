package com.android.compose.exercise.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.android.compose.exercise.R
import com.android.compose.exercise.fake.GameResponse

data class Game(var name: String, var image: Int?, var id: String) {
    companion object {
        fun fromApiGame(apiGame: GameResponse): Game {
            return Game(apiGame.name, apiGame.image, apiGame.id)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GameItem(
    game: Game,
    onClick: () -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = Modifier
            .width(128.dp)
            .wrapContentHeight()
            .padding(8.dp)
            .clickable { onClick() },
    ) {
        with(sharedTransitionScope) {
            Image(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = game.id),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .align(Alignment.CenterHorizontally)
                    .size(128.dp),
                painter = painterResource(id = game.image ?: R.drawable.game_golden_joker),
                contentScale = ContentScale.Crop,
                contentDescription = game.name
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = game.name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
