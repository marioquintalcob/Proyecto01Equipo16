package org.bedu.proyecto01equipo16.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.navBar.Navbar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        text = findViewById(R.id.txtReco)
        text.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent(this, RecopassActivity::class.java).apply {
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
                Pair("diego@gmail.com", "1234"),
                Pair("yaelramirezmendez@gmail.com", "1234"),
                Pair("eguzmanh04@gmail.com", "1234"),
                Pair("1", "1")
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
                val intent = Intent(this, Navbar::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(intent)

            } else {
                Toast.makeText(this, "⚠️Datos incorrectos, favor de verificar", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private lateinit var text: TextView
    private lateinit var boton: Button
    private lateinit var email: TextView
    private lateinit var pass: TextView
}
