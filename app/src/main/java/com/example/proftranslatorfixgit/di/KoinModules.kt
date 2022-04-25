package com.example.proftranslatorfixgit.di

import androidx.room.Room
import com.example.model.ApiDataDTO
import com.example.proftranslatorfixgit.history.HistoryActivity
import com.example.proftranslatorfixgit.history.HistoryProvider
import com.example.proftranslatorfixgit.history.HistoryViewModel
import com.example.proftranslatorfixgit.model.ModelProvider
import com.example.proftranslatorfixgit.view_model.MainViewModel
import com.example.repository.*
import com.example.repository.room.HistoryDataBase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(
            get(),
            HistoryDataBase::class.java,
            "HistoryDB"
        ).build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<ApiDataDTO>>> {
        RepositoryImplementation(
            dataSource = RemoteModel()
        )
    }
    single<RepositoryLocal<List<ApiDataDTO>>> {
        RepositoryImplementationLocal(
            dataSource = LocalModel(
                historyDao = get()
            )
        )
    }
}

val mainScreen = module {
    scope(named("MainActivity")) {
        scoped { ModelProvider(repositoryRemote = get(), repositoryLocal = get()) }
        viewModel { MainViewModel(provider = get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryProvider(repositoryLocal = get()) }
        viewModel { HistoryViewModel(provider = get()) }
    }
}