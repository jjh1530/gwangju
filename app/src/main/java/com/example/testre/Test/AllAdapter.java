package com.example.testre.Test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testre.R;

import java.util.ArrayList;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.UserViewHolder> {

    private ArrayList<All> arrayList;
    // 어뎁터에서 엑티비티 액션을 처리함
    private Context context;

    public AllAdapter(ArrayList<All> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //실제 리스트뷰가 어뎁터에 연결
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_item,parent,false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tv_num.setText(arrayList.get(position).getNum());
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPicture())
                .into(holder.iv_all);
        holder.tv_name.setText(String.valueOf(arrayList.get(position).getName()));
        holder.tv_info.setText(arrayList.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_num;
        ImageView iv_all;
        TextView tv_name;
        TextView tv_info;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_num = itemView.findViewById(R.id.tv_allnum);
            this.iv_all = itemView.findViewById(R.id.iv_all);
            this.tv_name = itemView.findViewById(R.id.tv_allname);
            this.tv_info = itemView.findViewById(R.id.tv_allinfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentPosition = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), AllActivityitem.class);
                    intent.putExtra("name",arrayList.get(currentPosition).getName());
                    intent.putExtra("picture",arrayList.get(currentPosition).getPicture());
                    intent.putExtra("info",arrayList.get(currentPosition).getInfo());
                    view.getContext().startActivity(intent);


                    //All all = arrayList.get(currentPosition);

                    //Toast.makeText(context,all.getName() + " 입니다.",Toast.LENGTH_SHORT).show();
                    }
            });


        }
    }
}
