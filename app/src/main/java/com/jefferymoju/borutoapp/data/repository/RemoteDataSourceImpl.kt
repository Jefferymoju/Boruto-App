package com.jefferymoju.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jefferymoju.borutoapp.data.local.BorutoDatabase
import com.jefferymoju.borutoapp.data.paging_source.HeroRemoteMediator
import com.jefferymoju.borutoapp.data.paging_source.SearchHeroesSource
import com.jefferymoju.borutoapp.data.remote.BorutoApi
import com.jefferymoju.borutoapp.domain.model.Hero
import com.jefferymoju.borutoapp.domain.repository.RemoteDataSource
import com.jefferymoju.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
): RemoteDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = {heroDao.getAllHeroes()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE), // each page we are going to paginate from our room database will have 3 heroes
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(borutoAPi = borutoApi, query = query)
            }
        ).flow
    }
}