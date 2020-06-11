package com.duyangs.toastz

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import android.view.View
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

fun tintIcon(drawable: Drawable?, @ColorInt tintColor: Int): Drawable? {
    drawable?.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
    return drawable
}

fun tint9PatchDrawableFrame(@ColorInt tintColor: Int?): Drawable? {
    if (tintColor == null) return null
    return tintIcon(getDrawable(R.drawable.toast_frame), tintColor)
}

fun setBackground(view: View, drawable: Drawable?) {
    view.background = drawable
}

fun getDrawable(@DrawableRes id: Int?) = if (id == null) null else ToastZLib.getContext().getDrawable(id)

fun getString(@StringRes resId: Int) = ToastZLib.getContext().getString(resId)
