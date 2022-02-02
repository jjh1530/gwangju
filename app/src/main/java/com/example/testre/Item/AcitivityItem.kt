package com.example.testre.Item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.ChatList
import com.example.testre.R
import com.example.testre.databinding.AcitivityItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso



class AcitivityItem : AppCompatActivity() {
    private lateinit var binding: AcitivityItemBinding


    private val barname: TextView by lazy {
        findViewById(R.id.tv_bar_item_name)
    }
    private val barInfo: TextView by lazy {
        findViewById(R.id.tv_bar_item_info)
    }

    private val barPicture : ImageView by lazy {
        findViewById(R.id.tv_bar_picture)
    }

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = AcitivityItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val info = intent.getStringExtra("info")

        barname?.text = name.toString()
        barInfo?.text = info.toString()
        // 의존성 추가 후 imageuri 연결
        val imageuri = intent.getStringExtra("picture");
        Picasso.with(this).load(imageuri).into(barPicture)

    }

}