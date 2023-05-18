package org.bedu.proyecto01equipo16

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        text = findViewById(R.id.txtReco)

        text.setOnClickListener {
            val bundle = Bundle()

            val intent = Intent(this, RecopassActivity::class.java ).apply{
                putExtras(bundle)
            }

            startActivity(intent)
        }

        boton = findViewById(R.id.btnLogin2)

        email = findViewById(R.id.txtEmail)
        pass = findViewById(R.id.txtPassword)

        boton.setOnClickListener {
            // Actualice los correos con los reales del equipo.
            val usuarios = listOf(
                Pair("mario.e.quintal.cob@hotmail.com", "1234"),
                Pair("otro.usuario@example.com", "contrasena"),
                Pair("usuario3@example.com", "123456"),
                Pair("diego@gmail.com","1234"),
                Pair("n-diegotorres@hotmail.com", "1234"),
                Pair("yaelramirezmendez@gmail.com", "1234"),
                Pair("eguzmanh04@gmail.com", "1234")
            )

            val emailIngresado = email.text.toString()
            val passIngresada = pass.text.toString()

            var loginExitoso = false

            for (usuario in usuarios) {
                if (emailIngresado == usuario.first && passIngresada == usuario.second) {
                    loginExitoso = true
                    break
                }
            }

            if (loginExitoso) {
                // Pasa a la pantalla del Navigation Bar
                val bundle = Bundle()
                val intent = Intent(this, Navbar::class.java ).apply{
                    putExtras(bundle)
                }
                startActivity(intent)

            } else {
                Toast.makeText(this, "Datos incorrectos, favor de verificar", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private lateinit var text: TextView
    private lateinit var boton: Button
    private lateinit var email: TextView
    private lateinit var pass: TextView
}
