package com.duyangs.sample

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.duyangs.toastz.ToastZ
import com.duyangs.toastz.ToastZLib


/**
 * "Customize Application" ToastZ
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2018/6/4.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ToastZLib.init(this)

        ToastZ.with().setGravity(Gravity.TOP)//Set the Toast display position， Gravity.CENTER、Gravity.TOP or Gravity.BOTTOM.
                .setSuccessColor(Color.parseColor("#909090"))//Set“Success”mode the Toast background color,@ColorInt
                .setInfoColor(Color.parseColor("#909090"))//Set“Info”mode the Toast background color,@ColorInt
                .setWarningColor(Color.parseColor("#909090"))//Set“Warning”mode the Toast background color,@ColorInt
                .setErrorColor(Color.parseColor("#909090"))//Set“Error”mode the Toast background color,@ColorInt
                .setTextSize(16)//Set the Toast text size，unit:sp
                .setTextColor(Color.parseColor("#FFFFFF"))//Set Toast text color，@ColorInt
                .setToastTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL))//Set Toast font，Typeface
                .tintIcon(true)//Set whether Toast Icon is displayed，true or false
                .apply()
    }
}