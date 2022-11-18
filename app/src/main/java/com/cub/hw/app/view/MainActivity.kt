package com.cub.hw.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.manager.RequestManagerFragment
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.cub.hw.app.BaseFragment
import com.cub.hw.app.R
import com.cub.hw.app.enums.PageEnum
import com.cub.hw.app.view.areaDetailFragment.AreaDetailFragment
import com.cub.hw.app.view.areaListFragment.AreaListFragment
import com.cub.hw.app.view.plantDetailFragment.PlantDetailFragment

class MainActivity : AppCompatActivity(), BaseFragment.FragmentChangeInterface {
    lateinit var fragmentManager: FragmentManager
    lateinit var areaListFragment: AreaListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        areaListFragment = AreaListFragment()
        fragmentManager = supportFragmentManager
        addFragment(areaListFragment,PageEnum.HOME)
    }

    override fun addFragment(fragment: Fragment, pageName: PageEnum) {
        val trans = fragmentManager.beginTransaction()
        trans.add(R.id.frame_layout, fragment, pageName.name)
            .addToBackStack(null)
        trans.commit()
    }

    override fun removeFragment(fragment: Fragment) {
        val trans = fragmentManager.beginTransaction()
        trans.remove(fragment)
        trans.commit()
    }

    override fun back() {
        fragmentManager.popBackStack()
    }

    override fun closeApp() {
        finish()
    }

    override fun onBackPressed() {
        val backStackEntryCount = fragmentManager.backStackEntryCount
        if(backStackEntryCount == 1 ){
            finish()
        }else{
            super.onBackPressed()
        }
    }
}