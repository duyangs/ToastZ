package com.duyangs.toastz

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.annotation.CheckResult
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.NonNull
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description: Toast 分装工具类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
@SuppressLint("InflateParams", "StaticFieldLeak")
object ToastZ {


    private var tGravity: Int = ToastZConstant.tGravity

    @ColorInt
    private var tDefaultTestColor: Int = ToastZConstant.tDefaultTestColor
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

    val TYPE_ERROR: Int = 1001
    val TYPE_INFO: Int = 1002
    val TYPE_SUCCESS: Int = 1003
    val TYPE_WARNING: Int = 1004

    private var mToast: Toast? = null

    init {
        // avoiding instantiation
    }

    fun normal(msg: Any) {
        normal(msg, tGravity)
    }

    fun normal(msg: Any, gravity: Int) {
        normal(msg, gravity, Toast.LENGTH_SHORT)
    }

    fun normal(msg: Any, gravity: Int, duration: Int) {
        normal(msg, gravity, duration, null)
    }

    fun normal(msg: Any, gravity: Int, duration: Int, icon: Drawable?) {
        normal(msg, gravity, duration, icon, icon != null)
    }

    fun normal(msg: Any, gravity: Int, duration: Int, icon: Drawable?, withIcon: Boolean) {
        custom(msg, gravity, icon, tNormalColor, duration, withIcon, true)
    }

    fun success(msg: Any) {
        success(msg, tGravity)
    }

    fun success(msg: Any, gravity: Int) {
        success(msg, gravity, Toast.LENGTH_SHORT)
    }

    fun success(msg: Any, gravity: Int, duration: Int) {
        success(msg, gravity, duration, true)
    }

    fun success(msg: Any, gravity: Int, duration: Int, withIcon: Boolean) {
        custom(msg, gravity, ToastZUtil.getDrawable(ToastZLib.getContext(), R.drawable.ic_check_white_48dp),
                tSuccessColor, duration, withIcon, true)
    }

    fun info(msg: Any) {
        info(msg, tGravity)
    }

    fun info(msg: Any, gravity: Int) {
        info(msg, gravity, Toast.LENGTH_SHORT)
    }

    fun info(msg: Any, gravity: Int, duration: Int) {
        info(msg, gravity, duration, true)
    }

    fun info(msg: Any, gravity: Int, duration: Int, withIcon: Boolean) {
        custom(msg, gravity, ToastZUtil.getDrawable(ToastZLib.getContext(), R.drawable.ic_info_outline_white_48dp),
                tInfoColor, duration, withIcon, true)
    }

    fun warning(msg: Any) {
        warning(msg, tGravity)
    }

    fun warning(msg: Any, gravity: Int) {
        warning(msg, gravity, Toast.LENGTH_SHORT)
    }

    fun warning(msg: Any, gravity: Int, duration: Int) {
        warning(msg, gravity, duration, true)
    }

    fun warning(msg: Any, gravity: Int, duration: Int, withIcon: Boolean) {
        custom(msg, gravity, ToastZUtil.getDrawable(ToastZLib.getContext(), R.drawable.ic_error_outline_white_48dp),
                tWarningColor, duration, withIcon, true)
    }

    fun error(msg: Any) {
        error(msg, tGravity)
    }

    fun error(msg: Any, gravity: Int) {
        error(msg, gravity, Toast.LENGTH_SHORT)
    }

    fun error(msg: Any, gravity: Int, duration: Int) {
        error(msg, gravity, duration, true)
    }

    fun error(msg: Any, gravity: Int, duration: Int, withIcon: Boolean) {
        custom(msg, gravity, ToastZUtil.getDrawable(ToastZLib.getContext(), R.drawable.ic_clear_white_48dp),
                tErrorColor, duration, withIcon, true)
    }


    fun custom(@NonNull msg: Any, @DrawableRes icon: Drawable, @ColorInt tintColor: Int, duration: Int) {
        custom(msg, tGravity, icon, tintColor, duration, true, false)
    }

    fun custom(@NonNull msg: Any, @DrawableRes icon: Drawable, @ColorInt tintColor: Int, duration: Int, withIcon: Boolean) {
        custom(msg, tGravity, icon, tintColor, duration, withIcon, false)
    }

    fun custom(@NonNull msg: Any, @DrawableRes iconRes: Int, @ColorInt tintColor: Int, duration: Int,
               withIcon: Boolean, shouldTint: Boolean) {
        custom(msg, tGravity, ToastZUtil.getDrawable(ToastZLib.getContext(), iconRes), tintColor, duration, withIcon, shouldTint)
    }


    /**
     * cancel toast
     */
    fun cancelToast() {
        if (mToast != null) {
            mToast!!.cancel()
            mToast = null
        }
    }

