package com.duyangs.toastz

import android.graphics.Color
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.view.Gravity


/**
 * "Constants" ToastZ
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2018/6/1.
 */
object ToastZConstant{
    const val tGravity: Int = Gravity.CENTER

    @ColorInt
    val tDefaultTestColor: Int = Color.parseColor("#FFFFFF")
    @ColorInt
    val tErrorColor: Int = Color.parseColor("#909090")
    @ColorInt
    val tInfoColor: Int = Color.parseColor("#909090")
    @ColorInt
    val tSuccessColor: Int = Color.parseColor("#909090")
    @ColorInt
    val tWarningColor: Int = Color.parseColor("#909090")
    @ColorInt
    val tNormalColor: Int = Color.parseColor("#909090")

    val currentTypeface: Typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL)
    const val textSize: Int = 16 // in SP
    const val tintIcon: Boolean = true
}