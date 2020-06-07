package com.transformations.sample.data.remote.example

import com.transformations.sample.data.remote.createWebService
import com.transformations.sample.data.remote.provideRetrofit
import org.koin.dsl.module


val fetchDetailsRemoteModule = module {

    single {
        provideRetrofit(
            get(),
            "https://api.thedogapi.com"
        )
    }

    single { FetchDetailsRepo(get()) }

    factory {
        createWebService<FetchDetailsAPI>(
            get()
        )
    }
}