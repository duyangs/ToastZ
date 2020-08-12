package com.duyangs.sample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.duyangs.toastz.ToastZ

@SuppressLint("Registered")
/**
 * <p>Project:ToastZSample</p>
 * <p>Package:com.duyangs.sample</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/06/01
 */
class ToastUtilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
    }

    fun onClick(view: View) {
        when(view.id){
            R.id.normal -> ToastZ.normal("normal")
            R.id.success -> ToastZ.success(R.string.success)
            R.id.info -> ToastZ.info("info")
            R.id.warning -> ToastZ.warning("warning")
            R.id.error -> ToastZ.error("error")
            R.id.ac_toast_cancel -> ToastZ.cancel()
        }
    }

}

