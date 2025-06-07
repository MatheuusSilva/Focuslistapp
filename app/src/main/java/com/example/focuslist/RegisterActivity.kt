package com.example.focuslist

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.focuslist.ui.theme.FocuslistTheme
import com.example.focuslist.db.AppDatabase
import com.example.focuslist.model.Usuario
import kotlinx.coroutines.launch

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSenha = findViewById<EditText>(R.id.etSenha)
        val btnRegistrar = findViewById<TextView>(R.id.btnRegistrar) // TextView com função de botão

        val db = AppDatabase.getDatabase(this)
        val usuarioDao = db.usuarioDao()

        btnRegistrar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val senha = etSenha.text.toString().trim()

            if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existente = usuarioDao.getByEmail(email)
                if (existente != null) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "E-mail já está cadastrado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val novoUsuario = Usuario(email = email, senha = senha, nome = nome)
                    usuarioDao.insert(novoUsuario)
                    Toast.makeText(
                        this@RegisterActivity,
                        "Usuário cadastrado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish() // Fecha a tela e volta para o Login
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    FocuslistTheme {
        Greeting3("Android")
    }
}