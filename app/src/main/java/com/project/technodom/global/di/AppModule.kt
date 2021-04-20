package com.project.technodom.global.di

import com.project.technodom.main.di.interactorAndRepositoryModule
import com.project.technodom.main.di.mainModule

val appModule = listOf(networkModule, utilModule, interactorAndRepositoryModule, mainModule)