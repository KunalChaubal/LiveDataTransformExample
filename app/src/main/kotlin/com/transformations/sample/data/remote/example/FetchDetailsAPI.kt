package com.transformations.sample.data.remote.example

import com.transformations.sample.data.remote.model.example.BreedDetail
import com.transformations.sample.data.remote.model.example.DogBreed
import retrofit2.http.GET
import retrofit2.http.Path

interface FetchDetailsAPI {
        @GET("/v1/breeds")
        suspend fun fetchBreedListFlow(): DogBreed

        @GET("/v1/breeds/{id}")
        suspend fun fetchBreedDetailsFlow(@Path(value = "id", encoded = true) id: Int): BreedDetail
}