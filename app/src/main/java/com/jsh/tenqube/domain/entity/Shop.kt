package com.jsh.tenqube.domain.entity


sealed class DomainShop {
    data class Shop(
        var id: String,
        var name: String,
        var imgUrl: String,
        var labelIds: List<String>
    ): DomainShop()
}