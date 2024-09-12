package com.ite.sws

/**
 * 에러 응답 데이터 클래스
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
data class ErrorRes(
    val status: Int,
    val errorCode: String,
    val message: String
)