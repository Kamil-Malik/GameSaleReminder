package com.lelestacia.gamediscountreminder.data.di

import android.content.Context
import androidx.room.Room
import com.lelestacia.gamediscountreminder.data.source.local.dao.StoreDao
import com.lelestacia.gamediscountreminder.data.source.local.db.GameDiscountDB
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
    fun provideDB(
        @ApplicationContext context: Context
    ): GameDiscountDB {
        return Room
            .databaseBuilder(
                context,
                GameDiscountDB::class.java,
                "game_discount_db"
            ).build()
    }

    @Provides
    @Singleton
    fun provideStoreDao(
        db: GameDiscountDB
    ): StoreDao {
        return db.storeDao()
    }
}