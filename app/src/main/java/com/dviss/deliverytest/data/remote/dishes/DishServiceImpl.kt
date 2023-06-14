package com.dviss.deliverytest.data.remote.dishes

import android.util.Log
import com.dviss.deliverytest.data.remote.HttpRoutes
import com.dviss.deliverytest.data.remote.dishes.dto.DishResponse
import com.dviss.deliverytest.data.remote.dishes.mapper.toDish
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.domain.remote.DishService
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

private const val TAG = "DishServiceImpl"

class DishServiceImpl(private val client: HttpClient): DishService {
    override suspend fun getDishes(): List<Dish> {
        val response = try {
            client.get {
                url(HttpRoutes.DISHES_URL)
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Log.d(TAG, "getCategories: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            DishResponse(emptyList())
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Log.d(TAG, "getCategories: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            DishResponse(emptyList())
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Log.d(TAG, "getCategories: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            DishResponse(emptyList())
        } catch (e: NoTransformationFoundException) {
            //Could not parse for some reason
            Log.d(TAG, "getCategories: ${e.message}")
            DishResponse(emptyList())
        } catch (e: Exception) {
            // other
            Log.d(TAG, "getCategories: ${e.message}")
            println("Error: ${e.message}")
            DishResponse(emptyList())
        }
        return response.dishes.map { it.toDish() }
    }
}