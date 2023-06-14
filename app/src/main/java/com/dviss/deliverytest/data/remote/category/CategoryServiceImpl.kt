package com.dviss.deliverytest.data.remote.category

import android.util.Log
import com.dviss.deliverytest.data.remote.HttpRoutes
import com.dviss.deliverytest.data.remote.category.dto.CategoryResponse
import com.dviss.deliverytest.data.remote.category.mapper.toCategory
import com.dviss.deliverytest.domain.model.Category
import com.dviss.deliverytest.domain.remote.CategoryService
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

private const val TAG = "CategoryServiceImpl"

class CategoryServiceImpl(private val client: HttpClient) : CategoryService {
    override suspend fun getCategories(): List<Category> {
        val response = try {
            client.get {
                url(HttpRoutes.CATEGORIES_URL)
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Log.d(TAG, "getCategories: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            CategoryResponse(emptyList())
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Log.d(TAG, "getCategories: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            CategoryResponse(emptyList())
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Log.d(TAG, "getCategories: ${e.response.status.description}")
            println("Error: ${e.response.status.description}")
            CategoryResponse(emptyList())
        } catch (e: NoTransformationFoundException) {
            //Could not parse for some reason
            Log.d(TAG, "getCategories: ${e.message}")
            CategoryResponse(emptyList())
        } catch (e: Exception) {
            // other
            Log.d(TAG, "getCategories: ${e.message}")
            println("Error: ${e.message}")
            CategoryResponse(emptyList())
        }
        return response.categories.map { it.toCategory() }
    }
}