package khomsin.nui.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import khomsin.nui.todo.model.RegisterModel

@Database(entities = [RegisterModel.Response.User::class], version = 1)
abstract class TodoInstance : RoomDatabase() {

    abstract fun getTodoDatabase(): TodoDatabase

    companion object {

        @Volatile
        private var instance: TodoInstance? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, TodoInstance::class.java, "db_todo").build()

    }

}