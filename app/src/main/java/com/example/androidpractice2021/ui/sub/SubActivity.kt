package com.example.androidpractice2021.ui.sub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpractice2021.R

/**
 * アプリケーション実行時、最初に実行されるActicityです。
 * MainFragmentの表示の処理のみを行っています。
 * @author Squatarola 2021
 */
class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contaner_fragment_sub, SubFragment()).commit()
    }
}