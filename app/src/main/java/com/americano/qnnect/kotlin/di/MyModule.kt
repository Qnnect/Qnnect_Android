package com.americano.qnnect.kotlin.di

import com.americano.qnnect.kotlin.MainSearchRecyclerViewAdapter
import com.americano.qnnect.kotlin.model.DataModel
import com.americano.qnnect.kotlin.model.DataModelImpl
import com.americano.qnnect.kotlin.model.service.KakaoSearchService
import com.americano.qnnect.kotlin.viewmodel.AllowViewModel
import com.americano.qnnect.kotlin.viewmodel.LoginViewModel
import com.americano.qnnect.kotlin.viewmodel.MainViewModel
import com.americano.qnnect.kotlin.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * MyModule.kt
 */

var retrofitPart = module {
    single<KakaoSearchService> {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoSearchService::class.java)
    }
}

var adapterPart = module {
    factory {
        MainSearchRecyclerViewAdapter()
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { AllowViewModel() }
    viewModel { ProfileViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)