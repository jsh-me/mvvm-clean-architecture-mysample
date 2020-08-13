package com.jsh.tenqube.data.mapper

import com.jsh.tenqube.data.dto.LabelModel
import com.jsh.tenqube.data.dto.ShopModel
import com.jsh.tenqube.data.source.label.local.DataLabel.*
import com.jsh.tenqube.data.source.shop.local.DataShop
import com.jsh.tenqube.data.source.shopAndLabel.ShopWithAllLabel
import com.jsh.tenqube.data.source.shopAndLabel.local.DataShopLocal.LocalShopLabelModel
import com.jsh.tenqube.domain.entity.DomainLabel.*
import com.jsh.tenqube.domain.entity.DomainShop.*
import com.jsh.tenqube.domain.entity.DomainShopLabel.*


fun List<LabelModel>.toDomainLabelList(): List<Label>{
    return this.map{
        Label(it.id, it.name)
    }
}

fun List<ShopModel>.toDomainShopList(): List<Shop>{
    return this.map{
        Shop(it.id, it.name, it.imgUrl, it.labelIds)
    }
}

fun List<LocalLabelModel>.toLocalDomainLabelList(): List<Label>{
    return this.map{
        Label(it.id, it.name)
    }
}

fun List<DataShop.LocalShopModel>.toLocalDomainShopList(): List<Shop>{
    return this.map{
        Shop(it.id, it.shopName, it.shopUrl, arrayListOf())
    }
}

fun LocalLabelModel.toDomainLabel(): Label {
    return Label(this.id, this.name)
}


fun Label.toDataLocalLabelModel(): LocalLabelModel {
    return LocalLabelModel(this.id, this.name)
}

fun List<Label>.toDataLocalLabelList(): List<LocalLabelModel>{
    return this.map{
        LocalLabelModel(it.id, it.name)
    }
}

fun DataShop.LocalShopModel.toLocalDomainShop(): Shop {
    return Shop(this.id, this.shopName, this.shopUrl, arrayListOf())
}

fun Shop.toLocalDataShopModel(): DataShop.LocalShopModel {
    return DataShop.LocalShopModel(id = this.id, shopName = this.name, shopUrl = this.imgUrl)
}


fun ShopLabel.toDataShopWithAllLabel(): ShopWithAllLabel {
    return ShopWithAllLabel(
        this.shop.toLocalDataShopModel(),
        this.labels.toDataLocalLabelList()
    )
}

fun ShopLabel.toDataLocalShopLabelModel(): List<LocalShopLabelModel> {
    return this.labels.map{
        LocalShopLabelModel(this.shop.id, it.name)
    }
}

fun ShopWithAllLabel.toDomainShopLabel(): ShopLabel {
    return ShopLabel(this.shop.toLocalDomainShop(), this.shopLabels.toLocalDomainLabelList())
}

fun List<ShopWithAllLabel>.toDomainShopLabelList(): List<ShopLabel> {
    return this.map{
        ShopLabel(it.shop.toLocalDomainShop(), it.shopLabels.toLocalDomainLabelList())
    }
}

fun SingleShopLabel.toDataShopLabel(): LocalShopLabelModel{
    return LocalShopLabelModel(this.shopId, this.labelId)
}