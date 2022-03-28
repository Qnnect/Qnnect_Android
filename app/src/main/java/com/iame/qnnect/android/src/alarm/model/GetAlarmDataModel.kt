package com.iame.qnnect.android.src.alarm.model

import io.reactivex.Single

interface GetAlarmDataModel {
    fun getAlarm(): Single<List<GetAlarmListResponse>>
}

