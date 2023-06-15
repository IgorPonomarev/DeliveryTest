package com.dviss.deliverytest.domain.location

import android.location.Location
import java.lang.Exception

interface LocationService {
    suspend fun getLocation(): Location

    class LocationException(message: String): Exception(message)
}