package com.example.roomimplementation.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomimplementation.model.User

//DE DAO NEED TO BE AN INTERFACE AND, BECAUSE THE DAO CONTAINS AL THE METHODS FOR ACCESING THE DATABASE
// HERE WE ARE GOING TO MAKE AL DE QUERYS
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)//if the new user to add is exactly the same, it will be ignored
    suspend fun addUser(user: User) //the suspend Keyword will enable us to use CoRoutines

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM user_table ")
    suspend fun deleteAllusers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readALLdATA(): LiveData<List<User>>
}