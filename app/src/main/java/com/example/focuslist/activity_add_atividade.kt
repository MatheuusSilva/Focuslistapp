package com.example.focuslist

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.focuslist.model.Tarefa
import com.example.focuslist.db.AppDatabase
import java.util.*

class activity_add_atividade : AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var etDescricao: EditText
    private lateinit var etPrazo: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnCancelar: Button

    @SuppressLint("DefaultLocale")
    private fun abrirDatePicker() {
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, y, m, d ->
            etPrazo.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
        }, ano, mes, dia)

        datePicker.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_atividade)

        etTitulo = findViewById(R.id.etTitulo)
        etDescricao = findViewById(R.id.etDescricao)
        etPrazo = findViewById(R.id.etPrazo)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnCancelar = findViewById(R.id.btnCancelar)

        val emailUsuario = intent.getStringExtra("email") ?: ""

        etPrazo.setOnClickListener {
            abrirDatePicker()
        }

        btnSalvar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val descricao = etDescricao.text.toString().trim()
            val prazo = etPrazo.text.toString().trim()

            if (titulo.isEmpty() || descricao.isEmpty() || prazo.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "app.db"
            ).allowMainThreadQueries().build()

            val novaTarefa = Tarefa(
                titulo = titulo,
                descricao = descricao,
                prazo = prazo,
                categoria = "À Prazo",
                usuarioEmail = emailUsuario
            )

            db.tarefaDao().inserir(novaTarefa)
            Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}
