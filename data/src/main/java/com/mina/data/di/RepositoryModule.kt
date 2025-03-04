package com.mina.data.di

import com.mina.data.repository.ProductRepositoryImpl
import com.mina.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository>{
        ProductRepositoryImpl(get())
    }
}