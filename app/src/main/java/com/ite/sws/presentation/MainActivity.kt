package com.ite.sws.presentation

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ite.sws.NumberFormatterUtil
import com.ite.sws.SharedPreferencesUtil
import com.ite.sws.databinding.ActivityMainBinding
import com.ite.sws.parking.api.repository.ParkingRepository

/**
 * 메인 액티비티(주차 정보 조회)
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
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val parkingRepository = ParkingRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferencesUtil.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!SharedPreferencesUtil.getAccessToken().isNullOrEmpty()) {
            binding.button.visibility = View.GONE
            fetchParkingInfo()
        } else {
            binding.button.setOnClickListener {
                fetchParkingInfo()
            }
        }
    }

    /**
     * 주차 정보 가져오기
     */
    private fun fetchParkingInfo() {
        parkingRepository.getParkingInfo(
            onSuccess = { parkingInfo ->
                binding.parkingText.text = when {

                    // 주차정산 완료된 경우
                    parkingInfo.paymentStatus == "ACTIVE" -> {
                        "주차정산 완료."
                    }
                    // 장바구니가 빈 경우
                    parkingInfo.parkingPaymentStatus == "EMPTY" -> {
                        "영수증 할인은 자동으로 이뤄집니다!"
                    }
                    // 장바구니에 담긴 경우
                    parkingInfo.parkingPaymentStatus == "CART" -> {
                        // 추가 결제를 해야 무료 주차 가능한 경우
                        if (parkingInfo.feeForFreeParking > 0) {
                            createSpannableString(
                                "${NumberFormatterUtil.formatCurrencyWithCommas(parkingInfo.feeForFreeParking.toInt())}만 추가하면\n주차 정산이 무료예요!",
                                NumberFormatterUtil.formatCurrencyWithCommas(parkingInfo.feeForFreeParking.toInt())
                            )
                            // 장바구니에 담긴 금액으로 무료 주차가 가능한 경우
                        } else if (parkingInfo.freeParkingTime.matches(Regex("^(?:[01]?\\d|2[0-3]):[0-5]\\d$"))) {
                            createSpannableString(
                                "현재 장바구니에 담긴 금액으로,\n${parkingInfo.freeParkingTime}까지 무료로 출차 가능합니다.",
                                parkingInfo.freeParkingTime
                            )
                        } else {
                            // 주차 시간이 5시간이 넘은 경우(무료 주차 불가)
                            parkingInfo.freeParkingTime
                        }
                    }
                    // 쇼핑 정산 완료된 경우
                    parkingInfo.parkingPaymentStatus == "PAID" -> {
                        // 추가 결제를 해야 무료 주차 가능한 경우
                        if (parkingInfo.feeForFreeParking > 0) {
                            createSpannableString(
                                "${NumberFormatterUtil.formatCurrencyWithCommas(parkingInfo.feeForFreeParking.toInt())}만 추가하면\n주차 정산이 무료예요!",
                                NumberFormatterUtil.formatCurrencyWithCommas(parkingInfo.feeForFreeParking.toInt())
                            )
                            // 결제 완료된 금액으로 무료 주차가 가능한 경우
                        } else if (parkingInfo.freeParkingTime.matches(Regex("^(?:[01]?\\d|2[0-3]):[0-5]\\d$"))) {
                            createSpannableString(
                                "${parkingInfo.freeParkingTime}\n까지 무료로 출차 가능합니다.",
                                parkingInfo.freeParkingTime
                            )
                        } else {
                            // 주차 시간이 5시간이 넘은 경우(무료 주차 불가)
                            parkingInfo.freeParkingTime
                        }
                    }
                    else -> {
                        "주차 정보가 없습니다."
                    }
                }
            },
            onFailure = { error ->
                // 에러 처리
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
        )
    }

    /**
     * 특정 문자열의 크기를 키움
     */
    private fun createSpannableString(fullText: String, targetText: String): SpannableString {
        val spannableString = SpannableString(fullText)
        val startIndex = fullText.indexOf(targetText)
        val endIndex = startIndex + targetText.length
        spannableString.setSpan(RelativeSizeSpan(2.5f), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}