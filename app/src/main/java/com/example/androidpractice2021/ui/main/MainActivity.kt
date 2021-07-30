package com.example.androidpractice2021.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidpractice2021.R

/**
 * アプリケーション実行時、最初に実行されるActicityです。
 * MainFragmentの表示の処理のみを行っています。
 * @author Squatarola 2021
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment_main, MainFragment()).commit()
    }
}