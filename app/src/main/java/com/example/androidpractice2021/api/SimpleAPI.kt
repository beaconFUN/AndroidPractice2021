package com.example.androidpractice2021.api

import android.util.Log
import com.example.androidpractice2021.model.SimpleModel
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.*
import java.io.IOException

/**
 * REST APIと実際に通信を行う処理です。
 * Herokuを使用しているので、アクセス完了に最大10秒程度かかる可能性があります。
 * @author Squatarola 2021
 */
class SimpleAPI(
    private val listener: NotifyFinished
) {

    /**
     * 非同期処理の完了を実装先に通知するためのインターフェースです。
     */
    interface NotifyFinished {
        fun onFinished(model: SimpleModel)
        fun onFailure(mes: String)
    }

    // インスタンス生成が重いので事前に生成しておく。
    // これらはスレッドセーフなので、Objectとして共通化できます。
    private val client = OkHttpClient.Builder().build()
    private val requestBuilder = Request.Builder()
    private val jacksonObjectMapper = jacksonObjectMapper()

    /**
     * 非同期でHTTPリクエストを処理します。
     * 結果はNotifyFinishedをリッスンしている先に通知されます。
     */
    fun getApi() {
        val request = this.requestBuilder
            .url("https://the-most-simple-api.herokuapp.com/")
            .get()
            .build()

        Log.d("API", "GET ${request.url}")

        this.client.newCall(request).enqueue( object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: throw IllegalAccessException("サーバに接続できませんでした。")

                Log.d("API", "onResponse ${request.url}")

                //レスポンスコードで処理分け
                val model = requireNotNull(
                    when (response.code) {
                    200 -> body.parseJson<SimpleModel>()
                    else -> {
                        listener.onFailure("${response.code} : サーバに接続できませんでした。")
                        null
                    }
                })

                //リスナーに処理完了を通知
                listener.onFinished(model)
                Log.d("API", "Finished getApi()")
            }

            override fun onFailure(call: Call, e: IOException) {
                listener.onFailure("サーバに接続できませんでした。")
                throw IllegalAccessException("${e.printStackTrace()} : サーバに接続できませんでした。")
            }
        })
    }

    /**
     * Stringから指定されたmodelを用いてJSONのパースを行います。
     * @param T: Class
     * @return T?
     */
    private inline fun <reified T> String.parseJson(): T?  =
        try {
            jacksonObjectMapper.readValue(this, T::class.java)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            null
        } catch (e: JsonMappingException) {
            e.printStackTrace()
            null
        }

}