package com.estudiante.practicasupabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.Locale

class AlumnoAdapter(
    context: Context,
    private val alumnos: ArrayList<Alumno>
) : ArrayAdapter<Alumno>(context, R.layout.fila_estudiante, alumnos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater
            .from(context)
            .inflate(R.layout.fila_estudiante, parent, false)

        val alumno = alumnos[position]

        val txtNombre = view.findViewById<TextView>(R.id.txt_nombre_estudiante)
        val txtCorreo = view.findViewById<TextView>(R.id.txt_correo_estudiante)
        val txtTelefono = view.findViewById<TextView>(R.id.txt_telefono_estudiante)
        val imgAlumno = view.findViewById<ImageView>(R.id.img_foto_estudiante)

        txtNombre.text = alumno.nombres.uppercase(Locale.getDefault())
        txtCorreo.text = alumno.correo
        txtTelefono.text = alumno.telefono

        Glide.with(context)
            .load("https://sga.uteq.edu.ec" + alumno.foto)
            .circleCrop()
            .placeholder(R.drawable.icono_perfil_placeholder)
            .error(R.drawable.icono_perfil_placeholder)
            .into(imgAlumno)

        return view
    }
}
