package com.transformations.sample.data.remote.example

import com.transformations.sample.data.remote.exception.AppException
import com.transformations.sample.data.remote.model.Resource
import com.transformations.sample.data.remote.model.example.BreedDetail
import com.transformations.sample.data.remote.model.example.DogBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchDetailsRepo(private val fetchDetailsAPI: FetchDetailsAPI) {

    suspend fun fetchDetailsFlow(): Flow<Resource<DogBreed>> {
        return flow {
            emit(handleException { fetchDetailsAPI.fetchBreedListFlow() })
        }
    }

    suspend fun fetchBreedDetailsFlow(id: Int): Flow<BreedDetail> {
        return flow {
            emit(fetchDetailsAPI.fetchBreedDetailsFlow(id) )
        }
    }
}

suspend fun <T> handleException(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.success(apiCall.invoke())
    } catch (throwable: Throwable) {
        Resource.error(AppException(throwable))
    }
}