package com.transformations.sample.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.transformations.sample.data.remote.model.Resource
import com.transformations.sample.data.remote.exception.AppException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.asLiveData(): LiveData<Resource<T>> {

    val result = MutableLiveData<Resource<T>>()

    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            result.postValue(Resource.error(AppException(t)))
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            result.postValue(Resource.success(response.body()))
        }
    })
    return result
}