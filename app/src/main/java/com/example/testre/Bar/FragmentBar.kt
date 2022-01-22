package com.example.testre.Bar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.Test.AllActivityitem
import com.example.testre.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all_item.*
import kotlinx.android.synthetic.main.activity_bar_acitivity_item.*
import kotlinx.android.synthetic.main.fragment_bar.view.*

class FragmentBar : Fragment() {
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bar, container, false)


        val barList = arrayListOf(
            Bar("1",R.drawable.bar1,"백희","한식과 양식의 조화를 이룬 동명동 맛집"),
            Bar("2",R.drawable.bar2,"마실","동명동 감성 퓨전 한식 술집"),
            Bar("3",R.drawable.bar3,"주식","분위기가 좋은 빈티지바와 이탈리아 메뉴"),
            Bar("4",R.drawable.bar4,"오늘와인한잔","분위기, 맛좋은 안주가 있는 와인바"),
            Bar("5",R.drawable.bar5,"차오르면","인테리어가 멋있는 와인바"),
            Bar("6",R.drawable.bar6,"맷도리","저렴하고 맛있는 분위기 맛집 이자카야"),
            Bar("7",R.drawable.bar7,"꼬쟁이","감성이 살아있는 오뎅바"),
            Bar("8",R.drawable.bar8,"2F","다양한 한식 메뉴와 칵테일을 즐길 수 잇는 이층집"),
            Bar("9",R.drawable.bar9,"온우","연어 사시미, 바지락 술찜 안주가 맛있는 집!")
        )

        val adapter = BarAdapter(barList)
        view.rv_bar.adapter = adapter
        view.rv_bar.layoutManager = LinearLayoutManager(context)

        adapter.setItemClickListener(object : BarAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {

                val intent = Intent(context, BarAcitivityItem::class.java)
                tv_bar_item_name.setText("이름")
                startActivity(intent)

            }
        })


        return view
    }
}