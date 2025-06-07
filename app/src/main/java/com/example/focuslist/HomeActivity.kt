package com.example.focuslist

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var btnAdicionar: ImageButton
    private var emailUsuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Recebe o e-mail do usuário que fez login
        emailUsuario = intent.getStringExtra("email") ?: ""

        // Referência ao botão flutuante ou de adicionar
        btnAdicionar = findViewById(R.id.btnAdicionar)

        // Quando clicar, abre a AddAtividadeActivity e envia o email do usuário
        btnAdicionar.setOnClickListener {
            val intent = Intent(this, activity_add_atividade::class.java)
            intent.putExtra("email", emailUsuario)
            startActivity(intent)
        }
    }
}


