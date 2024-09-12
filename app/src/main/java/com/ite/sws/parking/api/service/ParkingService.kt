package com.ite.sws.parking.api.service

import com.ite.sws.parking.data.GetParkingRes
import retrofit2.Call
import retrofit2.http.GET

/**
 * 주차 서비스 인터페이스
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
interface ParkingService {
    /**
     * 주차 정산 정보 조회
     */
    @GET("parkings")
    fun getParkingInfo(): Call<GetParkingRes>
}