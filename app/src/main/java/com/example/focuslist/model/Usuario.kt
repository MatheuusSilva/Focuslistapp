package com.example.focuslist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey val email: String,
    val senha: String,
    val nome:String
)