package com.example.bangu.signup.ui

class Event<out T>(private val content:T) {
    var hasBeenHandled = false

    fun getSignIfEvented():T?{
        return if(hasBeenHandled){ //이벤트가 이미 처리된 상태
            null
        } else {
            hasBeenHandled = true //이벤트 처리 표시하기
            content //값 반환
        }
    }
    /**
     * 이벤트의 처리 여부에 상관 없이 값을 반환
     */
    fun peekContent():T = content
}