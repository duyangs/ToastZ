package com.duyangs.toastz

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * <p>Project:ToastZ</p>
 * <p>Package:com.duyangs.toastz</p>
 * <p>Description:tool class</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/06/04
 */

@Suppress("DEPRECATION")
fun tintIcon(drawable: Drawable?, @ColorInt tintColor: Int): Drawable? {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
        drawable?.colorFilter = BlendModeColorFilter(tintColor, BlendMode.SRC_IN)
    } else {
        drawable?.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
    }
    return drawable
}

fun tint9PatchDrawableFrame(@DrawableRes toastFrameResId: Int,@ColorInt tintColor: Int?): Drawable? {
    if (tintColor == null) return null
    return tintIcon(getDrawable(toastFrameResId), tintColor)
}

fun setBackground(view: View, drawable: Drawable?) {
    view.background = drawable
}

@SuppressLint("UseCompatLoadingForDrawables")
fun getDrawable(@DrawableRes id: Int?) = if (id == null) null else ToastZ.getContext().getDrawable(id)

fun getString(@StringRes resId: Int) = ToastZ.getContext().getString(resId)

fun getYOffset(gravity: Int, yOffset: Int): Int = if (gravity == Gravity.CENTER) 0 else yOffset

fun getDrawableFrame(shouldTint: Boolean, @ColorInt tintColor: Int, @DrawableRes toastFrameResId: Int): Drawable? {
    return if (shouldTint) tint9PatchDrawableFrame(toastFrameResId,tintColor) else getDrawable(toastFrameResId)
}

fun msgFormat(msg: Any): CharSequence = when (msg) {
    is Int -> getString(msg)
    is CharSequence -> msg
    else -> ""
}

@SuppressLint("ShowToast", "InflateParams")
fun loadToastLayout(showMsg: CharSequence, duration: Int): Pair<Toast, Triple<View, ImageView, TextView>> {
    val mToast = Toast.makeText(ToastZ.getContext(), showMsg, duration)
    val toastLayout: View = (ToastZ.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.toast_layout, null)
    val toastIcon: ImageView = toastLayout.findViewById(R.id.toast_icon)
    val toastTextView: TextView = toastLayout.findViewById(R.id.toast_text)
    return Pair(mToast, Triple(toastLayout, toastIcon, toastTextView))
}