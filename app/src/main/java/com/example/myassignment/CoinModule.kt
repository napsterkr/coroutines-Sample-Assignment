package com.example.myassignment

import com.example.myassignment.viewModel.HomeListActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CoinModule {


    val viewModelModule = module {
        viewModel { HomeListActivityViewModel(get()) }
        single {
            retorfitModule
        }
    }


    val retorfitModule = module {
        single { RetrofitModule() }
    }


}