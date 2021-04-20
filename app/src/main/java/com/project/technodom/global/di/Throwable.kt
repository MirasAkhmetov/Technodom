package com.project.technodom.global.di

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.project.technodom.global.system.ResourceManager
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

fun Throwable.getErrorMessage(): String{
    if (this is HttpException){
        val body = this.response()?.errorBody()
        return body?.string().toString()
    }
    return ""
}

