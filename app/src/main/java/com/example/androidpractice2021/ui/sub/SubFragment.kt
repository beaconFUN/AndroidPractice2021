package com.example.androidpractice2021.ui.sub

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidpractice2021.R
import com.example.androidpractice2021.api.SimpleAPI
import com.example.androidpractice2021.model.SimpleModel

/**
 * SubFragmentのUIコンポーネント類を管理します。
 * @author Squatarola 2021
 */
class SubFragment :
    Fragment(),
    SimpleAPI.NotifyFinished
{
    private val simpleAPI = SimpleAPI(this)
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sub, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("View", "SubFragment view created.")

        view.findViewById<TextView>(R.id.tv_result).text = "Now Loading..."

        simpleAPI.getApi()
    }

    override fun onFinished(model: SimpleModel) {
        //GETしてきたテキストをTextViewに反映させる。
        requireView().findViewById<TextView>(R.id.tv_result).text = model.text
    }

    override fun onFailure() {
        //ダイアログはUIスレッドでないと発行できないため、明示的にUIスレッドに処理させる。
        this.handler.post {
            AlertDialog.Builder(requireContext())
                .setTitle("エラー")
                .setMessage("サーバとの通信に失敗しました。")
                .setPositiveButton("再試行") { _, _ -> simpleAPI.getApi() }
                .setNegativeButton("キャンセル") { _, _ -> }
                .create()
                .show()
        }
    }
}