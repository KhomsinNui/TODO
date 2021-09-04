package khomsin.nui.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import khomsin.nui.todo.model.RegisterModel

@Dao
interface TodoDatabase {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(data : RegisterModel.Response.User)

    @Delete
    suspend fun deleteUser(data : RegisterModel.Response.User)

    @Query("SELECT * FROM tbUser")
    fun getUser() : LiveData<List<RegisterModel.Response.User>>
}