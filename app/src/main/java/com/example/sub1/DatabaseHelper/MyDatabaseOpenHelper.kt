package com.example.sub1.DatabaseHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sub1.Model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.STR_SPORT to TEXT,
            Favorite.STR_EVENT to TEXT ,
            Favorite.DATE_EVENT to TEXT ,
            Favorite.ID_HOME_TEAM to TEXT ,
            Favorite.INT_HOME_SCORE to TEXT ,
            Favorite.STR_HOME_TEAM to TEXT,
            Favorite.STR_TIME to TEXT ,
            Favorite.ID_LEAGUE to TEXT,
            Favorite.STR_LEAGUE to TEXT ,
            Favorite.INT_AWAY_SCORE to TEXT ,
            Favorite.STR_AWAY_TEAM to TEXT ,
            Favorite.IMG_HOME to TEXT ,
            Favorite.IMG_AWAY to TEXT)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)