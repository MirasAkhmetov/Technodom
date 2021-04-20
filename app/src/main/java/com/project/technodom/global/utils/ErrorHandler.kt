package com.project.technodom.global.utils

import com.project.technodom.global.extension.errorMessage
import com.project.technodom.global.system.ResourceManager

class ErrorHandler (private val resourceManager: ResourceManager) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        messageListener(error.errorMessage(resourceManager))
    }
}