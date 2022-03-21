package com.example.roomimplementation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize /**WE NEED TO EXTEND THE CLASS FROM PARCELABLE IN ORDER TO BE ABLE TO ADD ARGUMENTS TO
THE NAVIGATION LAYOUT TO ENABLE THE ITEMS FOR BEING CLICKABLES**/
//The user class will represent an entity in the database and an entity represents a table
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int?
): Parcelable


