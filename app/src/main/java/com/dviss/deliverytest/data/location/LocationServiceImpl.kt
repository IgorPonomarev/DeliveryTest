package com.dviss.deliverytest.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import com.dviss.deliverytest.domain.location.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

private const val TAG = "LocationServiceImpl"

class LocationServiceImpl(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationService {

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Location {
        // Check if the app has location permission
        if (!context.hasLocationPermission()) {
            throw LocationService.LocationException("Missing location permission")
        }

        // Check if GPS and network providers are enabled
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGpsEnabled && !isNetworkEnabled) {
            throw LocationService.LocationException("GPS is disabled")
        }

        // Configure the location request
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 5000 // 5 seconds (adjust as needed)
            fastestInterval = 1000 // 1 second (adjust as needed)
        }

        // Define the location callback to handle location availability
        val locationCallback = object : LocationCallback() {
            override fun onLocationAvailability(locationAvailability: LocationAvailability) {
                if (locationAvailability.isLocationAvailable) {
                    // Location is available, you can request the last known location here
                    client.lastLocation
                        .addOnSuccessListener {
                            // Use the received location
                            // You can update the UI or trigger any other actions here
                        }
                        .addOnFailureListener { e: Exception ->
                            Log.d(TAG, "lastLocation: ${e.message}")
                        }
                } else {
                    Log.d(TAG, "Location is unavailable")
                }
            }
        }

        // Request location updates with the location callback
        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            .addOnSuccessListener {
                // Location updates have been requested successfully
            }
            .addOnFailureListener { e: Exception ->
                Log.d(TAG, "requestLocationUpdates: ${e.message}")
            }

        // Return the last known location using a suspendCancellableCoroutine
        return suspendCancellableCoroutine { continuation ->
            client.lastLocation
                .addOnSuccessListener { location ->
                    continuation.resume(location) {}
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
        }
    }
}