package com.iame.qnnect.android.di

import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.MyConstant.Companion.BASE_URL
import com.iame.qnnect.android.base.NullOnEmptyConverterFactory
import com.iame.qnnect.android.base.XAccessTokenInterceptor
import com.iame.qnnect.android.model.DataModel
import com.iame.qnnect.android.model.DataModelImpl
import com.iame.qnnect.android.model.service.KakaoSearchService
import com.iame.qnnect.android.src.alarm.AlarmAdapter
import com.iame.qnnect.android.src.alarm.model.GetAlarmDataModel
import com.iame.qnnect.android.src.alarm.service.GetAlarmAPI
import com.iame.qnnect.android.src.alarm.service.GetAlarmDataImpl
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.service.AlarmCheckAPI
import com.iame.qnnect.android.src.allow.service.AlarmCheckDataImpl
import com.iame.qnnect.android.src.answer.EditImageAdapter
import com.iame.qnnect.android.src.answer.model.PostAnswerDataModel
import com.iame.qnnect.android.src.answer.service.*
import com.iame.qnnect.android.src.declare.DeclareAdapter
import com.iame.qnnect.android.src.declare.model.DeleteDeclareDataModel
import com.iame.qnnect.android.src.declare.model.GetDeclareListDataModel
import com.iame.qnnect.android.src.declare.model.PostDeclareDataModel
import com.iame.qnnect.android.src.declare.service.*
import com.iame.qnnect.android.src.diary.AnswerAdapter
import com.iame.qnnect.android.src.diary.model.*
import com.iame.qnnect.android.src.diary.service.*
import com.iame.qnnect.android.src.diary.service.GetQuestionAPI
import com.iame.qnnect.android.src.diary.service.GetQuestionDataImpl
import com.iame.qnnect.android.src.drink.DrinkUserAdapter
import com.iame.qnnect.android.src.drink.model.GetUserDrinkDataModel
import com.iame.qnnect.android.src.drink.service.GetUserDrinkAPI
import com.iame.qnnect.android.src.drink.service.GetUserDrinkImpl
import com.iame.qnnect.android.src.edit_drink.MyRecipeAdapter
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.PostEditDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.service.GetCurrentDrinkAPI
import com.iame.qnnect.android.src.edit_drink.service.GetCurrentDrinkImpl
import com.iame.qnnect.android.src.edit_drink.service.PostEditDrinkAPI
import com.iame.qnnect.android.src.edit_drink.service.PostEditDrinkImpl
import com.iame.qnnect.android.src.edit_question.model.EditQuestionDataModel
import com.iame.qnnect.android.src.edit_question.service.EditQuestionAPI
import com.iame.qnnect.android.src.edit_question.service.EditQuestionImpl
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
import com.iame.qnnect.android.src.main.mypage.model.DeleteUserDataModel
import com.iame.qnnect.android.src.main.mypage.model.LogoutUserDataModel
import com.iame.qnnect.android.src.main.mypage.service.DeleteUserAPI
import com.iame.qnnect.android.src.main.mypage.service.DeleteUserDataImpl
import com.iame.qnnect.android.src.main.mypage.service.LogoutUserAPI
import com.iame.qnnect.android.src.main.mypage.service.LogoutUserDataImpl
import com.iame.qnnect.android.src.main.store.RecipeAdapter
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.model.ProfileDefaultDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.profile.service.ProfileDataImpl
import com.iame.qnnect.android.src.profile.service.ProfileDefaultAPI
import com.iame.qnnect.android.src.profile.service.ProfileDefaultDataImpl
import com.iame.qnnect.android.src.question.model.GetUserQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.SearchQuestionDataModel
import com.iame.qnnect.android.src.question.service.*
import com.iame.qnnect.android.src.recipe.model.GetRecipeDataModel
import com.iame.qnnect.android.src.recipe.service.GetRecipeAPI
import com.iame.qnnect.android.src.recipe.service.GetRecipeDataImpl
import com.iame.qnnect.android.src.reply.ImageAdapter
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.src.reply.model.DeleteAnswerDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyDataModel
import com.iame.qnnect.android.src.reply.model.PostReplyDataModel
import com.iame.qnnect.android.src.reply.reply_more.model.EditReplyDataModel
import com.iame.qnnect.android.src.reply.reply_more.service.EditReplyAPI
import com.iame.qnnect.android.src.reply.reply_more.service.EditReplyDataImpl
import com.iame.qnnect.android.src.reply.service.*
import com.iame.qnnect.android.src.search.model.SearchDataModel
import com.iame.qnnect.android.src.search.service.SearchAPI
import com.iame.qnnect.android.src.search.service.SearchDataImpl
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import com.iame.qnnect.android.src.splash.service.RefreshAPI
import com.iame.qnnect.android.src.splash.service.RefreshDataImpl
import com.iame.qnnect.android.src.store.MaterialAdapter
import com.iame.qnnect.android.src.store.model.GetMyMaterialAllDataModel
import com.iame.qnnect.android.src.store.model.GetMyMaterialDataModel
import com.iame.qnnect.android.src.store.model.PostBuyMaterialDataModel
import com.iame.qnnect.android.src.store.service.*
import com.iame.qnnect.android.viewmodel.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * MyModule.kt
 */

