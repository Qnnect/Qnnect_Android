package com.iame.qnnect.android.src.edit_alarm.model

import io.reactivex.Single

interface GetUserAlarmStatusDataModel {
    fun getAlarmStatus(): Single<Boolean>
}

