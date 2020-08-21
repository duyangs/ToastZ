package com.duyangs.sample

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.duyangs.toastz.Config
import com.duyangs.toastz.ToastZ


/**
 * "Customize Application" ToastZ
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2018/6/4.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val config = Config(
                gravity = Gravity.TOP,
                defaultTextColor = Color.parseColor("#FFFFFF"),
                successColor = Color.parseColor("#909090"),
                infoColor = Color.parseColor("#909090"),
                warningColor = Color.parseColor("#909090"),
                errorColor = Color.parseColor("#909090"),
                normalColor = Color.parseColor("#909090"),
                typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL),
                textSize = 16,
                tintIcon = true,
                yOffset = 200,
                toastFrameResId = R.drawable.toast_frame)

        ToastZ.init(this, config)
    }
}