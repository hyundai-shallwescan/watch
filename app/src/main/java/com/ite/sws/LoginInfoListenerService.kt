package com.ite.sws

import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

/**
 * 로그인 정보 수신 서비스
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
class LoginInfoListenerService : WearableListenerService() {

    /**
     * 로그인 시 발급받은 토큰을 SharedPreferences에 저장
     */
    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/login_token") {
            val token = String(messageEvent.data)
            // 토큰을 SharedPreferences에 저장
            SharedPreferencesUtil.setAccessToken(token)
        }
    }
}
