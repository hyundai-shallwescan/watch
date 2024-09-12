package com.ite.sws

import java.text.DecimalFormat

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
object NumberFormatterUtil {

    // 숫자를 천 단위로 쉼표로 구분하여 반환
    fun formatWithComma(number: Int): String {
        val formatter = DecimalFormat("#,###")
        return formatter.format(number)
    }

    // 금액을 천 단위로 쉼표로 구분하고 "원" 단위를 붙여 반환
    fun formatCurrencyWithCommas(amount: Int): String {
        return "${formatWithComma(amount)}원"
    }
}