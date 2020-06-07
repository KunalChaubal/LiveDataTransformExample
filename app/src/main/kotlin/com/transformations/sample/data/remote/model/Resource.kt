package com.transformations.sample.data.remote.model

import com.transformations.sample.data.remote.exception.AppException

class Resource<out T> private constructor(val status: Status, val data: T?, private val exception: AppException?) {

    enum class Status {
        SUCCESS, ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(exception: AppException? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                exception
            )
        }
    }

    fun getErrorMessage() = exception?.getErrorMessage()

    fun getErrorCode() = exception?.getErrorCode()
}