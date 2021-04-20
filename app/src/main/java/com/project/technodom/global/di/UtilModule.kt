package com.project.technodom.global.di

import com.project.technodom.global.functional.NetworkHandler
import com.project.technodom.global.system.AppSchedulers
import com.project.technodom.global.system.ResourceManager
import com.project.technodom.global.system.SchedulersProvider
import com.project.technodom.global.utils.ErrorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val utilModule = module {
    single { AppSchedulers() as SchedulersProvider }
    single { ResourceManager(androidContext()) }
    single { ErrorHandler(get()) }
    single { NetworkHandler(androidContext()) }
}