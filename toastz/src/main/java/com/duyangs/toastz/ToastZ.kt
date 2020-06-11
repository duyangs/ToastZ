package com.duyangs.toastz

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * <p>Project:ToastZ</p>
 * <p>Package:com.duyangs.toastz</p>
 * <p>Description: ToastZ</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/06/04
 */
@SuppressLint("InflateParams", "StaticFieldLeak")
object ToastZ {

    private var tGravity: Int = ToastZConstant.tGravity

    @ColorInt
    private var tDefaultTextColor: Int = ToastZConstant.tDefaultTextColor

    @ColorInt
    private var tErrorColor: Int = ToastZConstant.tErrorColor

    @ColorInt
    private var tInfoColor: Int = ToastZConstant.tInfoColor

    @ColorInt
    private var tSuccessColor: Int = ToastZConstant.tSuccessColor

    @ColorInt
    private var tWarningColor: Int = ToastZConstant.tWarningColor

    @ColorInt
    private var tNormalColor: Int = ToastZConstant.tNormalColor

    private var currentTypeface: Typeface = ToastZConstant.currentTypeface
    private var textSize: Int = ToastZConstant.textSize // in SP

    private var tintIcon: Boolean = ToastZConstant.tintIcon

    private var mToast: Toast? = null

    /**
     * Normal style
     */
    fun normal(msg: Any, gravity: Int? = tGravity, duration: Int? = Toast.LENGTH_LONG) {
        custom(msg, gravity, null, tNormalColor, duration)
    }

