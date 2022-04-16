package com.example.proftranslatorfixgit.di

import androidx.room.Room
import com.example.proftranslatorfixgit.history.HistoryProvider
import com.example.proftranslatorfixgit.history.HistoryViewModel
import com.example.proftranslatorfixgit.model.ModelProvider
import com.example.proftranslatorfixgit.view_model.MainViewModel
import com.example.repository.room.HistoryDataBase
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), com.example.repository.room.HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<com.example.repository.Repository<List<com.example.model.ApiData>>> {
        com.example.repository.RepositoryImplementation(
            dataSource = com.example.repository.RemoteModel()
        )
    }
    single<com.example.repository.RepositoryLocal<List<com.example.model.ApiData>>> {
        com.example.repository.RepositoryImplementationLocal(
            dataSource = com.example.repository.LocalModel(
                historyDao = get()
            )
        )
    }
}

val mainScreen = module {
    factory { ModelProvider(repositoryRemote = get(), repositoryLocal = get()) }
    factory { MainViewModel(provider = get()) }
}

val historyScreen = module {
    factory { HistoryProvider(repositoryLocal = get()) }
    factory { HistoryViewModel(provider = get()) }
}