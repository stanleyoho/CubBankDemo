package com.cub.hw.app

import android.content.Context
import androidx.fragment.app.Fragment
import com.cub.hw.app.enums.PageEnum

open class BaseFragment : Fragment() {
    lateinit var mContext : Context
    lateinit var fragmentChangeListener : FragmentChangeInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            this.fragmentChangeListener = context as FragmentChangeInterface
            this.mContext = context
        }catch (e:ClassCastException){
            throw ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener")
        }
    }

    interface FragmentChangeInterface{
        fun addFragment(fragment:Fragment,pageName:PageEnum)
        fun removeFragment(fragment:Fragment)
        fun back()
    }
}