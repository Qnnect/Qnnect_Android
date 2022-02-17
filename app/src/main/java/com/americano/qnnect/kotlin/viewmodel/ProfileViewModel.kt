package com.americano.qnnect.kotlin.viewmodel

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.PermissionRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseViewModel
import com.americano.qnnect.kotlin.model.DataModel
import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.*
import java.util.*

class ProfileViewModel() : BaseViewModel() {

    private val TAG = "ProfileViewModel"

    requestMultiplePermissions()
    val intent = Intent(Intent.ACTION_PICK)
    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
    startActivityForResult(intent, GET_GALLERY_IMAGE)

    // 접근 권한
    private fun requestMultiplePermissions() {
        Dexter.withActivity(this)
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
                    permissions: MutableList<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
            }
            .onSameThread()
            .check()
    }

    // 사진 가져오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri: Uri? = data.data

            val uri: Uri = data.getData()!!
            path = getFilePathFromURI(this, uri)

            Glide.with(this)
                .load(selectedImageUri)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(binding.userImg)

            binding.nextBtn.setBackgroundResource(R.drawable.next_btn_custom)
            img_check = true
//            imageview.setImageURI(selectedImageUri)
        }
    }

    // file uri
    fun getFilePathFromURI(context: Context, contentUri: Uri?): String {
        //copy file and send new file path
        val wallpaperDirectory = File(getExternalFilesDir(null).toString() + IMAGE_DIRECTORY)

//        File path = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File file = new File(path, "DemoPicture.jpg");


//        getExternalCacheDir()
//        var path: File = this.getExternalFilesDir(null)!!
//        var wallpaperDirectory = File(path.absolutePath.toString()+IMAGE_DIRECTORY)
//        wallpaperDirectory.mkdirs()

//        val file: File = File(wallpaperDirectory)
//        wallpaperDirectory.mkdirs()

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