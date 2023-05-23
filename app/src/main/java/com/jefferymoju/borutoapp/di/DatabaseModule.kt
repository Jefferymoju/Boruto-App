package com.jefferymoju.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.jefferymoju.borutoapp.data.local.BorutoDatabase
import com.jefferymoju.borutoapp.data.repository.LocalDataSourceImpl
import com.jefferymoju.borutoapp.domain.repository.LocalDataSource
import com.jefferymoju.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase( // function to specify how to provide an instance of our DB
        @ApplicationContext context: Context // inject application context to our project by using the annotation and specify the context
    ) : BorutoDatabase {
        return Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            BORUTO_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: BorutoDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            borutoDatabase = database
        )
    }
}