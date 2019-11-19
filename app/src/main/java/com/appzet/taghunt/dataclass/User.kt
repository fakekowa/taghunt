package com.appzet.taghunt.dataclass

data class User(val username: String, val creator: Boolean,  val long: Long = 0, val lat: Long = 0) {
}