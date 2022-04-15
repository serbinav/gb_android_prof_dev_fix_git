package com.example.mytranslator.di

import com.example.mytranslator.Constants
import com.example.mytranslator.model.LocalModel
import com.example.mytranslator.model.MainRepository
import com.example.mytranslator.model.RemoteModel
import com.example.mytranslator.model.WordTranslate
import com.example.mytranslator.retrofit.Data
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(Constants.NAME_REMOTE)
    internal fun provideDataSourceRemote(): MainRepository<Data> {
        return RemoteModel()
    }

    @Provides
    @Singleton
    @Named(Constants.NAME_LOCAL)
    internal fun provideDataSourceLocal(): MainRepository<WordTranslate> {
        return LocalModel()
    }

}