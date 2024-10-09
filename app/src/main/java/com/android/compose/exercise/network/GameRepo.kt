package com.android.compose.exercise.network

import com.android.compose.exercise.fake.API
import com.android.compose.exercise.ui.compose.Game

class GameRepo {
    private val api by lazy { API() }
    private val newGames = mutableListOf<Game>()
    private val favoriteGames = mutableListOf<Game>()

    suspend fun fetchNewGames(): List<Game> {
        if (newGames.isNotEmpty()) return newGames
        newGames.clear()
        newGames.addAll(api.fetchNewGames().map { Game.fromApiGame(it) })
        return newGames
    }

    suspend fun fetchFavoriteGames(): List<Game> {
        if (favoriteGames.isNotEmpty()) return favoriteGames
        favoriteGames.clear()
        favoriteGames.addAll(
            api.fetchFavoriteGames().map { Game.fromApiGame(it).apply { id = id.plus("fav") } }
        )
        return favoriteGames
    }

    suspend fun searchGames(query: String): List<Game> {
        return api.searchGames(query).map { Game.fromApiGame(it) }
    }

    companion object {
        val instance by lazy { GameRepo() }
    }
}