package com.ite.sws.parking.data

/**
 * 주차 정산 정보 응답 데이터 클래스
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
data class GetParkingRes(
    val paymentStatus: String?, // 주차 결제 상태 (null, ACTIVE, CANCEL)
    val freeParkingTime: String, // 무료 출차 가능 시간
    val feeForFreeParking: Long, // 무료로 출차하기 위한 금액
    val entranceAt: String, // 입차 시간
    val carNumber: String, // 차량 번호
    val discountParkingHour: Long, // 할인 주차 시간(시)
    val parkingPaymentStatus: String, // EMPTY, CART, PAID
    val parkingHour: Long, // 주차 시간(시)
    val parkingMinute: Long, // 주차 시간(분)
    val paymentHour: Long, // 정산 필요 주차 시간(시)
    val paymentMinute: Long, // 정산 필요 주차 시간(분)
    val parkingFee: Long // 주차 정산 요금
)
