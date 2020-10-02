package app.lingoknight.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.lingoknight.database.AppDatabase
import app.lingoknight.database.Word
import app.lingoknight.network.WordApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class AppRepository(private val database: AppDatabase) {

    val words: LiveData<List<Word>> = database.wordDao.getListOfWords()

    suspend fun refreshWords(){
        withContext(Dispatchers.IO){
            Timber.d("refresh Words is called")
            val networkWordList = WordApi.retrofitService.getProperties()
            database.wordDao.insertAll(networkWordList)
        }
    }

    fun getWord(name: String): LiveData<Word>{
        return database.wordDao.getWord(name)
    }
}