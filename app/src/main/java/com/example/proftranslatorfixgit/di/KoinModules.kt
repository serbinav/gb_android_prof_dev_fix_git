package com.example.proftranslatorfixgit.di

import androidx.room.Room
import com.example.proftranslatorfixgit.model.*
import com.example.proftranslatorfixgit.retrofit.ApiData
import com.example.proftranslatorfixgit.room.HistoryDataBase
import com.example.proftranslatorfixgit.view_model.HistoryViewModel
import com.example.proftranslatorfixgit.view_model.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<ApiData>>> { RepositoryImplementation(dataSource = RemoteModel()) }
    single<RepositoryLocal<List<ApiData>>> {
        RepositoryImplementationLocal(
            dataSource = LocalModel(
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