package com.zasa.comkot.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

// TODO: Base
fun Context.shouldRequestPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED

fun Context.permissionGranted(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.requestPermission(permission: String, requestCode: Int) =
    ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)

// TODO: Location
val Context.isLocationPermissionGranted: Boolean
    get() = permissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)

val Context.isLocationPermissionDenied: Boolean
    get() = shouldRequestPermission(Manifest.permission.ACCESS_FINE_LOCATION)

val Context.shouldRequestLocationStoragePermission: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isLocationPermissionDenied

fun Activity.requestLocationPermission() =
    requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, 0)
