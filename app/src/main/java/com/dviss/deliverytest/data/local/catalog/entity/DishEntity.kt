package com.dviss.deliverytest.data.local.catalog.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "dishes")
data class DishEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val weight: Double,
    val description: String,
    val imageUrl: String,
    val tags: List<String>
)

class TagsConverter {
    @TypeConverter
    fun fromTagsList(tags: List<String>): String {
        return tags.joinToString(",")
    }

    @TypeConverter
    fun toTagsList(tagsString: String): List<String> {
        return tagsString.split(",")
    }
}
