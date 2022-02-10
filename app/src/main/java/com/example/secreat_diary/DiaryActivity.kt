package com.example.secreat_diary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity: AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper()) // main Thread 연결


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val diaryEditText = findViewById<EditText>(R.id.diary)
        val detailference = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailference.getString("detail",""))
        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail",diaryEditText.text.toString())
            }
        }

        diaryEditText.addTextChangedListener{
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable,500)// 0.5초에 한번씩 저장한다
            //    detailference.edit{
//                putString("detail",diaryEditText.text.toString()) // 한글자씩 저장한다. 비효율적일수도
        }
    }// 글이 번경될때마다 호출되어 자동저장 / 중간에 앱 죽거나 꺼졌을때 데이터 유실을 방지
}
