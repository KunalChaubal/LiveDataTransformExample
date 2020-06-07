package com.transformations.sample.data.remote.example

import com.transformations.sample.data.remote.model.example.BreedDetail
import com.transformations.sample.data.remote.model.example.DogBreed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FetchDetailsAPI {
        @GET("/v1/breeds")
        fun fetchBreedList(): Call<DogBreed>

        @GET("/v1/breeds/{id}")
        fun fetchBreedDetails(@Path(value = "id", encoded = true) id: Int): Call<BreedDetail>
}