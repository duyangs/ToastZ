package com.duyangs.toastz

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.NonNull

@SuppressLint("StaticFieldLeak")
/**
 * <p>Project:ToastZ</p>
 * <p>Package:com.duyangs.toastz</p>
 * <p>Description:Initialization class</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/06/04
 */
object ToastZLib {

    private var mContext: Context? = null

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    fun init(context : Context ) {
        mContext = context.applicationContext
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
}
