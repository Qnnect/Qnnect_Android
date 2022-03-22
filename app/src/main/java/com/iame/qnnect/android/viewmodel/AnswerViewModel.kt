package com.iame.qnnect.android.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.answer.model.PostAnswerDataModel
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.main.home.model.UserDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import java.io.*
import java.util.*

class AnswerViewModel(private val model: PostAnswerDataModel,
                      private val model2: UserDataModel,
                      private val model3: GetReplyDataModel) : BaseViewModel() {

    private val TAG = "AnswerViewModel"

    var path = ""
    private val IMAGE_DIRECTORY = "/demonuts_upload_gallery"
    private val BUFFER_SIZE = 1024 * 2

    // get reply
    private val getReplyResponse = MutableLiveData<GetReplyResponse>()
    val replyResponse: LiveData<GetReplyResponse>
        get() = getReplyResponse

    fun getReply(commentId: Int) {
        addDisposable(model3.getData(commentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getReplyResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    // get user
    private val getUserResponse = MutableLiveData<GetUserResponse>()
    val userResponse: LiveData<GetUserResponse>
        get() = getUserResponse

    fun getUser() {
        addDisposable(model2.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getUserResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    private val postAnswereResponse = MutableLiveData<Int>()
    val answerResponse: LiveData<Int>
        get() = postAnswereResponse

    private val BadResponse = MutableLiveData<String>()
    val badResponse: LiveData<String>
        get() = BadResponse

    fun postAnswer(image5: MultipartBody.Part?,
                     image4: MultipartBody.Part?,
                     image3: MultipartBody.Part?,
                     image2: MultipartBody.Part?,
                     image1: MultipartBody.Part?,
                     content: MultipartBody.Part?,
                     cafeQuestionId: Int) {
        addDisposable(model.getData(image5, image4, image3, image2, image1, content, cafeQuestionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postAnswereResponse.postValue(this)
                }
            }, {
                BadResponse.postValue("질문을 답변할 수 있는 기간이 지났습니다")
                Log.d(TAG, "response error, message : ${it.message}")

            })
        )
    }

    // file uri
    fun getFilePathFromURI(context: Context, contentUri: Uri?): String {
        //copy file and send new file path
        val wallpaperDirectory = File(context.getExternalFilesDir(null).toString() + IMAGE_DIRECTORY)

        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }
        val copyFile = File(
//            +".mp4"
            wallpaperDirectory.toString() + File.separator + Calendar.getInstance()
                .getTimeInMillis().toString() + ".jpg"
        )
        // create folder if not exists
        copy(context, contentUri, copyFile)
        Log.d("vPath--->", copyFile.getAbsolutePath())
        return copyFile.getAbsolutePath()
    }

    fun copy(context: Context, srcUri: Uri?, dstFile: File?) {
        try {
            val inputStream = context.contentResolver.openInputStream(srcUri!!)
                ?: return
            val outputStream: OutputStream = FileOutputStream(dstFile)
            copystream(inputStream, outputStream)
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class, IOException::class)
    fun copystream(input: InputStream?, output: OutputStream?): Int {
        val buffer = ByteArray(BUFFER_SIZE)
        val `in` = BufferedInputStream(input, BUFFER_SIZE)
        val out = BufferedOutputStream(output, BUFFER_SIZE)
        var count = 0
        var n = 0
        try {
            while (`in`.read(buffer, 0, BUFFER_SIZE).also { n = it } != -1) {
                out.write(buffer, 0, n)
                count += n
            }
            out.flush()
        } finally {
            try {
                out.close()
            } catch (e: IOException) {
                Log.e(e.message, java.lang.String.valueOf(e))
            }
            try {
                `in`.close()
            } catch (e: IOException) {
                Log.e(e.message, java.lang.String.valueOf(e))
            }
        }
        return count
    }
}
