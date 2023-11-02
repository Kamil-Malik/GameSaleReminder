package com.lelestacia.gamediscountreminder.data.mapper

import com.lelestacia.gamediscountreminder.data.source.local.entity.StoreEntity
import com.lelestacia.gamediscountreminder.data.source.remote.dto.store.StoreDto

fun StoreDto.toEntity(): StoreEntity {
    return StoreEntity(
        id = 0,
        storeID = storeID,
        storeName = storeName,
        isActive = isActive,
        banner = images.banner,
        icon = images.icon,
        logo = images.logo
    )
}