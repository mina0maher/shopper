package com.mina.domain.repository

import com.mina.domain.model.Product
import com.mina.domain.network.ResultWrapper

interface ProductRepository {
   suspend fun getProducts():ResultWrapper<List<Product>>
}