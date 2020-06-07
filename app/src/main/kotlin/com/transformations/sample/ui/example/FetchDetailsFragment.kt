package com.transformations.sample.ui.example

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.transformations.sample.R
import com.transformations.sample.base.DataBindingFragment
import com.transformations.sample.databinding.FragmentFetchDetailsBinding
import org.koin.android.ext.android.inject


class FetchDetailsFragment : DataBindingFragment<FragmentFetchDetailsBinding>() {

    private val vm by inject<FetchDetailsViewModel>()

    companion object {
        const val TAG = "FetchDogsFragment"
        fun newInstance() =
            FetchDetailsFragment()
    }

    override fun layoutId() = R.layout.fragment_fetch_details

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vb.vm = vm
        initObservers()
        initClickListener()
    }

    private fun initClickListener() {
        vb.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                vm.selectedBreedLiveData.value = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun initObservers() {
        vm.fetchDogBreedList().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val aa =
                    ArrayAdapter<Any>(requireContext(), android.R.layout.simple_spinner_item, it)
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                vb.spinner.adapter = aa
            }
        })

        vm.fetchBreedDetails().observe(viewLifecycleOwner, Observer {
            vb.tvDetails.text = it
        })
    }
}
