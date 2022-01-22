package com.example.testre.Cafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testre.R
import kotlinx.android.synthetic.main.activity_bar_acitivity_item.*
import kotlinx.android.synthetic.main.fragment_cafe.view.*


class FragmentCafe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cafe, container, false)

        val cafeList = arrayListOf(
            Cafe("1",R.drawable.cafe1,"티소하","광주 동명동 분위기 좋은 찻집"),
            Cafe("2",R.drawable.cafe2,"게더링","주택을 개조한 아늑하고 세련된 인테리어 카페"),
            Cafe("3",R.drawable.cafe3,"스콘스","스콘 맛집! 스콘스!"),
            Cafe("4",R.drawable.cafe4,"티앗","독특한 분위기, 아기자기. 분위기 좋은 카페!"),
            Cafe("5",R.drawable.cafe5,"카페온화 광주","프리미엄 수플레 팬케이크 엔 드립커피 전문점"),
            Cafe("6",R.drawable.cafe6,"코코로나인","모든 디저트 수제로 만드는 디저트 맛집!"),
            Cafe("7",R.drawable.cafe7,"도피","12시간 이상 저온숙성한 반죽으로 만드는 도넛!"),
            Cafe("8",R.drawable.cafe8,"솔티드머랭","수제 케이크 아늑하고 모던한 분위기 카페"),
            Cafe("9",R.drawable.cafe9,"데일리오아시스","수플레와 차의 조화가 이루어진 카페")
        )
        val adapter = CafeAdapter(cafeList)
        view.rv_cafe.adapter = adapter
        view.rv_cafe.layoutManager = LinearLayoutManager(context)


        return view
    }
}