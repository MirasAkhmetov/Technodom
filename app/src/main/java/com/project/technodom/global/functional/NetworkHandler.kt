package com.project.technodom.global.functional

import android.content.Context
import com.project.technodom.global.extension.networkInfo

class NetworkHandler (private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}