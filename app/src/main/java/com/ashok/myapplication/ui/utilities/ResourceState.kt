package com.ashok.myapplication.ui.utilities

sealed class ResourceState<T> {
    class Loading<T> : ResourceState<T>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val error: Any) : ResourceState<T>()
}
