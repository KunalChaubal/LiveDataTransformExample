package com.transformations.sample.ui.main

import android.os.Bundle
import com.transformations.sample.R
import com.transformations.sample.base.DataBindingActivity
import com.transformations.sample.databinding.ActivityMainBinding
import com.transformations.sample.extensions.addFragment
import com.transformations.sample.ui.example.FetchDetailsFragment
import org.koin.android.ext.android.inject

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    private val mainVM by inject<MainVM>()

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb.vm = mainVM

        addFragment(
            FetchDetailsFragment.newInstance(),
            vb.container.id,
            FetchDetailsFragment.TAG
        )
    }
}