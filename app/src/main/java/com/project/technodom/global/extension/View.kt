package com.project.technodom.global.extension


import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.project.technodom.R
import com.project.technodom.global.base.BaseFragment
import com.project.technodom.global.di.ServiceProperties


//val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer!!

val BaseFragment.appContext: Context get() = activity?.applicationContext!!


fun View.visible(visible: Boolean?) {
    this.visibility = if (visible ?: false) View.VISIBLE else View.GONE
}

