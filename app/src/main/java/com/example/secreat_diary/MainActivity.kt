package com.example.secreat_diary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.System.putString
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply{
                minValue = 0
                maxValue = 9
            }
    }
    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }
    private val changeButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changeButton)
    }

    private var changeMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener{

            if(changeMode){
                Toast.makeText(this,"비밀번호 변경 중입니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreference.getString("password","000").equals(passwordFromUser)){
                //TODO 다이어리 페이지 작성 후에 넘겨주어야함
                startActivity(Intent(this,DiaryActivity::class.java))
            }else{
                showErrorAlertDialog()
            }

        }
        changeButton.setOnClickListener{
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(changeMode){
                passwordPreference.edit(true){
                    putString("password",passwordFromUser)
                }
                changeMode = false
                changeButton.setBackgroundColor(Color.BLACK)
                Toast.makeText(this,"변경되었습니다",Toast.LENGTH_SHORT).show()
                //번호를 저장하는 기능
            }else{
                //mode활성화 :: 비밀번호가 맞는지를 체크

                if (passwordPreference.getString("password","000").equals(passwordFromUser)){
                    changeMode = true
                    Toast.makeText(this,"변경할 패스워드를 입력해주세요",Toast.LENGTH_SHORT).show()
                    changeButton.setBackgroundColor(Color.RED)
                }else{
                    showErrorAlertDialog()
                }

            }
        }

    }
    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인"){ _, _-> }
            .create()
            .show()
    }
}