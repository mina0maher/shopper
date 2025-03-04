package com.mina.data.model

import com.mina.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class DataProductModel(
    val id:Long,
    val title:String,
    val price:Double,
    val category: String,
    val description:String,
    val image:String
){
    fun toProduct()=Product(
        id = id,
        title = title,
        price = price,
        category = category,
        description = category,
        image = image
    )
}
