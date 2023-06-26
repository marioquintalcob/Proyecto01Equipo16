package org.bedu.proyecto01equipo16.login.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.bedu.proyecto01equipo16.R

class RecopassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recopass)

        // funcionalidad del boton de recuperar contraseña
        boton = findViewById(R.id.btnPass)
        email = findViewById(R.id.txtEmail2)

        boton.setOnClickListener {
            val emails = listOf(
                "mario.e.quintal.cob@hotmail.com",
                "n-diegotorres@hotmail.com",
                "yaelramirezmendez@gmail.com",
                "eguzmanh04@gmail.com"
            )

            val emailIngresado = email.text.toString()

            var emailExistente = false

            for (e in emails) {
                if (emailIngresado == e) {
                    emailExistente = true
                    break
                }
            }

            if (emailExistente) {
                val bundle = Bundle()
                val intent = Intent(this, SendpassActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(intent)

            } else {
                Toast.makeText(this, "Correo no registrado, favor de verificar", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    private lateinit var boton: Button
    private lateinit var email: TextView
}
