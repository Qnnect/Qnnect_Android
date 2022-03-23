package com.iame.qnnect.android.src.declare.model

import io.reactivex.Single

interface GetDeclareListDataModel {
    fun getData(): Single<List<DeclareUser>>
}

