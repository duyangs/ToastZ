package com.duyangs.toastz

import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

/**
 * Project: ToastZ
 * Package: com.duyangs.toastz
 * Author: Ryan Du (duyangs1994@gmail.com)
 * Date: 2020/8/12 11:02 ( Tuesday August )
 * Description: Basic configuration
 */
data class Config(
        private var gravity: Int? = Constants.gravity,
        @ColorInt private var defaultTextColor: Int? = Constants.defaultTextColor,
        @ColorInt private var errorColor: Int? = Constants.errorColor,
        @ColorInt private var infoColor: Int? = Constants.infoColor,
        @ColorInt private var successColor: Int? = Constants.successColor,
        @ColorInt private var warningColor: Int? = Constants.warningColor,
        @ColorInt private var normalColor: Int? = Constants.normalColor,
        private var typeface: Typeface? = Constants.currentTypeface,
        private var textSize: Int? = Constants.textSize,
        private var tintIcon: Boolean? = Constants.tintIcon,
        private var yOffset: Int? = Constants.yOffset,
        @DrawableRes private var toastFrameResId: Int? = Constants.toastFrameResId
) {

    fun getGravity(): Int = gravity ?: Constants.gravity

    fun getDefaultTextColor(): Int = defaultTextColor ?: Constants.defaultTextColor

    fun getErrorColor(): Int = errorColor ?: Constants.errorColor

    fun getInfoColor(): Int = infoColor ?: Constants.infoColor

    fun getSuccessColor(): Int = successColor ?: Constants.successColor

    fun getWarningColor(): Int = warningColor ?: Constants.warningColor

    fun getNormalColor(): Int = normalColor ?: Constants.normalColor

    fun getTypeface(): Typeface = typeface ?: Constants.currentTypeface

    fun getTextSize(): Int = textSize ?: Constants.textSize

    fun getTintIcon(): Boolean = tintIcon ?: Constants.tintIcon

    fun getYOffset(): Int = yOffset ?: Constants.yOffset

    fun getToastFrameResId(): Int = toastFrameResId ?: Constants.toastFrameResId

}