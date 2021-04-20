package com.project.technodom.main.di

import com.project.technodom.main.presentation.data.MovieInteractor
import com.project.technodom.main.presentation.data.MovieRepository
import com.project.technodom.main.presentation.data.MovieRepositoryImpl
import org.koin.dsl.module

val interactorAndRepositoryModule = module {


    single<MovieRepository>{ MovieRepositoryImpl(get()) }
    single { MovieInteractor(get(), get()) }

}