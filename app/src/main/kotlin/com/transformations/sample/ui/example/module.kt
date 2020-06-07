package com.transformations.sample.ui.example

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fetchDetailsModule = module {
    viewModel { FetchDetailsViewModel(get()) }
}