    /**
     * Success style
     */
    fun success(msg: Any, gravity: Int? = tGravity, duration: Int? = Toast.LENGTH_LONG, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_check_white_48dp, tSuccessColor, duration, withIcon, true)
    }

    /**
     * Info style
     */
    fun info(msg: Any, gravity: Int? = tGravity, duration: Int? = Toast.LENGTH_LONG, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_info_outline_white_48dp, tInfoColor, duration, withIcon, true)
    }

    /**
     * Warning style
     */
    fun warning(msg: Any, gravity: Int? = tGravity, duration: Int? = Toast.LENGTH_LONG, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_error_outline_white_48dp, tWarningColor, duration, withIcon, true)
    }

    /**
     * Error style
     */
    fun error(msg: Any, gravity: Int? = tGravity, duration: Int? = Toast.LENGTH_LONG, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_clear_white_48dp, tErrorColor, duration, withIcon, true)
    }

    /**
     * Custom style
     */
    fun custom(msg: Any, gravity: Int? = tGravity,
               @DrawableRes iconRes: Int? = null,
               @ColorInt tintColor: Int? = tNormalColor,
               duration: Int? = Toast.LENGTH_SHORT,
               withIcon: Boolean? = false,
               shouldTint: Boolean? = false) {
        toast(msg, gravity ?: tGravity,
                getDrawable(iconRes),
                tintColor ?: tNormalColor,
                duration ?: Toast.LENGTH_SHORT,
                withIcon ?: false,
                shouldTint ?: false)
    }


    /**
     * Cancel toast
     */
    fun cancelToast() {
        if (mToast != null) {
            mToast!!.cancel()
            mToast = null
        }
    }

    private var toastTextView: TextView? = null

    /**
     * @param msg Any Need to show information，only StringRes or String.
     * @param gravity Toast display position, Gravity.CENTER、Gravity.TOP or Gravity.BOTTOM.
     * @param icon Toast icon，only support Drawable.
     * @param tintColor Toast background color, only support ColorInt.
     * @param duration Toast display time, Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
     * @param withIcon Whether to show Toast icon,true or false.
     * @param shouldTint Whether to modify the Toast background,true or false.
     */
    private fun toast(@NonNull msg: Any, gravity: Int, icon: Drawable?,
                      @ColorInt tintColor: Int, duration: Int,
                      withIcon: Boolean, shouldTint: Boolean) {

        cancelToast()

        var showMsg: CharSequence = ""
        if (msg is Int) {
            showMsg = getString(msg)
        } else if (msg is CharSequence) {
            showMsg = msg
        }

        mToast = Toast.makeText(ToastZLib.getContext(), showMsg, duration)
        val toastLayout: View = (ToastZLib.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.toast_layout, null)
        val toastIcon: ImageView = toastLayout.findViewById(R.id.toast_icon)
        toastTextView = toastLayout.findViewById(R.id.toast_text)

        val drawableFrame: Drawable? = if (shouldTint)
            tint9PatchDrawableFrame(tintColor)
        else
            getDrawable(R.drawable.toast_frame)

        setBackground(toastLayout, drawableFrame)

        val tIcon: Drawable?
        if (tintIcon) {
            if (withIcon) {
                if (icon == null) {
                    throw IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true")
                } else {
                    tIcon = tintIcon(icon, tDefaultTextColor)
                    setBackground(toastIcon, tIcon)
                }
            } else {
                toastIcon.visibility = View.GONE
            }
        } else {
            toastIcon.visibility = View.GONE
        }

        toastTextView?.let {
            it.text = showMsg
            it.setTextColor(tDefaultTextColor)
            it.typeface = currentTypeface
            it.textSize = textSize.toFloat()
        }

        mToast?.let {
            it.view = toastLayout
            it.setGravity(if (gravity == -1) {
                tGravity
            } else {
                gravity
            }, 0, if (gravity == Gravity.CENTER) {
                0
            } else {
                200
            })

            it.show()
        }
    }

    fun with(): Config {
        return Config()
    }

    class Config {
        private var cGravity: Int = ToastZConstant.tGravity

        @ColorInt
        private var cDefaultTestColor: Int = ToastZConstant.tDefaultTextColor

        @ColorInt
        private var cErrorColor: Int = ToastZConstant.tErrorColor

        @ColorInt
        private var cInfoColor: Int = ToastZConstant.tInfoColor

        @ColorInt
        private var cSuccessColor: Int = ToastZConstant.tSuccessColor

        @ColorInt
        private var cWarningColor: Int = ToastZConstant.tWarningColor

        @ColorInt
        private var cNormalColor: Int = ToastZConstant.tNormalColor

        private var cTypeface: Typeface = ToastZConstant.currentTypeface
        private var cTextSize: Int = ToastZConstant.textSize
        private var cTintIcon: Boolean = ToastZConstant.tintIcon

        fun reset() {
            tGravity = ToastZConstant.tGravity
            tDefaultTextColor = ToastZConstant.tDefaultTextColor
            tErrorColor = ToastZConstant.tErrorColor
            tInfoColor = ToastZConstant.tInfoColor
            tSuccessColor = ToastZConstant.tSuccessColor
            tWarningColor = ToastZConstant.tWarningColor
            tNormalColor = ToastZConstant.tNormalColor
            currentTypeface = ToastZConstant.currentTypeface
            textSize = ToastZConstant.textSize
            tintIcon = ToastZConstant.tintIcon
        }

        fun setGravity(gravity: Int): Config {
            cGravity = gravity
            return this
        }

        @CheckResult
        fun setTextColor(@ColorInt textColor: Int): Config {
            cDefaultTestColor = textColor
            return this
        }

        @CheckResult
        fun setErrorColor(@ColorInt errorColor: Int): Config {
            cErrorColor = errorColor
            return this
        }

        @CheckResult
        fun setInfoColor(@ColorInt infoColor: Int): Config {
            cInfoColor = infoColor
            return this
        }

        @CheckResult
        fun setSuccessColor(@ColorInt successColor: Int): Config {
            cSuccessColor = successColor
            return this
        }

        @CheckResult
        fun setWarningColor(@ColorInt warningColor: Int): Config {
            cWarningColor = warningColor
            return this
        }

        @CheckResult
        fun setToastTypeface(@NonNull typeface: Typeface): Config {
            cTypeface = typeface
            return this
        }

        @CheckResult
        fun setTextSize(sizeInSp: Int): Config {
            cTextSize = sizeInSp
            return this
        }

        @CheckResult
        fun tintIcon(tintIcon: Boolean): Config {
            cTintIcon = tintIcon
            return this
        }

        fun apply() {
            tGravity = cGravity
            tDefaultTextColor = cDefaultTestColor
            tNormalColor = cNormalColor
            tErrorColor = cErrorColor
            tInfoColor = cInfoColor
            tSuccessColor = cSuccessColor
            tWarningColor = cWarningColor
            currentTypeface = cTypeface
            textSize = cTextSize
            tintIcon = cTintIcon
        }
    }
}
