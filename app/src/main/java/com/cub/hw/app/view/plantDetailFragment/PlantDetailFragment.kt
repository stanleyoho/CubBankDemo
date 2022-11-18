package com.cub.hw.app.view.plantDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cub.hw.app.BaseFragment
import com.cub.hw.app.R
import com.cub.hw.app.apiResponseModel.ResultXXX
import kotlinx.android.synthetic.main.fragment_area_detail.view.*

class PlantDetailFragment(val data: ResultXXX) : BaseFragment() {
    lateinit var btnBack : ImageView
    lateinit var areaPic : ImageView
    lateinit var areaDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_plant_detail, container, false)
        btnBack = view.ivBack.apply {
            setOnClickListener { fragmentChangeListener.back() }
        }
        areaPic = view.ivAreaDetailImage.apply {
            Glide.with(mContext)
                .load(data.fPic01URL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
        areaDescription = view.textAreaDetailDescription.apply {
            text = data.fBrief
        }
        return view
    }
}