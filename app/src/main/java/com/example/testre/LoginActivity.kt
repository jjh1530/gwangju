package com.example.testre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private var string_uid : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener {
            //이미 로그인한 사람인 경우 currentUser에 유저정보가 담긴다.
            val user = auth.currentUser
            val name = et_name.text
            if (name.toString().equals("")  || name.toString() == null) {
                Toast.makeText(this,"이름을 입력해주세요",Toast.LENGTH_SHORT).show()
                Log.d("ddddd",name.toString())
            }else {
                if (user != null) {
                    // 로그인 한 적이있음
                    string_uid = user.uid
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("string_uid", string_uid)
                    startActivity(intent)
                    Toast.makeText(this, " ${name}님 안녕하세요.", Toast.LENGTH_SHORT).show()
                } else {
                    // 처음 접속
                    auth.signInAnonymously()
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                //접속성공
                                val user = auth.currentUser
                                string_uid = user!!.uid
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this, "처음 로그인 하셨습니다.", Toast.LENGTH_SHORT).show()
                            } else {
                                //접속 실패
                                Toast.makeText(
                                    baseContext, "로그인 실패", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }

        }

    }
}