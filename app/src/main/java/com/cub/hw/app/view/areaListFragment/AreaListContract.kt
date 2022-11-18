package com.cub.hw.app.view.areaListFragment

import com.cub.hw.app.apiResponseModel.AreasDetailInformation

class AreaListContract {
    interface View{
        fun updateRecycler(areaData : AreasDetailInformation)
    }

    interface Presenter{
        fun getData()
    }
}