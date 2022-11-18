package com.cub.hw.app.view.areaDetailFragment

import com.cub.hw.app.apiResponseModel.ResultXXX

class AreaDetailContract {
    interface View{
        fun updateRecycler(areaData : ArrayList<ResultXXX>)
    }

    interface Presenter{
        fun getPlantsData(name:String)
    }
}