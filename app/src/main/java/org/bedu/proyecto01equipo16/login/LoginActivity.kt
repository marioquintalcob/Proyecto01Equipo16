package org.bedu.proyecto01equipo16.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.bedu.proyecto01equipo16.databinding.ActivityLoginBinding
import org.bedu.proyecto01equipo16.login.signup.RecopassActivity
import org.bedu.proyecto01equipo16.navBar.Navbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()

        binding.txtReco.setOnClickListener {
            val intent = Intent(this, RecopassActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.doLogin(binding.txtEmail.text.toString(), binding.txtPassword.text.toString())
        }

        viewModel.user.observe(this) {
            if (it != null) {
                val intent = Intent(this, Navbar::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Datos incorrectos, favor de verificar", Toast.LENGTH_SHORT)
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
}
