package com.transformations.sample.ui.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.transformations.sample.data.remote.example.FetchDetailsRepo
import com.transformations.sample.data.remote.model.Resource

class FetchDetailsViewModel(
    private val fetchDetailsRepo: FetchDetailsRepo
) : ViewModel() {

    val selectedBreedLiveData = MutableLiveData<Int>()

    fun fetchDogBreedList(): LiveData<List<String>> {
        return Transformations.map(fetchDetailsRepo.fetchDetails()) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.map { it.name }
                }
                Resource.Status.ERROR -> {
                    // Handle Error
                    null
                }
            }
        }
    }

    fun fetchBreedDetails(): LiveData<String> {
        return Transformations.switchMap(selectedBreedLiveData)
        {
            makeDetailsApiCall(it + 1)
            // Make any additional transformations here, if needed
        }
    }

    private fun makeDetailsApiCall(id: Int): LiveData<String> {
        return Transformations.map(fetchDetailsRepo.fetchBreedDetails(id)) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    "-------Details-------\n" +
                    "Name: ${it.data?.name}\n" +
                    "Bred For: ${it.data?.bredFor}\n" +
                    "Breed Group: ${it.data?.breedGroup}\n" +
                    "LifeSpan: ${it.data?.lifeSpan}\n"
                }
                Resource.Status.ERROR -> {
                    // Handle Error
                    null
                }
            }
        }
    }
}