package com.example.testre.Test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.testre.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<All> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        recyclerView = findViewById(R.id.rv_firebase);
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트

        //파이어베이스 기능 가져와서 연동
        database = FirebaseDatabase.getInstance();
        //db 테이블 연결
        databaseReference = database.getReference("All");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복분으로 리스트 추출
                    All all = snapshot.getValue(All.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    arrayList.add(all); // 어레이 리스트에 user클래스 데이터 추가
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //디비를 가져오던중 에러 발생 시
                Log.e("AllAcitity",String.valueOf(error.toException())); // 에러문 출력
            }
        });
        adapter = new AllAdapter(arrayList,this);
        recyclerView.setAdapter(adapter); //리사이클러뷰 어뎁터 연결
    }
}