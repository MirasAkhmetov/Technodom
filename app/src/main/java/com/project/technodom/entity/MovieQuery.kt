package com.project.technodom.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class MovieQuery(
    var page: Int = 1
): Parcelable
