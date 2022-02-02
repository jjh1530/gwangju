package com.example.testre.Bar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.Item.AcitivityItem
import com.example.testre.ChatList
import com.example.testre.R
import com.example.testre.databinding.AcitivityItemBinding
import com.example.testre.databinding.FragmentBarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FragmentBar : Fragment(R.layout.fragment_bar) {
    private lateinit var barDB: DatabaseReference
    private lateinit var barAdapter : BarAdapter
    private val barList = mutableListOf<Bar>()

    private val listener = object: ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val bar = snapshot.getValue(Bar::class.java)
            bar ?: return

            barList.add(bar)
            barAdapter.submitList(barList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }
    private var binding : FragmentBarBinding? = null
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentBarBinding = FragmentBarBinding.bind(view)
        binding = fragmentBarBinding

        barList.clear()
        barDB = Firebase.database.reference.child("Bar")

        barAdapter = BarAdapter(
            onItemClicked = {bar ->
            val intent = Intent(context, AcitivityItem::class.java)
                intent.putExtra("name",bar.name)
                intent.putExtra("info",bar.info)
                intent.putExtra("picture",bar.picture)
                startActivity(intent)
        })

        fragmentBarBinding.rvBar.layoutManager = LinearLayoutManager(context)
        fragmentBarBinding.rvBar.adapter = barAdapter

        barDB.addChildEventListener(listener)

    }




}