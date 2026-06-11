package com.estudiante.practicasupabase

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.launch

class ActividadListaTextoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_texto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contenedor_raiz)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtAlumnos = findViewById<EditText>(R.id.txt_lista_alumnos)
        val progressMaterias = findViewById<ProgressBar>(R.id.barra_carga)

        lifecycleScope.launch {
            progressMaterias.visibility = View.VISIBLE
            try {
                val alumnos = SupabaseManager.client
                    .from("alumnos")
                    .select {
                        order("nombres", Order.ASCENDING)
                    }
                    .decodeList<Alumno>()

                var texto = ""
                for (alumno in alumnos) {
                    texto += "Nombres: " + alumno.nombres + "\n"
                    texto += "Correo: " + alumno.correo + "\n"
                    texto += "Teléfono: " + alumno.telefono + "\n\n"
                }

                txtAlumnos.setText(texto)
            } catch (e: RestException) {
                txtAlumnos.setText(e.description ?: e.message ?: "Error de Supabase")
            } catch (e: Exception) {
                txtAlumnos.setText(e.message ?: e.toString())
            } finally {
                progressMaterias.visibility = View.INVISIBLE
            }
        }
    }
}
