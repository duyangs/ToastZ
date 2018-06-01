package com.duyangs.sample

import android.app.Application
import android.graphics.Color
import android.view.Gravity
import com.duyangs.toastz.ToastZ
import com.duyangs.toastz.ToastZLib


/**
 * "" ToastZ
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2018/6/1.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ToastZLib.init(this)

        ToastZ.with().setGravity(Gravity.TOP)
                .setSuccessColor(Color.parseColor("#909090"))
                .setInfoColor(Color.parseColor("#909090"))
                .setWarningColor(Color.parseColor("#909090"))
                .setErrorColor(Color.parseColor("#909090"))
                .apply()
    }
}