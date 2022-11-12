package com.example.data.storage.repositoriesImpl

import android.content.Context
import android.util.Log
import com.example.data.storage.room.dao.OnStudyWordPairDao
import com.example.data.storage.room.dao.PendingWordPairDao
import com.example.data.storage.room.dao.StudiedWordPairDao
import com.example.data.storage.room.database.WordPairDB
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.model.StudiedWordPair
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WordPairRepository(var context: Context) :
    com.example.domain.repositoriesI.WordPairRepository {

    private var onStudyWordPairDao: OnStudyWordPairDao =
        WordPairDB.getDB(context).onStudyWordPairDao()

    private var pendingWordPairDao: PendingWordPairDao =
        WordPairDB.getDB(context).pendingWordPairDao()

    private var studiedWordPairDao: StudiedWordPairDao =
        WordPairDB.getDB(context).studiedWordPairDao()



    override fun save(wordPair: OnStudyWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            try {
                onStudyWordPairDao.addWordPair(mapToData(wordPair))
            }
            catch (e: Exception) {
                Log.i("save StudiedWordPair", e.message.toString())
            }
        }
    }

    override fun save(wordPair: PendingWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            pendingWordPairDao.addWordPair(mapToData(wordPair))
        }
    }

    override fun save(wordPair: StudiedWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            studiedWordPairDao.addWordPair(mapToData(wordPair))
        }
    }

    override suspend fun getOnStudy(): List<OnStudyWordPair> {
        var temp = arrayListOf<OnStudyWordPair>()
        onStudyWordPairDao.readAll().forEach { temp.add(mapToDomain(it)) }

        return temp
    }

    override suspend fun getPending(): List<PendingWordPair> {
        var temp = arrayListOf<PendingWordPair>()
        pendingWordPairDao.readAll().forEach { temp.add(mapToDomain(it)) }
        return temp
    }

    override suspend fun getStudied(): List<StudiedWordPair> {
        var temp = arrayListOf<StudiedWordPair>()
        studiedWordPairDao.readAll().forEach { temp.add(mapToDomain(it)) }
        return temp
    }

    override suspend fun IsOnStudyListContainsStudiedWords(): Boolean {
        return onStudyWordPairDao.isOnStudyListContainsStudiedWords()
    }

    override suspend fun getOnStudyCount(): Int {
        return onStudyWordPairDao.getCount()
    }

    override suspend fun getPendingCount(): Int {
        return pendingWordPairDao.getCount()
    }

    override suspend fun getStudiedCount(): Int {
        return studiedWordPairDao.getCount()
    }

    override fun update(wordPair: OnStudyWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            onStudyWordPairDao.updateWordPair(mapToData(wordPair))
        }
    }

    override fun update(wordPair: PendingWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            pendingWordPairDao.updateWordPair(mapToData(wordPair))
        }
    }

    override fun update(wordPair: StudiedWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            studiedWordPairDao.updateWordPair(mapToData(wordPair))
        }
    }

    override fun delete(wordPair: OnStudyWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            onStudyWordPairDao.deleteWordPair(mapToData(wordPair))
        }
    }

    override fun delete(wordPair: PendingWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            pendingWordPairDao.deleteWordPair(mapToData(wordPair))
        }
    }

    override fun delete(wordPair: StudiedWordPair) {
        GlobalScope.launch(Dispatchers.Default) {
            try {
                studiedWordPairDao.deleteWordPair(mapToData(wordPair))
            }
            catch (e: Exception) {
                Log.i("delete StudiedWordPair", e.message.toString())
            }
        }
    }

    override fun deleteAll() {
        GlobalScope.launch(Dispatchers.Default) {
            onStudyWordPairDao.deleteAll()
            pendingWordPairDao.deleteAll()
            studiedWordPairDao.deleteAll()
        }
    }


    private fun mapToData(domainModel: OnStudyWordPair): com.example.data.storage.models.OnStudyWordPair {
        return com.example.data.storage.models.OnStudyWordPair(
            domainModel.id,
            domainModel.word,
            domainModel.translate,
            domainModel.countRightAnswers
        )
    }

    private fun mapToData(domainModel: PendingWordPair): com.example.data.storage.models.PendingWordPair {
        return com.example.data.storage.models.PendingWordPair(
            domainModel.id,
            domainModel.word,
            domainModel.translate
        )
    }

    private fun mapToData(domainModel: StudiedWordPair): com.example.data.storage.models.StudiedWordPair {
        return com.example.data.storage.models.StudiedWordPair(
            domainModel.id,
            domainModel.word,
            domainModel.translate
        )
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.OnStudyWordPair): OnStudyWordPair {
        return OnStudyWordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate,
            dataModel.countRightAnswers
        )
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.PendingWordPair): PendingWordPair {
        return PendingWordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate
        )
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.StudiedWordPair): StudiedWordPair {
        return StudiedWordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate
        )
    }
}