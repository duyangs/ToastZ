package com.duyangs.toastz

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.NonNull
import android.view.View

/**
 * <p>Project:ToastZ</p>
 * <p>Package:com.duyangs.toastz</p>
 * <p>Description:tool class</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/06/04
 */
object ToastZUtil {

    fun tintIcon(drawable: Drawable, @ColorInt tintColor: Int): Drawable {
        drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
        return drawable
    }

    fun tint9PatchDrawableFrame(context: Context, @ColorInt tintColor: Int): Drawable {
        return tintIcon(getDrawable(context, R.drawable.toast_frame), tintColor)
    }

    fun setBackground(view: View, drawable: Drawable) {
        view.background = drawable
    }

    fun getDrawable(context: Context, @DrawableRes id: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            context.getDrawable(id)
        else
            context.resources.getDrawable(id)
    }
}
