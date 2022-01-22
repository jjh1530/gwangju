package com.example.testre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testre.Bar.BarAdapter
import com.example.testre.Bar.FragmentBar
import com.example.testre.Cafe.FragmentCafe
import com.example.testre.Resturant.FragmentResturant
import com.example.testre.databinding.ActivityMenuBinding
import com.google.android.material.tabs.TabLayoutMediator


class MenuActivity : AppCompatActivity() {
    val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.hasExtra("string_uid")
        var uid = intent.getStringExtra("string_uid")


        //페이지 데이터를 로드
        val list = listOf( FragmentBar(), FragmentCafe(), FragmentResturant())
        //아답터 생성
        val pagerAdapter = FragmentPagerAdapter(list,this)
        // 아답터와 뷰페이저 연결
        binding.viewPager.adapter = pagerAdapter
        // 탭 메뉴의 개수만큼 제목 목록으로 생성
        val titles = listOf("술집","카페","식당")
        // 탭레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles.get(position)
        }.attach()



    }
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }
}