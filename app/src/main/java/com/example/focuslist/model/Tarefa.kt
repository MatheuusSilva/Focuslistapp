package com.example.focuslist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tarefa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descricao: String,
    val prazo: String,
    val categoria: String, // "À Prazo", "Pendentes", "Concluídas"
    val usuarioEmail: String // Vai vincular a tarefa ao dono dela
)