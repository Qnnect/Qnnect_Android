package com.iame.qnnect.android.src.declare.service

import com.iame.qnnect.android.src.declare.model.DeleteDeclareDataModel
import io.reactivex.Single

class DeleteDeclareDataImpl(private val service: DeleteDeclareAPI) : DeleteDeclareDataModel {
    override fun getData(reportId: Int): Single<Unit> {
        return service.getDeleteDeclare(reportId)
    }
}