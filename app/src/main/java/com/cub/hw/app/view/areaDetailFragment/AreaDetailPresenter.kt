package com.cub.hw.app.view.areaDetailFragment

import com.cub.hw.app.InstanceData
import com.cub.hw.app.apiResponseModel.ResultXXX

class AreaDetailPresenter(val view : AreaDetailContract.View) : AreaDetailContract.Presenter{
    override fun getPlantsData(name:String) {
        val resultList = ArrayList<ResultXXX>()
        val plants = InstanceData.plantsData
        if(plants != null){
            for (item in plants){
                if(item.fLocation.contains(name)){
                    resultList.add(item)
                }
            }
        }
        view.updateRecycler(resultList)
    }
}