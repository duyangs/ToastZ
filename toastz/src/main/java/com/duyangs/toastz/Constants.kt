package com.duyangs.toastz

import android.graphics.Color
import android.graphics.Typeface
import androidx.annotation.ColorInt
import android.view.Gravity


/**
 * "Constants" ToastZ
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2020/8/12.
 */
object Constants{
    const val gravity: Int = Gravity.CENTER
    @ColorInt
    val defaultTextColor: Int = Color.parseColor("#FFFFFF")
    @ColorInt
    val errorColor: Int = Color.parseColor("#909090")
    @ColorInt
    val infoColor: Int = Color.parseColor("#909090")
    @ColorInt
    val successColor: Int = Color.parseColor("#909090")
    @ColorInt
    val warningColor: Int = Color.parseColor("#909090")
    @ColorInt
    val normalColor: Int = Color.parseColor("#909090")

    val currentTypeface: Typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL)
    const val textSize: Int = 16 // in SP
    const val tintIcon: Boolean = true
}