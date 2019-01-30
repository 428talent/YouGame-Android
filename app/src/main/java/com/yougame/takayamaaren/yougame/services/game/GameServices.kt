package com.yougame.takayamaaren.yougame.services.game

import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.sdk.model.response.GameBand
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

object GameServices {
    suspend fun fetchGameBand(gameId: Int, imageType: String? = "desktop"): GameBand {
        return ApiClient.client.getGameBand(gameId, imageType).await()
    }

    suspend fun getGame(gameId: Int): Game {
        return ApiClient.client.getGame(gameId).await()
    }
}