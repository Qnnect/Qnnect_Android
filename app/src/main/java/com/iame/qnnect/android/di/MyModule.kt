package com.iame.qnnect.android.di

import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.MyConstant.Companion.BASE_URL
import com.iame.qnnect.android.base.NullOnEmptyConverterFactory
import com.iame.qnnect.android.base.XAccessTokenInterceptor
import com.iame.qnnect.android.model.DataModel
import com.iame.qnnect.android.model.DataModelImpl
import com.iame.qnnect.android.model.service.KakaoSearchService
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.service.AlarmCheckAPI
import com.iame.qnnect.android.src.allow.service.AlarmCheckDataImpl
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.service.LoginAPI
import com.iame.qnnect.android.src.login.service.LoginDataImpl
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.profile.service.ProfileDataImpl
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import com.iame.qnnect.android.src.splash.service.RefreshAPI
import com.iame.qnnect.android.src.splash.service.RefreshDataImpl
import com.iame.qnnect.android.viewmodel.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * MyModule.kt
 */

val client: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(5000, TimeUnit.MILLISECONDS)
    .connectTimeout(5000, TimeUnit.MILLISECONDS)
    // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
    .build()

var retrofitPart = module {
    single<KakaoSearchService> {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoSearchService::class.java)
    }
    single<LoginAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginAPI::class.java)
    }
    single<RefreshAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RefreshAPI::class.java)
    }
    single<AlarmCheckAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlarmCheckAPI::class.java)
    }
    single<ProfileAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileAPI::class.java)
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
    factory<LoginDataModel> {
        LoginDataImpl(get())
    }
    factory<RefreshDataModel> {
        RefreshDataImpl(get())
    }
    factory<AlarmCheckDataModel> {
        AlarmCheckDataImpl(get())
    }
    factory<ProfileDataModel> {
        ProfileDataImpl(get())
    }
}

var viewModelPart = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { AllowViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { BookmarkViewModel() }
    viewModel { StoreViewModel() }
    viewModel { MypageViewModel() }
    viewModel { EditProfileViewModel() }
    viewModel { GroupBottomViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)