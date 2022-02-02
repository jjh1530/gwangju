package com.example.testre.All;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testre.R;
import com.squareup.picasso.Picasso;

// 전체 아이템 리스트 클릭시 넘어가는 아이템 페이지
public class AllActivityitem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item);

        TextView tv_all_name = findViewById(R.id.tv_all_name);
        ImageView tv_all_picture = findViewById(R.id.tv_all_picture);
        TextView tv_all_info = findViewById(R.id.tv_all_info);


        Intent intent = getIntent();
        String imageuri = intent.getStringExtra("picture");
        // 의존성 추가 후 imageuri 연결
        Picasso.with(this).load(imageuri).into(tv_all_picture);
        tv_all_name.setText(intent.getStringExtra("name"));
        tv_all_info.setText(intent.getStringExtra("info"));

    }
}