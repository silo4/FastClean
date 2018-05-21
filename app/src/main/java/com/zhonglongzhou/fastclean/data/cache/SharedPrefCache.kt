package com.zhonglongzhou.fastclean.data.cache

import android.content.Context
import androidx.content.edit
import com.google.gson.reflect.TypeToken
import com.zhonglongzhou.fastclean.data.entity.businessA.BusinessAEntity
import com.zhonglongzhou.fastclean.data.entity.businessB.BusinessBEntity
import com.zhonglongzhou.fastclean.data.tools.JsonTools

/**
 * Created by zhonglz on 2018/5/4
 */

class SharedPrefCache(private val context: Context){
    private val SHARED_PREFS = "fast_interaction_shared_prefs"

    private val KEY_BUSINESS_A = "businessA"
    private val KEY_BUSINESS_B = "businessB"

    private val prefs by lazy {
        context.applicationContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }

    fun getBussinessA() : BusinessAEntity?{

        val json = prefs.getString(KEY_BUSINESS_A, "")

        return json?.let {
            val type = object : TypeToken<BusinessAEntity>(){}.type
            JsonTools.fromJson<BusinessAEntity>(json, type) } ?: BusinessAEntity("create A")
    }

    fun updateBusinessA(info: BusinessAEntity?) : Boolean{
        val json = JsonTools.toJson(info)
        //KTX 写法
        prefs.edit {
            putString(KEY_BUSINESS_A, json)
        }
        return true
    }

    fun getBussinessB() : BusinessBEntity?{

        val json = prefs.getString(KEY_BUSINESS_B, "")

        return json?.let {
            val type = object : TypeToken<BusinessBEntity>(){}.type
            JsonTools.fromJson<BusinessBEntity>(json, type) }
    }

    fun updateBussinessB(info: BusinessBEntity?) : Boolean{
        val json = JsonTools.toJson(info)
        //KTX 写法
        prefs.edit {
            putString(KEY_BUSINESS_B, json)
        }
        return true
    }
}