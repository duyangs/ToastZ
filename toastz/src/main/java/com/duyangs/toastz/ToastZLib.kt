package com.duyangs.toastz

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.NonNull

@SuppressLint("StaticFieldLeak")
/**
 * <p>Project:ToastZ</p>
 * <p>Package:com.duyangs.toastz</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/19 0019
 */
object ToastZLib {

    private var mContext: Context? = null

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    fun init(@NonNull context : Context ) {
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
