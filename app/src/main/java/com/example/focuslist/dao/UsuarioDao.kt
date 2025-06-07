package com.example.focuslist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.focuslist.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE email = :email AND senha = :senha")
    suspend fun login(email: String, senha: String): Usuario?

    @Query("SELECT * FROM usuario WHERE email = :email")
    suspend fun getByEmail(email: String): Usuario?
}