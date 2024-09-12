package com.ite.sws.parking.api.repository

import com.google.gson.Gson
import com.ite.sws.ErrorRes
import com.ite.sws.RetrofitClient
import com.ite.sws.parking.api.service.ParkingService
import com.ite.sws.parking.data.GetParkingRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 주차 Repository
 * @author 남진수
 * @since 2024.09.12
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12   남진수       최초 생성
 * 2024.09.12   남진수       주차 정산 정보 조회
 * </pre>
 */
class ParkingRepository {

    private val parkingService =
        RetrofitClient.instance.create(ParkingService::class.java)

    /**
     * 주차 정산 정보 조회
     */
    fun getParkingInfo(
        onSuccess: (GetParkingRes) -> Unit,
        onFailure: (ErrorRes) -> Unit
    ) {
        val call = parkingService.getParkingInfo()
        call.enqueue(object : Callback<GetParkingRes> {
            override fun onResponse(call: Call<GetParkingRes>, response: Response<GetParkingRes>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    val errorBodyString = response.errorBody()?.string()
                    val errorRes = Gson().fromJson(errorBodyString, ErrorRes::class.java)
                    onFailure(errorRes)
                }
            }

            override fun onFailure(call: Call<GetParkingRes>, t: Throwable) {
                t.printStackTrace()
                val networkError = ErrorRes(
                    status = 0,
                    errorCode = "NETWORK_ERROR",
                    message = t.localizedMessage ?: "Unknown network error"
                )
                onFailure(networkError)
            }
        })
    }
}
