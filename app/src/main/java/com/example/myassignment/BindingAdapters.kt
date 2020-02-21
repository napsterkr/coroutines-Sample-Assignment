package com.example.myassignment

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGoneBoolean")
    fun showHideBoolean(view: View, show: Boolean?) {
        view.visibility = if (show != null && show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("visibleGoneList")
    fun showHideList(view: View, size: Int?) {
        view.visibility = if (size != null && size > 0) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter( value = ["goneVisibleList", "goneVisibleLoaderVisible"],requireAll = true)
    fun goneVisibleList(view: View, size: Int?, goneVisibleLoaderVisible: Boolean?) {
        view.visibility = if ((size != null && size > 0) || goneVisibleLoaderVisible!!) View.GONE else View.VISIBLE
    }
}