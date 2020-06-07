package com.transformations.sample.data.remote.example

import androidx.lifecycle.LiveData
import com.transformations.sample.data.remote.model.Resource
import com.transformations.sample.data.remote.model.example.BreedDetail
import com.transformations.sample.data.remote.model.example.DogBreed
import com.transformations.sample.extensions.asLiveData

class FetchDetailsRepo(private val fetchDetailsAPI: FetchDetailsAPI) {
    fun fetchDetails(): LiveData<Resource<DogBreed>> {
        return fetchDetailsAPI.fetchBreedList().asLiveData()
    }
    fun fetchBreedDetails(id: Int): LiveData<Resource<BreedDetail>> {
        return fetchDetailsAPI.fetchBreedDetails(id).asLiveData()
    }
}