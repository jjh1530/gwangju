package com.example.testre

data class ChatList (
    val userId: String,
    val title: String,
    ) {
    constructor() : this("","")
}