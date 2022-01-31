package com.flink.android.challenge

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.flink.android.api.FlinkApiProvider
import com.flink.android.api.Product
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient by lazy { FlinkApiProvider.createApiClient() }
    private val pref by lazy {
        application.getSharedPreferences(
            "preference_key",
            Context.MODE_PRIVATE
        )
    }


    val state = MutableLiveData<State>()


    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            try {
//      val products = apiClient.getProductsEmpty()
//                val products = apiClient.getProductsError()
                val products = apiClient.getProducts().map { product ->
                    product.copy(value = pref.getInt(product.id, 0))
                }

                if (products.isEmpty()) {
                    state.value = State.Empty
                } else {
                    state.value = State.Success(products)
                }
            } catch (ex: Exception) {
                state.value = State.Error
            }
        }
    }

    fun minus(product: Product) {
        state.value.run {
            if (this is State.Success) {
                val newList = products.map {
                    if (it.id == product.id) {
                        val newVal = product.value - 1

                        //Save in pref
                        saveInPreferences(product.id, newVal)

                        product.copy(value = newVal)
                    } else {
                        it
                    }
                }

                state.value = State.Success(newList)
            }
        }

    }

    fun plus(product: Product) {
        state.value.run {
            if (this is State.Success) {
                val newList = products.map {
                    if (it.id == product.id) {
                        val newVal = product.value + 1

                        //Save in pref
                        saveInPreferences(product.id, newVal)

                        product.copy(value = newVal)
                    } else {
                        it
                    }
                }

                state.value = State.Success(newList)
            }
        }
    }

    private fun saveInPreferences(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

}

class MainViewModelFactory(application: Application) :
    ViewModelProvider.Factory {
    private val mApplication: Application = application
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mApplication) as T
    }
}


sealed class State {
    class Success(val products: List<Product>) : State()
    object Empty : State()
    object Error : State()
}