package com.jefferymoju.borutoapp.data.repository

import com.jefferymoju.borutoapp.data.local.BorutoDatabase
import com.jefferymoju.borutoapp.domain.model.Hero
import com.jefferymoju.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase): LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

}