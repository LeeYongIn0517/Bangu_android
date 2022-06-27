package com.example.bangu

import com.example.bangu.login.data.LoginAPI
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse
import com.google.gson.Gson
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MockWebServerTest: FunSpec() {

    private lateinit var server : MockWebServer

    override fun beforeSpec(spec: Spec) {
        server = MockWebServer()
        server.start()
        println("beforeSpec #####")
    }

    override fun afterSpec(spec: Spec) {
        server.shutdown()
        println("beforeTest >>>>>")
    }
    init {
        test("Test MockServer"){
            val baseUrl = server.url("/v1/")

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            //인터페이스 구현
            val service = retrofit.create(LoginAPI::class.java)

            val req = LoginRequest(
                userId = "userId",
                password = "password"
            )
            val res = LoginResponse(
                grantType = "grantType",
                accessToken = "accessToken",
                refreshToken = "refreshToken",
                accessTokenExpireDate = 1)

            //모의 응답: 클라이언트에서 요청을 보내면 준비된 모의응답을 출력
            val response = MockResponse()
                .setResponseCode(200)
                .setBody(Gson().toJson(res))

            server.enqueue(response)
            service.getLoginToken(req)
                .test()
                .awaitDone(2000,TimeUnit.SECONDS)
                //에러가 없는지 확인
                .assertNoErrors()
                //원하는 값을 가지고 있는지 확인
                .assertValue{
                    it.grantType == "grantType"
                    it.accessToken == "accessToken"
                    it.refreshToken == "refreshToken"
                    it.accessTokenExpireDate == 1
                }
        }
    }
}