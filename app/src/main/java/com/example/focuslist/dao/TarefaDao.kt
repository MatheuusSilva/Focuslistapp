package com.example.focuslist.dao

import androidx.room.*
import com.example.focuslist.model.Tarefa

@Dao
interface TarefaDao {
    @Insert
    fun inserir(tarefa: Tarefa)

    @Query("SELECT * FROM Tarefa WHERE usuarioEmail = :email")
    fun getTarefasPorEmail(email: String): List<Tarefa>

    @Update
    fun atualizar(tarefa: Tarefa)

    @Delete
    fun deletar(tarefa: Tarefa)
}