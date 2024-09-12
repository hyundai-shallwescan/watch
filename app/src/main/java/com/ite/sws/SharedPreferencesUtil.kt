package com.ite.sws

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferencesUtil
 * @author 남진수
 * @since 2024.09.12
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12   남진수       최초 생성
 * </pre>
 */
object SharedPreferencesUtil {

    private const val PREFS_NAME = "auth_prefs"
    private const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
    private const val KEY_CART_ID = "CART_ID"
    private const val KEY_CART_MEMBER_NAME = "NAME"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveString(context: Context, key: String, value: String) {
        val editor = getPreferences(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String, defaultValue: String? = null): String? {
        return getPreferences(context).getString(key, defaultValue)
    }

    fun saveInt(context: Context, key: String, value: Int) {
        val editor = getPreferences(context).edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
        return getPreferences(context).getInt(key, defaultValue)
    }

    fun saveLong(context: Context, key: String, value: Long) {
        val editor = getPreferences(context).edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(context: Context, key: String, defaultValue: Long = 0): Long {
        return getPreferences(context).getLong(key, defaultValue)
    }

    fun saveBoolean(context: Context, key: String, value: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(context: Context, key: String, defaultValue: Boolean = false): Boolean {
        return getPreferences(context).getBoolean(key, defaultValue)
    }

    fun remove(context: Context, key: String) {
        val editor = getPreferences(context).edit()
        editor.remove(key)
        editor.apply()
    }

    fun clear(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }

    /**
     * 액세스 토큰 저장
     */
    fun setAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply()
    }

    /**
     * 액세스 토큰 가져오기
     */
    fun getAccessToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    /**
     * 액세스 토큰 삭제
     */
    fun removeAccessToken() {
        sharedPreferences.edit().remove(KEY_ACCESS_TOKEN).apply()
    }

    /**
     * 데이터 모두 삭제
     */
    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    /**
     * 카트 ID 저장
     */
    fun setCartId(cartId: Long) {
        sharedPreferences.edit().putLong(KEY_CART_ID, cartId).apply()
    }

    /**
     * 카트 ID 가져오기
     */
    fun getCartId(): Long {
        return sharedPreferences.getLong(KEY_CART_ID, 0)
    }

    /**
     * 장바구니 멤버 이름 저장
     */
    fun setCartMemberName(name: String) {
        sharedPreferences.edit().putString(KEY_CART_MEMBER_NAME, name).apply()
    }

    /**
     * 장바구니 멤버 이름 가져오기
     */
    fun getCartMemberName(): String? {
        return sharedPreferences.getString(KEY_CART_MEMBER_NAME, "")
    }
}