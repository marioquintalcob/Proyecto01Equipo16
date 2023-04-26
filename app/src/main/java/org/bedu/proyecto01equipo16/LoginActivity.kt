package org.bedu.proyecto01equipo16

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

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
    }
    private lateinit var text: TextView
}