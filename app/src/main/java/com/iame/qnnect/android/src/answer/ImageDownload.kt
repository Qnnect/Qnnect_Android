package com.iame.qnnect.android.src.answer
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
//
//import android.os.AsyncTask
//import android.os.Environment
//import androidx.test.core.app.ApplicationProvider
//import java.io.File
//import java.io.FileOutputStream
//import java.io.InputStream
//import java.lang.Exception
//import java.net.HttpURLConnection
//import java.net.URL
//import java.text.SimpleDateFormat
//import java.util.*
//import android.widget.Toast
//
//import android.graphics.drawable.Drawable
//
//import android.graphics.drawable.BitmapDrawable
//
//import android.graphics.Bitmap
//
//import com.bumptech.glide.request.target.CustomTarget
//
//import com.bumptech.glide.Glide
//
//import android.R
//import android.provider.Settings.System.getString
//
//
//class ImageDownload {
//    /**
//     * 파일명
//     */
//    private var fileName: String? = null
//
//    /**
//     * 저장할 폴더
//     */
//    private val SAVE_FOLDER = "/save_folder"
//
//    // File 에서 이미지를 불러올 때 안전하게 불러오기 위해서 만든 함수
//    // bitmap size exceeds VM budget 오류 방지용
////    fun SafeDecodeBitmapFile(imgUrl: String): String {
////        // 파일 선언 -> 경로는 파라미터에서 받는다
////        val targetDir: String =
////            Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER
////        val file = File("$targetDir/$fileName.jpg")
////
////        //다운로드 경로를 지정
////        val savePath: String =
////            Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER
////        val dir = File(savePath)
////
////        //상위 디렉토리가 존재하지 않을 경우 생성
////        if (!dir.exists()) {
////            dir.mkdirs()
////        }
////
////        //파일 이름 :날짜_시간
////        val day = Date()
////        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
////        fileName = java.lang.String.valueOf(sdf.format(day))
////
////        //다운로드 폴더에 동일한 파일명이 존재하는지 확인
////        val localPath = "$savePath/$fileName.jpg"
////
////        // 가로, 세로 최대 크기 (이보다 큰 이미지가 들어올 경우 크기를 줄인다.)
////        val IMAGE_MAX_SIZE: Int = 500
////        val bfo = BitmapFactory.Options()
////        bfo.inJustDecodeBounds = true
////        BitmapFactory.decodeFile(imgUrl, bfo)
////        if (bfo.outHeight * bfo.outWidth >= IMAGE_MAX_SIZE * IMAGE_MAX_SIZE) {
////            bfo.inSampleSize =
////                Math.pow(2.0, Math.round(Math.log(IMAGE_MAX_SIZE
////                        / Math.max(bfo.outHeight, bfo.outWidth)
////                    .toDouble()) / Math.log(0.5)) as Double)
////                    .toInt()
////        }
////        bfo.inJustDecodeBounds = false
////        bfo.inPurgeable = true
////        bfo.inDither = true
////        BitmapFactory.decodeFile(imgUrl, bfo)
//////        BitmapConvertFile(BitmapFactory.decodeFile(targetDir, bfo), localPath)
////        return localPath
////    }
//
//    //     비트맵을 파일로 변환하는 메소드
////    private fun BitmapConvertFile(bitmap: Bitmap, strFilePath: String) {
////        // OutputStream 선언 -> bitmap데이터를 OutputStream에 받아 File에 넣어주는 용도
////        var out: OutputStream? = null
////        try {
////            // 파일 초기화
////            file.createNewFile()
////
////            // OutputStream에 출력될 Stream에 파일을 넣어준다
////            out = FileOutputStream(file)
////
////            // bitmap 압축
////            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
////        } catch (e: Exception) {
////            e.printStackTrace()
////        } finally {
////            try {
////                out!!.close()
////            } catch (e: IOException) {
////                e.printStackTrace()
////            }
////        }
////    }
//
//}
//
//class ImageDownload : AsyncTask<String?, Void?, Void?>() {
//    /**
//     * 파일명
//     */
//    private var fileName: String? = null
//
//    /**
//     * 저장할 폴더
//     */
//    private val SAVE_FOLDER = "/save_folder"
//
//    override fun onPostExecute(result: Void?) {
//        super.onPostExecute(result)
//
//        //저장한 이미지 열기
//        val i = Intent(Intent.ACTION_VIEW)
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        val targetDir: String =
//            Environment.getExternalStorageDirectory().toString().toString() + SAVE_FOLDER
//        val file = File("$targetDir/$fileName.jpg")
//
//        //type 지정 (이미지)
//        i.setDataAndType(Uri.fromFile(file), "image/*")
//        //이미지 스캔해서 갤러리 업데이트
//        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file))
//    }
//
//    override fun doInBackground(vararg params: String?): Void? {
//
//        //다운로드 경로를 지정
//        val savePath: String =
//            Environment.getExternalStorageDirectory().toString().toString() + SAVE_FOLDER
//        val dir = File(savePath)
//
//        //상위 디렉토리가 존재하지 않을 경우 생성
//        if (!dir.exists()) {
//            dir.mkdirs()
//        }
//
//        //파일 이름 :날짜_시간
//        val day = Date()
//        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
//        fileName = java.lang.String.valueOf(sdf.format(day))
//
//        //웹 서버 쪽 파일이 있는 경로
//        val fileUrl = params[0]
//
//        //다운로드 폴더에 동일한 파일명이 존재하는지 확인
//        if (File("$savePath/$fileName").exists() === false) {
//        } else {
//        }
//        val localPath = "$savePath/$fileName.jpg"
//        try {
//            val imgUrl = URL(fileUrl)
//            //서버와 접속하는 클라이언트 객체 생성
//            val conn: HttpURLConnection = imgUrl.openConnection() as HttpURLConnection
//            val len: Int = conn.getContentLength()
//            val tmpByte = ByteArray(len)
//            //입력 스트림을 구한다
//            val `is`: InputStream = conn.getInputStream()
//            val file = File(localPath)
//            //파일 저장 스트림 생성
//            val fos = FileOutputStream(file)
//            var read: Int
//            //입력 스트림을 파일로 저장
//            while (true) {
//                read = `is`.read(tmpByte)
//                if (read <= 0) {
//                    break
//                }
//                fos.write(tmpByte, 0, read) //file 생성
//            }
//            `is`.close()
//            fos.close()
//            conn.disconnect()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }
//
//}