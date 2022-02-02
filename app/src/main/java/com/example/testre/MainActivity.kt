package com.example.testre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.testre.All.AllActivity
import com.example.testre.Item.ReviewActivity


class MainActivity : AppCompatActivity() {
    private val btn_menu : Button by lazy {
        findViewById(R.id.btn_menu)
    }
    private val btn_all : Button by lazy {
        findViewById(R.id.btn_all)
    }

    private val btn_map : Button by lazy {
        findViewById(R.id.btn_map)
    }
    private var doubleBackToExit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        btn_all.setOnClickListener {
            val intent = Intent(this, AllActivity::class.java)
            startActivity(intent)
        }

        btn_map.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
        }else {
            Toast.makeText(this,"종료하시려면 뒤로가기를 한번 더 눌러주세요.",Toast.LENGTH_SHORT).show()
            doubleBackToExit = true
            runDelayed(1500L) {
                doubleBackToExit = false
            }

        }
    }
    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function,millis)
    }
}
