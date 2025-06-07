package com.example.focuslist

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.focuslist.db.AppDatabase
import com.example.focuslist.R
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usa layout XML tradicional
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnLogin = findViewById<TextView>(R.id.btnLogin)
        val btnRegistrar = findViewById<TextView>(R.id.btnRegistrar)

        val db = AppDatabase.getDatabase(this)
        val usuarioDao = db.usuarioDao()

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (email.isBlank() || senha.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val usuario = usuarioDao.login(email, senha)

                if (usuario != null) {
                    Toast.makeText(this@LoginActivity, "Bem-vindo, ${usuario.nome}!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.putExtra("nomeUsuario", usuario.nome)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
