package com.mina.data.repository

import com.mina.domain.model.Product
import com.mina.domain.network.NetworkService
import com.mina.domain.network.ResultWrapper
import com.mina.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: NetworkService):ProductRepository {
    override suspend fun getProducts(category:String?): ResultWrapper<List<Product>> {
        return networkService.getProducts(category)
    }
}