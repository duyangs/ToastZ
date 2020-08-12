package com.duyangs.toastz

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull

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

    private var config = Config()
    private var mToast: Toast? = null
    private var mContext: Context? = null

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    fun init(context: Context, config: Config? = null) {
        mContext = context.applicationContext
        config?.let {
            this.config = config
        }
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    fun getContext(): Context {
        if (mContext != null)
            return mContext as Context
        else
            throw NullPointerException("u should init first")
    }

    /**
     * Normal style
     */
    fun normal(msg: Any, duration: Int? = null, gravity: Int? = null) {
        custom(msg, gravity, null, config.getNormalColor(), duration)
    }

    /**
     * Success style
     */
    fun success(msg: Any, duration: Int? = null, gravity: Int? = null, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_check_white_48dp, config.getSuccessColor(), duration, withIcon, true)
    }

    /**
     * Info style
     */
    fun info(msg: Any, duration: Int? = null, gravity: Int? = null, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_info_outline_white_48dp, config.getInfoColor(), duration, withIcon, true)
    }

    /**
     * Warning style
     */
    fun warning(msg: Any, duration: Int? = null, gravity: Int? = null, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_error_outline_white_48dp, config.getWarningColor(), duration, withIcon, true)
    }

    /**
     * Error style
     */
    fun error(msg: Any, duration: Int? = null, gravity: Int? = null, withIcon: Boolean? = true) {
        custom(msg, gravity, R.drawable.ic_clear_white_48dp, config.getErrorColor(), duration, withIcon, true)
    }

    /**
     * Custom style 自定义
     */
    fun custom(msg: Any, gravity: Int? = null,
            @DrawableRes iconRes: Int? = null,
            @ColorInt tintColor: Int? = null,
            duration: Int? = null,
            withIcon: Boolean? = false,
            shouldTint: Boolean? = false) {

        toast(msg, gravity ?: config.getGravity(),
                getDrawable(iconRes),
                tintColor ?: config.getNormalColor(),
                duration ?: Toast.LENGTH_LONG,
                withIcon ?: false,
                shouldTint ?: false)
    }

    /**
     * Cancel toast
     */
    fun cancel() {
        mToast?.let {
            it.cancel()
            mToast = null
        }
    }

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

        cancel()

        fun tintIconVerify(): Boolean = (config.getTintIcon() and withIcon and (icon != null))

        fun layoutSetBackground(toastLayout: View) {
            setBackground(toastLayout, getDrawableFrame(shouldTint, tintColor))
        }

        fun tintIconSetBackground(toastIcon: ImageView) {
            if (tintIconVerify()) {
                val tIcon = tintIcon(icon, config.getDefaultTestColor())
                setBackground(toastIcon, tIcon)
            } else {
                toastIcon.visibility = View.GONE
            }
        }

        fun setTextView(toastTextView: TextView, msg: CharSequence) {
            toastTextView.let {
                it.text = msg
                it.setTextColor(config.getDefaultTestColor())
                it.typeface = config.getTypeface()
                it.textSize = config.getTextSize().toFloat()
            }
        }

        fun show(toastLayout: View) {
            mToast?.let {
                it.view = toastLayout
                it.setGravity(gravity, 0, getYOffset(gravity))
                it.show()
            }
        }

        val showMsg = msgFormat(msg)
        val (toast, child) = loadToastLayout(showMsg, duration)
        val (toastLayout, toastIcon, toastTextView) = child
        mToast = toast

        layoutSetBackground(toastLayout)
        tintIconSetBackground(toastIcon)
        setTextView(toastTextView, showMsg)
        show(toastLayout)
    }
}
