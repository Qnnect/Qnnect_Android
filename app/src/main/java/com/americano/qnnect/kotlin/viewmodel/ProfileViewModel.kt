package com.americano.qnnect.kotlin.viewmodel

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.PermissionRequest
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseViewModel
import com.americano.qnnect.kotlin.model.DataModel
import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import com.americano.qnnect.kotlin.src.profile.ProfileActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.*
import java.util.*
import androidx.databinding.ObservableField




class ProfileViewModel() : BaseViewModel() {

    private val TAG = "ProfileViewModel"

    var path = ""
    private val IMAGE_DIRECTORY = "/demonuts_upload_gallery"
    private val BUFFER_SIZE = 1024 * 2

    // 접근 권한
    fun requestMultiplePermissions(activity: ProfileActivity) {
        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }
            }).withErrorListener {
            }
            .onSameThread()
            .check()
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