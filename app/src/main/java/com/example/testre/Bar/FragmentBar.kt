package com.example.testre.Bar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.R
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


        }
        )


        fragmentBarBinding.rvBar.layoutManager = LinearLayoutManager(context)
        fragmentBarBinding.rvBar.adapter = barAdapter


        barDB.addChildEventListener(listener)
    }

    override fun onResume() {
        super.onResume()
        barAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        barDB.removeEventListener(listener)
    }

    //    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view = inflater.inflate(R.layout.fragment_bar, container, false)
//
//
//        val barList = arrayListOf(
//            Bar("1",R.drawable.bar1,"백희","한식과 양식의 조화를 이룬 동명동 맛집"),
//            Bar("2",R.drawable.bar2,"마실","동명동 감성 퓨전 한식 술집"),
//            Bar("3",R.drawable.bar3,"주식","분위기가 좋은 빈티지바와 이탈리아 메뉴"),
//            Bar("4",R.drawable.bar4,"오늘와인한잔","분위기, 맛좋은 안주가 있는 와인바"),
//            Bar("5",R.drawable.bar5,"차오르면","인테리어가 멋있는 와인바"),
//            Bar("6",R.drawable.bar6,"맷도리","저렴하고 맛있는 분위기 맛집 이자카야"),
//            Bar("7",R.drawable.bar7,"꼬쟁이","감성이 살아있는 오뎅바"),
//            Bar("8",R.drawable.bar8,"2F","다양한 한식 메뉴와 칵테일을 즐길 수 잇는 이층집"),
//            Bar("9",R.drawable.bar9,"온우","연어 사시미, 바지락 술찜 안주가 맛있는 집!")
//        )
//
//        val adapter = BarAdapter(barList)
//        view.rv_bar.adapter = adapter
//        view.rv_bar.layoutManager = LinearLayoutManager(context)
//
//        adapter.setItemClickListener(object : BarAdapter.OnItemClickListener{
//            override fun onClick(v: View, position: Int) {
//
//            }
//        })
//
//
//        return view
//    }
}