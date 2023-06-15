package com.dviss.deliverytest.domain.usecase

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.dviss.deliverytest.domain.location.LocationService
import java.lang.Exception
import java.util.Locale

private const val TAG = "GetLocationUseCase"

class GetLocation(
    private val context: Context,
    private val locationService: LocationService
) {

    suspend operator fun invoke(): String {
        try {
            val location = locationService.getLocation()
            val geocoder = Geocoder(context, Locale.getDefault())
            val address =  geocoder.getFromLocation(location.latitude, location.longitude, 1)
            return address?.get(0)?.locality ?: "Unknown location"

        } catch (e: LocationService.LocationException) {
            Log.d(TAG, "invoke: ${e.message}")
        } catch (e: Exception) {
            Log.d(TAG, "invoke: ${e.message}")
        }
        return "Unknown Location"
    }
}