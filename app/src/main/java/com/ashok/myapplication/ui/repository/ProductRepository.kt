package com.ashok.myapplication.ui.repository

import com.ashok.myapplication.data.datasource.ProductDataSource
import com.ashok.myapplication.data.entity.Products
import com.ashok.myapplication.ui.utilities.ResourceState
import retrofit2.Response
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ProductRepository @Inject constructor(private val productDataSource: ProductDataSource) {
    suspend fun getAllProducts():Flow<ResourceState<Products>>{
        return flow {
            val response = productDataSource.geAllProducts()
            emit(ResourceState.Loading())
            if (response.isSuccessful && response.body() !=null){
                emit(ResourceState.Success(response.body()!!))
            }else{
                emit(ResourceState.Error("Error fetching products data"))
            }
        }.catch { e->
            emit(ResourceState.Error(e.localizedMessage?:"Some error in flow"))
        }
    }
}