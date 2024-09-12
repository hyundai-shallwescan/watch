package com.ite.sws

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit 클라이언트
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
object RetrofitClient {
    private const val BASE_URL = BuildConfig.BASE_URL

    // OkHttpClient 설정
    val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                // SharedPreferencesUtil에서 액세스 토큰 가져오기
                val accessToken = SharedPreferencesUtil.getAccessToken()
                // 액세스 토큰이 있으면 헤더에 추가
                if (!accessToken.isNullOrEmpty()) {
                    requestBuilder.addHeader("Authorization", "Bearer $accessToken")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
}