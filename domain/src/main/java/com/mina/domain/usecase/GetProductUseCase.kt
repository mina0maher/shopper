package com.mina.domain.usecase

import com.mina.domain.repository.ProductRepository

class GetProductUseCase (private val repository: ProductRepository){
    suspend fun execute() = repository.getProducts()
}