package com.cub.hw.app.view.areaListFragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.util.Log
import com.cub.hw.app.InstanceData
import com.cub.hw.app.tasks.ApiService
import com.cub.hw.app.utils.ApiClientUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AreaListPresenter(val view: AreaListContract.View) : AreaListContract.Presenter {
    override fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val areaDataResponse =
                    ApiClientUtils.client.create(ApiService::class.java).GetTaipeiZooAreasData()
                        .await()
                val areaData = areaDataResponse.body()

                withContext(Dispatchers.Main) {
                    view.updateRecycler(areaData!!)
                }

                val plantsDataResponse =
                    ApiClientUtils.client.create(ApiService::class.java).GetTaipeiZooPlantData()
                        .await()
                val plantsData = plantsDataResponse.body()
                InstanceData.plantsData = plantsData?.result?.results

                Log.d("stanely", "areaData : $areaData")
                Log.d("stanely", "plantsData : $plantsData")
            } catch (e: java.lang.Exception) {
                withContext(Dispatchers.Main) {
                    view.showErrorMsg()
                }
            }
        }
    }
}