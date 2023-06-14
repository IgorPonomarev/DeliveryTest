package com.dviss.deliverytest.data.local.catalog

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dviss.deliverytest.data.local.catalog.entity.CategoryEntity
import com.dviss.deliverytest.data.local.catalog.entity.DishEntity
import com.dviss.deliverytest.data.local.catalog.entity.TagsConverter

@Database(
    entities = [
        DishEntity::class,
        CategoryEntity::class
    ],
    version = 1
)
@TypeConverters(TagsConverter::class)
abstract class FoodDatabase: RoomDatabase() {

    abstract val dao: FoodDao
}