package com.iame.qnnect.android.base

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * 비어있는(length=0)인 Response를 받았을 경우 처리
 */
class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation?>?,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter { body -> if (body.contentLength() == 0L) null else delegate.convert(body) }
    }
}