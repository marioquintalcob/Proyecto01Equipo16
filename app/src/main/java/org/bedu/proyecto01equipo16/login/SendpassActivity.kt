package org.bedu.proyecto01equipo16.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.bedu.proyecto01equipo16.R

class SendpassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sendpass)

        boton = findViewById(R.id.btnReturn)

        boton.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        }

    }

    private lateinit var boton: Button
}