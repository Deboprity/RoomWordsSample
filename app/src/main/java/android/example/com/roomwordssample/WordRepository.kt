package android.example.com.roomwordssample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository(application: Application) {
    private var mWordDao: WordDao
    private var mAllWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db!!.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        InsertAsyncTask(mWordDao).execute(word)
    }

    fun update(word: Word) {
        UpdateAsyncTask(mWordDao).execute(word)
    }

    fun delete(word: Word) {
        DeleteAsyncTask(mWordDao).execute(word)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.update(params[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.delete(params[0])
            return null
        }
    }
}