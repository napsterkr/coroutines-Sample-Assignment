package com.example.myassignment.interfaces

import com.example.myassignment.dataModel.ToDoResponseBean
import retrofit2.Response
import retrofit2.http.GET

interface  ToDoApi {

    @GET("/todos")
    suspend fun getToDoList() : Response<List<ToDoResponseBean>>

}