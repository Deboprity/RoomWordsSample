package android.example.com.roomwordssample

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Delete
    fun delete(word: Word)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(word: Word)

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}