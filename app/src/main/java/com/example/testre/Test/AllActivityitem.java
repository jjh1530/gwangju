package com.example.testre.Test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testre.R;
import com.example.testre.Test.All;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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