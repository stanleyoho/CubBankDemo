package com.cub.hw.app.view.areaDetailFragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cub.hw.app.BaseFragment
import com.cub.hw.app.R
import com.cub.hw.app.apiResponseModel.ResultX
import com.cub.hw.app.apiResponseModel.ResultXXX
import com.cub.hw.app.enums.PageEnum
import com.cub.hw.app.view.plantDetailFragment.PlantDetailFragment
import kotlinx.android.synthetic.main.fragment_area_detail.view.*
import kotlinx.android.synthetic.main.layout_area_detail.view.*

class AreaDetailFragment(val data : ResultX) : BaseFragment(),AreaDetailContract.View {
    lateinit var btnBack : ImageView
    lateinit var areaPic : ImageView
    lateinit var areaDescription : TextView
    lateinit var plantsRecycler : RecyclerView
    lateinit var presenter : AreaDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AreaDetailPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_area_detail, container, false)
        btnBack = view.ivBack.apply { 
            setOnClickListener { fragmentChangeListener.back() }
        }
        areaPic = view.ivAreaDetailImage.apply { 
            Glide.with(mContext)
                .load(data.ePicUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
        areaDescription = view.textAreaDetailDescription.apply {
            text = data.eInfo
        }
        plantsRecycler = view.recyclerPlants
        return view
    }

    override fun onStart() {
        super.onStart()
        plantsRecycler.layoutManager = LinearLayoutManager(mContext)
        presenter.getPlantsData(data.eName)
    }

    override fun updateRecycler(areaData: ArrayList<ResultXXX>) {
        val adapter = plantsRecycler.adapter as AreaDetailFragmentAdapter?
        if(adapter == null){
            plantsRecycler.adapter = AreaDetailFragmentAdapter(mContext,areaData,object :
                AreaDetailFragmentAdapter.ItemClickCallbackInterface {
                override fun onClick(data: ResultXXX) {
                    fragmentChangeListener.addFragment(
                        PlantDetailFragment(data),
                        PageEnum.PLANT_DETAIL
                    )
                }
            })
        }else{
            adapter.update(areaData)
        }
    }

    class AreaDetailFragmentAdapter(
        val context: Context,
        var data: List<ResultXXX>,
        val callback: ItemClickCallbackInterface
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return PlantVH(
                LayoutInflater.from(context).inflate(R.layout.layout_area_detail, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val vh = holder as PlantVH
            val data = data[position]
            Glide.with(context)
                .load(data.fPic01URL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(vh.iv)
            vh.areaName.text = data.FNameCh
            vh.areaDescription.text = data.fBrief
            vh.item.setOnClickListener { callback.onClick(data) }
        }

        override fun getItemCount(): Int {
            return data.size
        }

        @SuppressLint("NotifyDataSetChanged")
        fun update(data: List<ResultXXX>) {
            this.data = data
            notifyDataSetChanged()
        }

        interface ItemClickCallbackInterface {
            fun onClick(data: ResultXXX)
        }

        class PlantVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val iv = itemView.imagePic
            val areaName = itemView.textAreaName
            val areaDescription = itemView.textDescription
            val item = itemView.layout_item
            val goTo = itemView.imageView.apply {
                visibility = View.GONE
            }
        }
    }
}