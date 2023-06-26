package org.bedu.proyecto01equipo16

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import org.bedu.proyecto01equipo16.login.LoginActivity
import org.bedu.proyecto01equipo16.login.signup.RegisterActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        boton = findViewById(R.id.btnLogin)

        boton.setOnClickListener {
            val bundle = Bundle()

            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        boton2 = findViewById(R.id.btnRegister)

        boton2.setOnClickListener {
            val bundle = Bundle()

            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }
    }

    private lateinit var boton: Button
    private lateinit var boton2: Button
}
