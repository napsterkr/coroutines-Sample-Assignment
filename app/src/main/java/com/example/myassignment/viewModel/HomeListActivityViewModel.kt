package com.example.myassignment.viewModel

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassignment.BR
import com.example.myassignment.R
import com.example.myassignment.RetrofitModule
import com.example.myassignment.dataModel.ToDoResponseBean
import com.example.myassignment.interfaces.OnToDoItemClickListner
import kotlinx.coroutines.*
import me.tatarka.bindingcollectionadapter2.ItemBinding
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class HomeListActivityViewModel(private val service: RetrofitModule) : ViewModel() {

    private val parentJob = Job()


    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val toDoList = ObservableArrayList<ToDoResponseBean>()
    var toDoItemClickListner = MutableLiveData<String>()
    var progressBarLodingStatus = ObservableField<Boolean>(false)


    val toDoItemBinding = ItemBinding.of<ToDoResponseBean> { itemBinding, _, _ ->
        itemBinding.set(BR.itemViewModel, R.layout.recycle_view_item)
        itemBinding.bindExtra(BR.itemClickListener, object : OnToDoItemClickListner {
            override fun termsAndConditionsOnItemClick(item: ToDoResponseBean) {
                toDoItemClickListner.postValue(item.title)
            }

        })
    }

    // function to fetch to do List from server
    @ExperimentalCoroutinesApi
    fun fetchToDoListFromServer() {
        progressBarLodingStatus.set(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getApiService().getToDoList()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        toDoList.addAll((response.body()!!))
                        progressBarLodingStatus.set(false)
                    } else {
                        progressBarLodingStatus.set(false)
                    }

                } catch (e: HttpException) {
                    progressBarLodingStatus.set(false)
                    Log.d("error1", e.message ?: "Something went wrong")
                } catch (e: Throwable) {
                    progressBarLodingStatus.set(false)
                    Log.d("error1", e.message ?: "Something went wrong")
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun onReloadClick() {
        toDoList.clear()
        fetchToDoListFromServer()
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}