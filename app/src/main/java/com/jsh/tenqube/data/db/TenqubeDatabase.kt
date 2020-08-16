package com.jsh.tenqube.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsh.tenqube.data.source.label.local.LabelDao
import com.jsh.tenqube.data.source.label.local.LocalLabelModel
import com.jsh.tenqube.data.source.shop.local.LocalShopLabelModel
import com.jsh.tenqube.data.source.shop.local.LocalShopModel
import com.jsh.tenqube.data.source.shop.local.ShopDao

@Database(entities = [LocalShopModel::class, LocalLabelModel::class, LocalShopLabelModel::class], version = 1, exportSchema = false)
abstract class TenqubeDatabase: RoomDatabase() {

    abstract fun shopDao(): ShopDao

    abstract fun labelDao(): LabelDao

}