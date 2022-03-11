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
import com.iame.qnnect.android.src.answer.model.PostAnswerDataModel
import com.iame.qnnect.android.src.answer.service.PostAnswerAPI
import com.iame.qnnect.android.src.answer.service.PostAnswerDataImpl
import com.iame.qnnect.android.src.diary.AnswerAdapter
import com.iame.qnnect.android.src.diary.model.DeleteScrapDataModel
import com.iame.qnnect.android.src.diary.model.GetQuestionDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.diary.service.*
import com.iame.qnnect.android.src.group.member.GroupMemberAdapter
import com.iame.qnnect.android.src.group.model.GroupDataModel
import com.iame.qnnect.android.src.group.question.GroupQuestionViewPagerAdapter
import com.iame.qnnect.android.src.group.service.GroupAPI
import com.iame.qnnect.android.src.group.service.GroupDataImpl
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.service.LoginAPI
import com.iame.qnnect.android.src.login.service.LoginDataImpl
import com.iame.qnnect.android.src.main.bookmark.GroupnameAdapter
import com.iame.qnnect.android.src.main.bookmark.QuestionListAdapter
import com.iame.qnnect.android.src.main.bookmark.model.BookmarkAllDataModel
import com.iame.qnnect.android.src.main.bookmark.model.BookmarkListDataModel
import com.iame.qnnect.android.src.main.bookmark.model.CafeListDataModel
import com.iame.qnnect.android.src.main.bookmark.service.*
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.QuestionRecyclerViewAdapter
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import com.iame.qnnect.android.src.main.home.home_service.HomeAPI
import com.iame.qnnect.android.src.main.home.home_service.HomeDataImpl
import com.iame.qnnect.android.src.main.home.model.UserDataModel
import com.iame.qnnect.android.src.main.home.service.UserAPI
import com.iame.qnnect.android.src.main.home.service.UserDataImpl
import com.iame.qnnect.android.src.main.store.RecipeAdapter
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.profile.service.ProfileDataImpl
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.service.PostQuestionAPI
import com.iame.qnnect.android.src.question.service.PostQuestionDataImpl
import com.iame.qnnect.android.src.search.model.SearchDataModel
import com.iame.qnnect.android.src.search.service.SearchAPI
import com.iame.qnnect.android.src.search.service.SearchDataImpl
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
    single<UserAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
    }
    single<GroupAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GroupAPI::class.java)
    }
    single<HomeAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeAPI::class.java)
    }
    single<CafeListAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CafeListAPI::class.java)
    }
    single<BookmarkListAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookmarkListAPI::class.java)
    }
    single<PostQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostQuestionAPI::class.java)
    }
    single<SearchAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchAPI::class.java)
    }
    single<BookmarkAllAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookmarkAllAPI::class.java)
    }
    single<PostScrapAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostScrapAPI::class.java)
    }
    single<DeleteScrapAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeleteScrapAPI::class.java)
    }
    single<GetQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetQuestionAPI::class.java)
    }
    single<PostAnswerAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostAnswerAPI::class.java)
    }
}

var adapterPart = module {
    factory {
        MainSearchRecyclerViewAdapter()
    }
    factory {
        GroupQuestionViewPagerAdapter()
    }
    factory {
        GroupMemberAdapter()
    }
    factory {
        RecipeAdapter()
    }
    factory {
        QuestionRecyclerViewAdapter()
    }
    factory {
        GroupAdapter()
    }
    factory {
        GroupnameAdapter()
    }
    factory {
        QuestionListAdapter()
    }
    factory {
        AnswerAdapter()
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
    factory<UserDataModel> {
        UserDataImpl(get())
    }
    factory<GroupDataModel> {
        GroupDataImpl(get())
    }
    factory<HomeDataModel> {
        HomeDataImpl(get())
    }
    factory<CafeListDataModel> {
        CafeListDataImpl(get())
    }
    factory<BookmarkListDataModel> {
        BookmarkListDataImpl(get())
    }
    factory<PostQuestionDataModel> {
        PostQuestionDataImpl(get())
    }
    factory<SearchDataModel> {
        SearchDataImpl(get())
    }
    factory<BookmarkAllDataModel> {
        BookmarkAllDataImpl(get())
    }
    factory<PostScrapDataModel> {
        PostScrapDataImpl(get())
    }
    factory<DeleteScrapDataModel> {
        DeleteScrapDataImpl(get())
    }
    factory<GetQuestionDataModel> {
        GetQuestionDataImpl(get())
    }
    factory<PostAnswerDataModel> {
        PostAnswerDataImpl(get())
    }
}

var viewModelPart = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { AllowViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MypageViewModel(get()) }
    viewModel { GroupViewModel(get()) }
    viewModel { BookmarkViewModel(get(), get(), get())}
    viewModel { QuestionViewModel(get())}
    viewModel { SearchViewModel(get())}
    viewModel { DiaryViewModel(get(), get(), get(), get())}
    viewModel { AnswerViewModel(get(), get()) }

    viewModel { StoreViewModel() }
    viewModel { DrinkViewModel() }
    viewModel { EditDrinkViewModel() }
    viewModel { OnboardViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)