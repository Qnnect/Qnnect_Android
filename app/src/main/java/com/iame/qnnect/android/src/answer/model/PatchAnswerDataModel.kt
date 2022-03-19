package com.iame.qnnect.android.src.answer.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part
import java.io.File

interface PatchAnswerDataModel {
    fun getData(@Part image5: MultipartBody.Part?,
                @Part image4: MultipartBody.Part?,
                @Part image3: MultipartBody.Part?,
                @Part image2: MultipartBody.Part?,
                @Part image1: MultipartBody.Part?,
                @Part content: MultipartBody.Part?,
                cafeQuestionId: Int): Single<Unit>
}

//@SerializedName("image5") val image5: File,
//@SerializedName("image4") val image4: File,
//@SerializedName("image3") val image3: File,
//@SerializedName("image2") val image2: File,
//@SerializedName("image1") val image1: File,
//@SerializedName("content") val content: String
// @SerializedName("cafeId") val cafeId: Int,
//    @SerializedName("cafeQuestionId") val cafeQuestionId: Int