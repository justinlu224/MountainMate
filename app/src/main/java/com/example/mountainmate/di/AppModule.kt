package com.example.mountainmate.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mountainmate.data.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME = "app_database"
const val SCHEDULE_TABLE_NAME = "schedule_table"
const val DEFAULT_ITEM_TABLE_NAME = "default_item_table"
const val CHECK_ITEM_TABLE_NAME = "check_item_table"
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    extracted(db)
                }
            })
            .build()
    }
}

private fun extracted(db: SupportSQLiteDatabase) {
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Tent', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Sleeping Bag', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Sleeping Pad', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Stove', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Water Filter', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('First Aid Kit', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Map', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Compass', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Flashlight', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Knife', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Food', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Water', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Clothing', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Backpack', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Trekking Poles', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Boots', 'CAMPING')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('Socks', 'CAMPING')")
}