    private var toastTextView: TextView? = null

    fun custom(@NonNull msg: Any, gravity: Int, icon: Drawable?,
               @ColorInt tintColor: Int, duration: Int,
               withIcon: Boolean, shouldTint: Boolean) {

        cancelToast()

        var showMsg = ""
        if (msg is Int) {
            showMsg = ToastZLib.getContext().getString(msg)
        } else if (msg is String) {
            showMsg = msg
        }

        mToast = Toast.makeText(ToastZLib.getContext(), showMsg, duration)
        val toastLayout: View = (ToastZLib.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.toast_layout, null)
        val toastIcon: ImageView = toastLayout.findViewById(R.id.toast_icon)
        toastTextView = toastLayout.findViewById(R.id.toast_text)

        var drawableFrame: Drawable? = null

        drawableFrame = if (shouldTint)
            ToastZUtil.tint9PatchDrawableFrame(ToastZLib.getContext(), tintColor)
        else
            ToastZUtil.getDrawable(ToastZLib.getContext(), R.drawable.toast_frame)

        ToastZUtil.setBackground(toastLayout, drawableFrame)

        var tIcon: Drawable? = null
        if (withIcon) {
            if (icon == null)
                throw IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true")
            if (tintIcon) {
                tIcon = ToastZUtil.tintIcon(icon, tDefaultTestColor)
            }
            ToastZUtil.setBackground(toastIcon, tIcon!!)
        } else {
            toastIcon.visibility = View.GONE
        }

        toastTextView!!.text = showMsg
        toastTextView!!.setTextColor(tDefaultTestColor)
        toastTextView!!.typeface = currentTypeface
        toastTextView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())

        mToast!!.view = toastLayout
        mToast!!.setGravity(if (gravity == -1) {
            tGravity
        } else {
            gravity
        }, 0, if (gravity == Gravity.CENTER) {
            0
        } else {
            200
        })

        mToast!!.show()
    }

    fun with(): Config {
        return Config()
    }

    class Config() {
        private var cGravity: Int = ToastZConstant.tGravity
        @ColorInt
        private var cDefaultTestColor: Int = ToastZConstant.tDefaultTestColor
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
            ToastZ.tGravity = ToastZConstant.tGravity
            ToastZ.tDefaultTestColor = ToastZConstant.tDefaultTestColor
            ToastZ.tErrorColor = ToastZConstant.tErrorColor
            ToastZ.tInfoColor = ToastZConstant.tInfoColor
            ToastZ.tSuccessColor = ToastZConstant.tSuccessColor
            ToastZ.tWarningColor = ToastZConstant.tWarningColor
            ToastZ.tNormalColor = ToastZConstant.tNormalColor
            ToastZ.currentTypeface = ToastZConstant.currentTypeface
            ToastZ.textSize = ToastZConstant.textSize
            ToastZ.tintIcon = ToastZConstant.tintIcon
        }

        fun setGravity(gravity: Int): ToastZ.Config {
            cGravity = gravity
            return this
        }

        @CheckResult
        fun setTextColor(@ColorInt textColor: Int): ToastZ.Config {
            cDefaultTestColor = textColor
            return this
        }

        @CheckResult
        fun setErrorColor(@ColorInt errorColor: Int): ToastZ.Config {
            cErrorColor = errorColor
            return this
        }

        @CheckResult
        fun setInfoColor(@ColorInt infoColor: Int): ToastZ.Config {
            cInfoColor = infoColor
            return this
        }

        @CheckResult
        fun setSuccessColor(@ColorInt successColor: Int): ToastZ.Config {
            cSuccessColor = successColor
            return this
        }

        @CheckResult
        fun setWarningColor(@ColorInt warningColor: Int): ToastZ.Config {
            cWarningColor = warningColor
            return this
        }

        @CheckResult
        fun setToastTypeface(@NonNull typeface: Typeface): ToastZ.Config {
            cTypeface = typeface
            return this
        }

        @CheckResult
        fun setTextSize(sizeInSp: Int): ToastZ.Config {
            cTextSize = sizeInSp
            return this
        }

        @CheckResult
        fun tintIcon(tintIcon: Boolean): ToastZ.Config {
            cTintIcon = tintIcon
            return this
        }

        fun apply() {
            ToastZ.tGravity = cGravity
            ToastZ.tDefaultTestColor = cDefaultTestColor
            ToastZ.tErrorColor = cErrorColor
            ToastZ.tInfoColor = cInfoColor
            ToastZ.tSuccessColor = cSuccessColor
            ToastZ.tWarningColor = cWarningColor
            ToastZ.currentTypeface = cTypeface
            ToastZ.textSize = cTextSize
            ToastZ.tintIcon = cTintIcon
        }
    }
}
