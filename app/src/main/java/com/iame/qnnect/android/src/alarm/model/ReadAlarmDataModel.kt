package com.iame.qnnect.android.src.alarm.model

import io.reactivex.Single

interface ReadAlarmDataModel {
    fun getAlarm(notificationId: Int): Single<Unit>
}

