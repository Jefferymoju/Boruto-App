package com.jefferymoju.borutoapp.domain.repository

import com.jefferymoju.borutoapp.domain.model.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId: Int): Hero

}