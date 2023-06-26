package org.bedu.proyecto01equipo16.login.signup

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import org.bedu.proyecto01equipo16.databinding.ActivityInputBinding
import org.bedu.proyecto01equipo16.utils.PermissionManager

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var viewModel: RegisterViewModel

    private var imageUri: Uri? = null

    private var hasBeenHandledCamera = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()

        viewModel.successfulCreated.observe(this, Observer {
            val builder = AlertDialog.Builder(this)
            if (it) {
                builder
                    .setTitle("Usuario creado con exito")
                    .setMessage("Ya puedes iniciar sesion")
                    .setPositiveButton("Continuar") { dialog, _ ->
                        dialog.cancel()
                        this.finish()
                    }
                    .show()

            } else {
                builder
                    .setTitle("Hubo un problema")
                    .setMessage("Hubo un error al crear el usuario o ya existe uno registrado con ese correo")
                    .setPositiveButton("Ok", DialogInterface.OnClickListener { _, _ ->
                        finishActivity(RESULT_OK)
                    })
                    .show()
            }
        })

        viewModel.launchCamera.observe(this) {
            if (it && !hasBeenHandledCamera) {
                hasBeenHandledCamera = true
                launchCamera()
                return@observe
            }
            hasBeenHandledCamera = false
        }

        viewModel.setProfilePicture.observe(this) {
            if (it) {
                Glide.with(this)
                    .load(imageUri)
                    .transform(CircleCrop())
                    .into(binding.imaProfile)
            }
        }

        viewModel.passwordBadFormat.observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    "No coinciden las contraseñas o tiene menos de 6 caracteres",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.emptyFields.observe(this) {
            if (it) {
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imaProfile.setOnClickListener {
            viewModel.requestCamera(this, this)
        }

        binding.btnCreateAccount.setOnClickListener {
            val bytes = imageUri?.let { it1 -> contentResolver.openInputStream(it1)?.readBytes() }
            val profilePicture = bytes?.let { Base64.encodeToString(bytes, Base64.DEFAULT) }

            viewModel.validateDateAndSave(
                name = binding.etName.text.toString(),
                phoneNumber = binding.etPhone.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString(),
                confirmPassword = binding.etConfirmPassword.text.toString(),
                profilePicture = profilePicture ?: ""
            )
        }

        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                binding.textUserName.text = binding.etName.text
            }

            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun launchCamera() {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_pic")
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description")
        imageUri = this.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, PermissionManager.CAMERA_RESULT_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel.onRequestPermissionsResult(requestCode, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
