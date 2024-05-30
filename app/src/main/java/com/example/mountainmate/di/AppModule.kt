package com.example.mountainmate.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mountainmate.data.room.AppDataBase
import com.example.mountainmate.data.room.Category
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
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('帳篷', '${Category.SLEEPING}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('睡袋', '${Category.SLEEPING}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('枕頭', '${Category.SLEEPING}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('眼罩', '${Category.SLEEPING}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('耳塞', '${Category.SLEEPING}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('睡墊', '${Category.SLEEPING}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('天幕', '${Category.SLEEPING}')")

    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('爐子', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('瓦斯罐', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('打火機', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('鍋具', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('刀子', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('檔風板', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('碗筷', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('水袋', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('濾水器', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('早餐', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('午餐', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('晚餐', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('行動糧', '${Category.FOOD}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('預備糧', '${Category.FOOD}')")

    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('排汗快乾底層', '${Category.WARM}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('保暖羽絨刷毛中層', '${Category.WARM}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('雨衣雨褲', '${Category.WARM}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('備用衣物', '${Category.WARM}')")

    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('個人藥品', '${Category.SAFE}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('大力膠帶', '${Category.SAFE}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('救生毯', '${Category.SAFE}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('急救包', '${Category.SAFE}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('哨子', '${Category.SAFE}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('地圖', '${Category.SAFE}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('指北針', '${Category.SAFE}')")

    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('個人證件', '${Category.OTHER}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('入山、入園證', '${Category.OTHER}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('紙筆', '${Category.OTHER}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('衛生紙', '${Category.OTHER}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('垃圾袋', '${Category.OTHER}')")
    db.execSQL("INSERT INTO $DEFAULT_ITEM_TABLE_NAME (item_name, category) VALUES ('拖鞋', '${Category.OTHER}')")
}