package com.example.testre.Item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.ChatList
import com.example.testre.R
import com.example.testre.databinding.ActivityReviewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ReviewActivity : AppCompatActivity() {
    private lateinit var reviewDB: DatabaseReference
    private lateinit var itemAdapter : ItemAdapter

    private var binding : ActivityReviewBinding? = null

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    private val reviewList = mutableListOf<ChatList>()
    private val listener = object: ChildEventListener {
        // 목록 검색 목록에 대한 추가 수신 대기
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val chatList = snapshot.getValue(ChatList::class.java)
            chatList ?: return

            reviewList.add(chatList)
            itemAdapter.submitList(reviewList)
        }
        // 목록 변경사항에 대한 대기
        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
        // 삭제 대기
        override fun onChildRemoved(snapshot: DataSnapshot) {}
        // 항목 순서 변경사항 수신 대기
        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        itemAdapter = ItemAdapter()
        val binding = ActivityReviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        reviewDB = Firebase.database.reference.child("Review")


        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvReview.adapter = itemAdapter

        findViewById<Button>(R.id.btn_review).setOnClickListener {
            val review = findViewById<EditText>(R.id.et_review).text.toString()
           uploadReview(userId = auth.currentUser.toString(), review)
            reviewDB.addChildEventListener(listener)
        }

    }


    private fun uploadReview(userId: String,title:String ) {
        val model = ChatList(userId, title)
        reviewDB.push().setValue(model)
    }

    override fun onResume() {
        super.onResume()
        itemAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        reviewDB.removeEventListener(listener)
    }
}