package com.example.sub1.DatabaseHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sub1.Model.Favorite
import com.example.sub1.Model.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
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
            Favorite.TABLE_FAVORITE_EVENT, true,
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

        db.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.STR_TEAM to TEXT,
            FavoriteTeam.INT_FORMED_YEAR to TEXT ,
            FavoriteTeam.STR_TEAM_LOGO to TEXT ,
            FavoriteTeam.STR_LEAGUE to TEXT ,
            FavoriteTeam.STR_DESCRIPTIONEN to TEXT)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE_EVENT, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)