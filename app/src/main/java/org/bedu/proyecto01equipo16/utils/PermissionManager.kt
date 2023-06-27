package org.bedu.proyecto01equipo16.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager {
    companion object {
        const val STORAGE_PERMISSION_CODE = 100
        const val CAMERA_PERMISSION_CODE = 200
        const val CAMERA_RESULT_CODE = 201
        const val NOTIFICATION_PERMISSION_CODE = 300

        fun checkCameraPermission(context: Context): Boolean {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return false;
            }
            return true;
        }

        fun checkStoragePermission(context: Context): Boolean {
            val write = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            val read = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            return write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED
        }

        fun requestStoragePermission(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                STORAGE_PERMISSION_CODE
            )
        }

        fun requestCameraPermission(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }

        fun requestNotificationPermission(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                NOTIFICATION_PERMISSION_CODE
            )
        }
    }
}
