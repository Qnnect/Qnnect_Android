package com.iame.qnnect.android.di

import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.model.DataModel
import com.iame.qnnect.android.model.DataModelImpl
import com.iame.qnnect.android.model.service.KakaoSearchService
import com.iame.qnnect.android.viewmodel.*
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
    viewModel { HomeViewModel() }
    viewModel { BookmarkViewModel() }
    viewModel { StoreViewModel() }
    viewModel { MypageViewModel() }
    viewModel { SplashViewModel() }
    viewModel { EditProfileViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)