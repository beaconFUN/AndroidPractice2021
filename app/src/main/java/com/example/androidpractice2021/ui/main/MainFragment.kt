package com.example.androidpractice2021.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidpractice2021.R
import com.example.androidpractice2021.ui.sub.SubActivity

/**
 * MainFragmentのUIコンポーネント類を管理します。
 * @author Squatarola 2021
 */
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            findViewById<TextView>(R.id.main_fragment_tv_text).apply {
                text = """This is Practise App.
                    |
                    |Test API Server
                    |https://the-most-simple-api.herokuapp.com/
                """.trimMargin()
            }

            findViewById<Button>(R.id.main_fragment_bt_confirm).apply {
                text = "access"
                setOnClickListener {
                    val intent = Intent(requireActivity(), SubActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        //It's not if you can or can't do it.
        //                    What matters is if you want to do it.
    }
}