/*
   * bearer 토큰 필요한 api 사용시 accessToken유효한지 검사
   * 유효하지 않다면 재발급 api 호출
   * refreshToken이 유효하다면 정상적으로 accessToken재발급 후 기존 api 동작 완료
   *
*/
class BearerInterceptor: Interceptor {
    //todo 조건 분기로 인터셉터 구조 변경
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var Baseresponse = chain.request()
        if(Baseresponse.method == "HTTP 403 "){
            var accessToken = MyApplication.sSharedPreferences.getString("X-ACCESS-TOKEN", null)
            var refreshToken = MyApplication.sSharedPreferences.getString("refresh-token", null)
            val response = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RefreshAPI::class.java)

            var request = PostRefreshRequest(accessToken!!, refreshToken!!)
            var result = response.postRefresh(request)

            var editor = MyApplication.editor
            editor.putString("X-ACCESS-TOKEN", result.blockingGet().accessToken)
            editor.putString("refresh-token", result.blockingGet().refreshToken)
            editor.commit()

            accessToken = result.blockingGet().accessToken

            val newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer ${accessToken}")
                .build()
            return chain.proceed(newRequest)
        }
        else{
            val response = chain.request()
            return chain.proceed(chain.request())
        }
    }
}

val client: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(5000, TimeUnit.MILLISECONDS)
    .connectTimeout(5000, TimeUnit.MILLISECONDS)
    // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
    .addInterceptor(BearerInterceptor())
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
    single<ProfileDefaultAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileDefaultAPI::class.java)
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
    // text/plain 방식
    single<PostQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
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
    single<PostLikeAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostLikeAPI::class.java)
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
    single<GetReplyAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetReplyAPI::class.java)
    }
    // text/plain 방식
    single<PostReplyAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostReplyAPI::class.java)
    }
    // text/plain 방식
    single<EditReplyAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EditReplyAPI::class.java)
    }
    single<GetCurrentDrinkAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetCurrentDrinkAPI::class.java)
    }
    single<PostEditDrinkAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostEditDrinkAPI::class.java)
    }
    single<PostBuyMaterialAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostBuyMaterialAPI::class.java)
    }
    single<GetMyMaterialAllAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetMyMaterialAllAPI::class.java)
    }
    single<GetMyMaterialAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetMyMaterialAPI::class.java)
    }
    single<GetUserDrinkAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetUserDrinkAPI::class.java)
    }
    single<DeleteAnswerAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeleteAnswerAPI::class.java)
    }
    single<com.iame.qnnect.android.src.question.service.GetQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.iame.qnnect.android.src.question.service.GetQuestionAPI::class.java)
    }
    single<SearchQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchQuestionAPI::class.java)
    }
    single<GetRecipeAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetRecipeAPI::class.java)
    }
    single<DeleteQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeleteQuestionAPI::class.java)
    }
    single<DeleteUserAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeleteUserAPI::class.java)
    }
    single<PostDeclareAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostDeclareAPI::class.java)
    }
    single<GetDeclareListDrinkAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetDeclareListDrinkAPI::class.java)
    }
    single<DeleteDeclareAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeleteDeclareAPI::class.java)
    }
    single<UserQuestionAllAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserQuestionAllAPI::class.java)
    }
    // text/plain 방식
    single<EditQuestionAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EditQuestionAPI::class.java)
    }
    single<LogoutUserAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LogoutUserAPI::class.java)
    }
    single<GetAlarmAPI> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetAlarmAPI::class.java)
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
    factory {
        ReplyAdapter()
    }
    factory {
        ImageAdapter()
    }
    factory { 
        MyRecipeAdapter() 
    }
    factory {
        DrinkUserAdapter()
    }
    factory {
        EditImageAdapter()
    }
    factory {
        DeclareAdapter()
    }
    factory {
        MaterialAdapter()
    }
    factory {
        AlarmAdapter()
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
    factory<ProfileDefaultDataModel> {
        ProfileDefaultDataImpl(get())
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
    factory<PostLikeDataModel> {
        PostLikeDataImpl(get())
    }
    factory<GetQuestionDataModel> {
        GetQuestionDataImpl(get())
    }
    factory<PostAnswerDataModel> {
        PostAnswerDataImpl(get())
    }
    factory<GetReplyDataModel> {
        GetReplyDataImpl(get())
    }
    factory<PostReplyDataModel> {
        PostReplyDataImpl(get())
    }
    factory<EditReplyDataModel> {
        EditReplyDataImpl(get())
    }
    factory<GetCurrentDrinkDataModel> {
        GetCurrentDrinkImpl(get())
    }
    factory<PostEditDrinkDataModel> {
        PostEditDrinkImpl(get())
    }
    factory<PostBuyMaterialDataModel> {
        PostBuyMaterialImpl(get())
    }
    factory<GetMyMaterialAllDataModel> {
        GetMyMaterialAllImpl(get())
    }
    factory<GetMyMaterialDataModel> {
        GetMyMaterialImpl(get())
    }
    factory<GetUserDrinkDataModel> {
        GetUserDrinkImpl(get())
    }
    factory<DeleteAnswerDataModel> {
        DeleteAnswerDataImpl(get())
    }
    factory<com.iame.qnnect.android.src.question.model.GetQuestionDataModel> {
        com.iame.qnnect.android.src.question.service.GetQuestionDataImpl(get())
    }
    factory<SearchQuestionDataModel> {
        SearchQuestionDataImpl(get())
    }
    factory<GetRecipeDataModel> {
        GetRecipeDataImpl(get())
    }
    factory<EditQuestionDataModel> {
        EditQuestionImpl(get())
    }
    factory<DeleteQuestionDataModel> {
        DeleteQuestionDataImpl(get())
    }
    factory<DeleteUserDataModel> {
        DeleteUserDataImpl(get())
    }
    factory<PostDeclareDataModel> {
        PostDeclareDataImpl(get())
    }
    factory<GetDeclareListDataModel> {
        GetDeclareListDataImpl(get())
    }
    factory<DeleteDeclareDataModel> {
        DeleteDeclareDataImpl(get())
    }
    factory<GetUserQuestionDataModel> {
        UserQuestionDataImpl(get())
    }
    factory<LogoutUserDataModel> {
        LogoutUserDataImpl(get())
    }
    factory<GetAlarmDataModel> {
        GetAlarmDataImpl(get())
    }
}

var viewModelPart = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { AllowViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { EditProfileViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MypageViewModel(get(), get(), get()) }
    viewModel { GroupViewModel(get()) }
    viewModel { BookmarkViewModel(get(), get(), get())}
    viewModel { QuestionViewModel(get())}
    viewModel { SearchViewModel(get())}
    viewModel { DiaryViewModel(get(), get(), get(), get(), get(), get(), get())}
    viewModel { AnswerViewModel(get(), get(), get()) }
    viewModel { ReplyViewModel(get(), get(), get(), get()) }
    viewModel { EditReplyViewModel(get()) }
    viewModel { EditDrinkViewModel(get(), get()) }
    viewModel { StoreViewModel(get() ) }
    viewModel { StoreActivityViewModel(get() ) }
    viewModel { MyMaterialViewModel(get(), get()) }
    viewModel { DrinkViewModel(get() ) }
    viewModel { QuestionListViewModel(get(), get())}
    viewModel { SearchQuestionViewModel(get())}
    viewModel { RecipeViewModel(get()) }
    viewModel { EditQuestionViewModel(get()) }
    viewModel { UserDeclareViewModel(get(), get()) }
    viewModel { AlarmViewModel(get()) }

    viewModel { OnboardViewModel() }
    viewModel { FinishDrinkViewModel() }
    viewModel { EditAlarmViewModel() }
    viewModel { EmptyViewModel() }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)