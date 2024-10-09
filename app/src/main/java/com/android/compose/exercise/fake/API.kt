package com.android.compose.exercise.fake

import com.android.compose.exercise.R
import kotlinx.coroutines.delay

data class GameResponse(
    val id: String,
    val name: String,
    val image: Int?,
)

// Fake abstraction of a remote API.
class API {
    private var allGames: List<GameResponse> = listOf(
        GameResponse(
            id = "1",
            name = "Schlager Slotten",
            image = R.drawable.game_schlager_slotten,
        ),
        GameResponse(
            id = "2",
            name = "Casino Island",
            image = R.drawable.game_casino_island,
        ),
        GameResponse(
            id = "3",
            name = "Golden Joker",
            image = R.drawable.game_golden_joker,
        ),
        GameResponse(
            id = "4",
            name = "Cash & Carry",
            image = R.drawable.game_cash_carry,
        )
    )

    suspend fun searchGames(query: String): List<GameResponse> {
        val filtered = allGames.filter { game ->
            game.name.contains(query, ignoreCase = true)
        }

        simulateNetworkDelay()
        return filtered
    }

    suspend fun fetchNewGames(): List<GameResponse> {
        simulateNetworkDelay()
        return allGames.reversed()
    }

    suspend fun fetchFavoriteGames(): List<GameResponse> {
        simulateNetworkDelay()
        return allGames
    }

    private suspend fun simulateNetworkDelay() {
        delay((Math.random() * 2000).toLong())
    }
}
