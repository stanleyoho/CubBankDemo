package com.cub.hw.app.view.areaListFragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.cub.hw.app.BaseFragment
import com.cub.hw.app.R
import com.cub.hw.app.apiResponseModel.AreasDetailInformation
import com.cub.hw.app.apiResponseModel.ResultX
import com.cub.hw.app.enums.PageEnum
import com.cub.hw.app.view.areaDetailFragment.AreaDetailFragment
import kotlinx.android.synthetic.main.fragment_area_list.view.*
import kotlinx.android.synthetic.main.layout_area_detail.view.*
import kotlinx.coroutines.*

class AreaListFragment : BaseFragment(), AreaListContract.View {
    lateinit var recycler: RecyclerView
    lateinit var presenter: AreaListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AreaListPresenter(this)
        presenter.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_area_list, container, false)
        recycler = view.recyclerView
        recycler.layoutManager = LinearLayoutManager(mContext)
        return view
    }

    override fun updateRecycler(areaData: AreasDetailInformation) {

        val adapter = recycler.adapter as HomeFragmentAdapter?
        if (adapter == null) {
            recycler.adapter = HomeFragmentAdapter(mContext, areaData, object :
                HomeFragmentAdapter.ItemClickCallbackInterface {
                override fun onClick(data: ResultX) {
                    fragmentChangeListener.addFragment(
                        AreaDetailFragment(data),
                        PageEnum.AREA_DETAIL
                    )
                }
            })
        } else {
            adapter.update(areaData)
        }

    }

    class HomeFragmentAdapter(
        val context: Context,
        var data: AreasDetailInformation,
        val callback: ItemClickCallbackInterface
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return AreaDetailVH(
                LayoutInflater.from(context).inflate(R.layout.layout_area_detail, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val vh = holder as AreaDetailVH
            val data = data.result!!.results[position]
            Glide.with(context)
                .load(data.ePicUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(vh.iv)
            vh.areaName.text = data.eName
            vh.areaDescription.text = data.eInfo
            vh.item.setOnClickListener { callback.onClick(data) }
        }

        override fun getItemCount(): Int {
            return data.result!!.results.size
        }

        @SuppressLint("NotifyDataSetChanged")
        fun update(data: AreasDetailInformation) {
            this.data = data
            notifyDataSetChanged()
        }

        interface ItemClickCallbackInterface {
            fun onClick(data: ResultX)
        }

        class AreaDetailVH(itemView: View) : ViewHolder(itemView) {
            val iv = itemView.imagePic
            val areaName = itemView.textAreaName
            val areaDescription = itemView.textDescription
            val item = itemView.layout_item
        }
    }
}