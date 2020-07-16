package com.transformations.sample.ui.example

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.transformations.sample.data.remote.example.FetchDetailsRepo
import com.transformations.sample.data.remote.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FetchDetailsViewModel(
    private val fetchDetailsRepo: FetchDetailsRepo
) : ViewModel() {

    val breedDetailObservable = ObservableField<String>()

    private val logTag = "FetchDetailsViewModel"

    fun fetchDogBreedList(): LiveData<List<String>?> {
        return liveData {
            fetchDetailsRepo.fetchDetailsFlow()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            emit(it.data?.map { it.name })
                        }
                        Resource.Status.ERROR -> {
                            Log.i(logTag, "Error: ${it.getErrorMessage()}")
                        }
                    }
                }
        }
    }

    fun fetchBreedDetails(id: Int) {
        viewModelScope.launch {
            fetchDetailsRepo.fetchBreedDetailsFlow(id + 1)
                .flowOn(Dispatchers.IO)
                .catch { error -> Log.i(logTag, "Error: $error")  }
                .collect {
                    val resultString = "-------Details-------\n" +
                            "Name: ${it.name}\n" +
                            "Bred For: ${it.bredFor}\n" +
                            "Breed Group: ${it.breedGroup}\n" +
                            "LifeSpan: ${it.lifeSpan}\n"
                    breedDetailObservable.set(resultString)
                }
        }
    }
}