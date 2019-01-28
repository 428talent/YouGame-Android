package com.yougame.takayamaaren.yougame.services.game

import com.yougame.takayamaaren.yougame.sdk.model.response.GameBand
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

object GameServices {
    suspend fun fetchGameBand(gameId : Int): GameBand {
        return ApiClient.client.getGameBand(gameId).await()
    }
}