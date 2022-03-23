package com.iame.qnnect.android.src.declare.model

import io.reactivex.Single

interface DeleteDeclareDataModel {
    fun getData(reportId: Int): Single<Unit>
}

