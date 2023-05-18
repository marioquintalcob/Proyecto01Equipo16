package org.bedu.proyecto01equipo16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SendpassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sendpass)

        boton = findViewById(R.id.btnReturn)

        boton.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent(this, LoginActivity::class.java ).apply{
                putExtras(bundle)
            }
            startActivity(intent)
        }


    }
    private lateinit var boton: Button
}